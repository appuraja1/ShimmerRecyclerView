/**
 *
 * Copyright 2026 Appu Raja
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appuraja.views.shimmer

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    @LayoutRes innerViewResId: Int
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.viewholder_shimmer, parent, false)) {

    private val shimmerLayout: ShimmerFrameLayout = itemView as ShimmerFrameLayout
    private val shimmerBuilder = Shimmer.AlphaHighlightBuilder()

    init {
        if (innerViewResId != 0) {
            inflater.inflate(innerViewResId, shimmerLayout, true)
        }
    }

    fun setShimmerAngle(angle: Int) {
        // Facebook shimmer me angle direct float degree accept karta hai
        shimmerBuilder.setTilt(angle.toFloat())
        updateShimmer()
    }

    fun setShimmerColor(@ColorInt color: Int) {
        // Facebook Shimmer ColorHighlightBuilder color allow karta hai
        shimmerBuilder.setBaseAlpha(0.7f).setHighlightAlpha(1.0f)
        updateShimmer()
    }

    fun setShimmerViewHolderBackground(viewHolderBackground: Drawable?) {
        shimmerLayout.background = viewHolderBackground
    }

    fun setShimmerAnimationDuration(duration: Int) {
        shimmerBuilder.setDuration(duration.toLong())
        updateShimmer()
    }

    fun setAnimationReversed(animationReversed: Boolean) {
        if (animationReversed) {
            shimmerBuilder.setDirection(Shimmer.Direction.RIGHT_TO_LEFT)
        } else {
            shimmerBuilder.setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        }
        updateShimmer()
    }

    private fun updateShimmer() {
        shimmerLayout.setShimmer(shimmerBuilder.build())
    }

    fun bind() {
        shimmerLayout.startShimmer()
    }
}