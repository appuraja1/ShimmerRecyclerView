/**
 * Copyright 2026 Appu Raja
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appuraja.views.shimmer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShimmerRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var actualAdapter: Adapter<*>? = null
        private set

    val shimmerAdapter: ShimmerAdapter = ShimmerAdapter()

    private var shimmerLayoutManager: LayoutManager? = null
    private var actualLayoutManager: LayoutManager? = null
    private var layoutManagerType: LayoutManagerType = LayoutManagerType.LINEAR_VERTICAL

    private var canScroll: Boolean = false

    @LayoutRes
    var layoutReference: Int = 0
        private set

    private var gridCount: Int = 2

    enum class LayoutManagerType {
        LINEAR_VERTICAL, LINEAR_HORIZONTAL, GRID
    }

    init {
        initAttributes(context, attrs)
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ShimmerRecyclerView, 0, 0)

        val shimmerAngle: Int
        val shimmerColor: Int
        val shimmerDuration: Int
        val shimmerMaskWidth: Float
        val isAnimationReversed: Boolean
        val shimmerItemBackground: Drawable?

        try {
            setDemoLayoutReference(
                a.getResourceId(
                    R.styleable.ShimmerRecyclerView_shimmer_demo_layout,
                    R.layout.layout_sample_view
                )
            )
            setDemoChildCount(
                a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_child_count, 10)
            )
            setGridChildCount(
                a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_grid_child_count, 2)
            )

            layoutManagerType = when (a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_layout_manager_type, 0)) {
                0 -> LayoutManagerType.LINEAR_VERTICAL
                1 -> LayoutManagerType.LINEAR_HORIZONTAL
                2 -> LayoutManagerType.GRID
                else -> throw IllegalArgumentException("This value for layout manager is not valid!")
            }

            shimmerAngle = a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_angle, 0)
            shimmerColor = a.getColor(
                R.styleable.ShimmerRecyclerView_shimmer_demo_shimmer_color,
                getColorCompat(R.color.default_shimmer_color)
            )
            shimmerItemBackground = a.getDrawable(
                R.styleable.ShimmerRecyclerView_shimmer_demo_view_holder_item_background
            )
            shimmerDuration = a.getInteger(R.styleable.ShimmerRecyclerView_shimmer_demo_duration, 1500)
            shimmerMaskWidth = a.getFloat(R.styleable.ShimmerRecyclerView_shimmer_demo_mask_width, 0.5f)
            isAnimationReversed = a.getBoolean(
                R.styleable.ShimmerRecyclerView_shimmer_demo_reverse_animation,
                false
            )
        } finally {
            a.recycle()
        }

        shimmerAdapter.apply {
            setShimmerAngle(shimmerAngle)
            setShimmerColor(shimmerColor)
            setShimmerMaskWidth(shimmerMaskWidth)
            setShimmerItemBackground(shimmerItemBackground)
            setShimmerDuration(shimmerDuration)
            setAnimationReversed(isAnimationReversed)
        }

        showShimmerAdapter()
    }

    fun setGridChildCount(count: Int) {
        this.gridCount = count
        if (layoutManagerType == LayoutManagerType.GRID) {
            shimmerLayoutManager = null
        }
    }

    fun setDemoLayoutManager(type: LayoutManagerType) {
        this.layoutManagerType = type
        this.shimmerLayoutManager = null
    }

    fun setDemoChildCount(count: Int) {
        shimmerAdapter.setMinItemCount(count)
    }

    fun setDemoShimmerDuration(duration: Int) {
        shimmerAdapter.setShimmerDuration(duration)
    }

    fun setDemoShimmerMaskWidth(maskWidth: Float) {
        shimmerAdapter.setShimmerMaskWidth(maskWidth)
    }

    fun showShimmerAdapter() {
        canScroll = false

        if (shimmerLayoutManager == null) {
            initShimmerManager()
        }

        layoutManager = shimmerLayoutManager
        adapter = shimmerAdapter
    }

    fun hideShimmerAdapter() {
        canScroll = true
        layoutManager = actualLayoutManager
        adapter = actualAdapter
    }

    override fun setLayoutManager(manager: LayoutManager?) {
        if (manager == null) {
            actualLayoutManager = null
        } else if (manager !== shimmerLayoutManager) {
            actualLayoutManager = manager
        }
        super.setLayoutManager(manager)
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        if (adapter == null) {
            actualAdapter = null
        } else if (adapter !== shimmerAdapter) {
            actualAdapter = adapter
        }
        super.setAdapter(adapter)
    }

    fun setDemoLayoutReference(@LayoutRes layoutRes: Int) {
        this.layoutReference = layoutRes
        shimmerAdapter.setLayoutReference(layoutRes)
    }

    private fun initShimmerManager() {
        shimmerLayoutManager = when (layoutManagerType) {
            LayoutManagerType.LINEAR_VERTICAL -> object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean = canScroll
            }
            LayoutManagerType.LINEAR_HORIZONTAL -> object : LinearLayoutManager(context, HORIZONTAL, false) {
                override fun canScrollHorizontally(): Boolean = canScroll
            }
            LayoutManagerType.GRID -> object : GridLayoutManager(context, gridCount) {
                override fun canScrollVertically(): Boolean = canScroll
            }
        }
    }

    @ColorInt
    private fun getColorCompat(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }
}