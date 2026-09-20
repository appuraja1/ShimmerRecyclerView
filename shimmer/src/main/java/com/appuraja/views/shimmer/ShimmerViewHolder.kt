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
import io.supercharge.shimmerlayout.ShimmerLayout

class ShimmerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    @LayoutRes innerViewResId: Int
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.viewholder_shimmer, parent, false)) {

    private val shimmerLayout: ShimmerLayout = itemView as ShimmerLayout

    init {
        if (innerViewResId != 0) {
            inflater.inflate(innerViewResId, shimmerLayout, true)
        }
    }

    fun setShimmerAngle(angle: Int) {
        shimmerLayout.setShimmerAngle(angle)
    }

    fun setShimmerColor(@ColorInt color: Int) {
        shimmerLayout.setShimmerColor(color)
    }

    fun setShimmerMaskWidth(maskWidth: Float) {
        shimmerLayout.setMaskWidth(maskWidth)
    }

    fun setShimmerViewHolderBackground(viewHolderBackground: Drawable?) {
        shimmerLayout.background = viewHolderBackground
    }

    fun setShimmerAnimationDuration(duration: Int) {
        shimmerLayout.setShimmerAnimationDuration(duration)
    }

    fun setAnimationReversed(animationReversed: Boolean) {
        shimmerLayout.setAnimationReversed(animationReversed)
    }

    fun bind() {
        shimmerLayout.startShimmerAnimation()
    }
}