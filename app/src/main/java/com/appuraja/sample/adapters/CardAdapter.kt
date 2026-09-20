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
package com.appuraja.sample.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appuraja.sample.models.ItemCard
import com.appuraja.sample.utils.BaseUtils
import com.appuraja.sample.viewholders.ItemHolder

class CardAdapter : RecyclerView.Adapter<ItemHolder>() {

    private var cards: List<ItemCard> = emptyList()
    private var type: Int = BaseUtils.TYPE_LIST

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.newInstance(parent, type)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int = cards.size

    @SuppressLint("NotifyDataSetChanged")
    fun setCards(cards: List<ItemCard>?) {
        this.cards = cards ?: emptyList()
        notifyDataSetChanged()
    }

    fun setType(type: Int) {
        this.type = type
    }
}