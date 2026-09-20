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
package com.appuraja.sample.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.appuraja.sample.R
import com.appuraja.sample.models.ItemCard
import com.appuraja.sample.utils.BaseUtils.TYPE_GRID
import com.appuraja.sample.utils.BaseUtils.TYPE_LIST
import com.appuraja.sample.utils.BaseUtils.TYPE_SECOND_GRID
import com.appuraja.sample.utils.BaseUtils.TYPE_SECOND_LIST
import com.bumptech.glide.Glide

class ItemHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleView: TextView? = itemView.findViewById(R.id.card_title)
    private val descView: TextView? = itemView.findViewById(R.id.card_subtitle)
    private val thumbnailView: ImageView? = itemView.findViewById(R.id.card_image)
    private val summaryView: TextView? = itemView.findViewById(R.id.card_summary)

    fun bind(card: ItemCard) {
        titleView?.text = card.title
        descView?.text = card.description
        summaryView?.text = card.summaryText

        thumbnailView?.let { imageView ->
            Glide.with(itemView.context)
                .load(card.thumbnailUrl)
                .into(imageView)
        }
    }

    companion object {

        fun newInstance(container: ViewGroup, type: Int): ItemHolder {
            val layoutId = getLayoutResourceId(type)
            val root = LayoutInflater.from(container.context).inflate(layoutId, container, false)
            return ItemHolder(root)
        }

        @LayoutRes
        private fun getLayoutResourceId(type: Int): Int {
            return when (type) {
                TYPE_LIST -> R.layout.layout_news_card
                TYPE_SECOND_LIST -> R.layout.layout_second_news_card
                TYPE_GRID, TYPE_SECOND_GRID -> R.layout.layout_ecom_item
                else -> throw IllegalArgumentException("Invalid layout type: $type")
            }
        }
    }
}