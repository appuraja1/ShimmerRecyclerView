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

class ShimmerAdapter : RecyclerView.Adapter<ShimmerViewHolder>() {

    private var itemCount: Int = 0
    private var layoutReference: Int = 0
    private var shimmerAngle: Int = 0
    private var shimmerColor: Int = 0
    private var shimmerDuration: Int = 0
    private var shimmerMaskWidth: Float = 0f
    private var isAnimationReversed: Boolean = false
    private var shimmerItemBackground: Drawable? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ShimmerViewHolder(inflater, parent, layoutReference).apply {
            setShimmerColor(shimmerColor)
            setShimmerAngle(shimmerAngle)
            setShimmerMaskWidth(shimmerMaskWidth)
            setShimmerViewHolderBackground(shimmerItemBackground)
            setShimmerAnimationDuration(shimmerDuration)
            setAnimationReversed(isAnimationReversed)
        }
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = itemCount

    fun setMinItemCount(count: Int) {
        this.itemCount = count
    }

    fun setShimmerAngle(angle: Int) {
        this.shimmerAngle = angle
    }

    fun setShimmerColor(@ColorInt color: Int) {
        this.shimmerColor = color
    }

    fun setShimmerMaskWidth(maskWidth: Float) {
        this.shimmerMaskWidth = maskWidth
    }

    fun setShimmerItemBackground(background: Drawable?) {
        this.shimmerItemBackground = background
    }

    fun setShimmerDuration(duration: Int) {
        this.shimmerDuration = duration
    }

    fun setLayoutReference(@LayoutRes layoutRes: Int) {
        this.layoutReference = layoutRes
    }

    fun setAnimationReversed(reversed: Boolean) {
        this.isAnimationReversed = reversed
    }
}