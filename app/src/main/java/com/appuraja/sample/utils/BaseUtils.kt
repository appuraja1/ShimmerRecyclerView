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
package com.appuraja.sample.utils

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.appuraja.sample.R
import com.appuraja.sample.models.ItemCard
import com.appuraja.sample.utils.view.CardPaddingItemDecoration

object BaseUtils {

    const val TYPE_LIST = 0
    const val TYPE_GRID = 1
    const val TYPE_SECOND_LIST = 2
    const val TYPE_SECOND_GRID = 3

    private fun getListCards(resources: Resources): List<ItemCard> {
        val ndtvCard = createItemCard(
            resources,
            R.string.ndtv_titletext,
            R.string.ndtv_image_url,
            R.string.ndtv_subtext,
            R.string.ndtv_summarytext
        )

        val opCard = createItemCard(
            resources,
            R.string.op_titletext,
            R.string.op_image_url,
            R.string.op_subtext,
            R.string.op_summarytext
        )

        val gotCard = createItemCard(
            resources,
            R.string.got_titletext,
            R.string.got_image_url,
            R.string.got_subtext,
            R.string.got_summarytext
        )

        val jetCard = createItemCard(
            resources,
            R.string.jet_titletext,
            R.string.jet_image_url,
            R.string.jet_subtext,
            R.string.jet_summarytext
        )

        return listOf(ndtvCard, opCard, gotCard, jetCard)
    }

    private fun getGridCards(resources: Resources): List<ItemCard> {
        val on7 = createItemCard(
            resources,
            R.string.on7_titletext,
            R.string.on7_image_url,
            R.string.on7_subtext,
            R.string.on7_summarytext
        )

        val note5 = createItemCard(
            resources,
            R.string.note5_titletext,
            R.string.note5_image_url,
            R.string.note5_subtext,
            R.string.note5_summarytext
        )

        val pixel = createItemCard(
            resources,
            R.string.pix_titletext,
            R.string.pix_image_url,
            R.string.pix_subtext,
            R.string.pix_summarytext
        )

        val iphone6 = createItemCard(
            resources,
            R.string.i6_titletext,
            R.string.i6_image_url,
            R.string.i6_subtext,
            R.string.i6_summarytext
        )

        val moto = createItemCard(
            resources,
            R.string.moto_titletext,
            R.string.moto_image_url,
            R.string.moto_subtext,
            R.string.moto_summarytext
        )

        val s7 = createItemCard(
            resources,
            R.string.s7_titletext,
            R.string.s7_image_url,
            R.string.s7_subtext,
            R.string.s7_summarytext
        )

        return listOf(on7, note5, pixel, iphone6, s7, moto)
    }

    fun getCards(resources: Resources, type: Int): List<ItemCard>? = when (type) {
        TYPE_LIST, TYPE_SECOND_LIST -> getListCards(resources)
        TYPE_GRID, TYPE_SECOND_GRID -> getGridCards(resources)
        else -> null
    }

    fun getDemoConfiguration(configurationType: Int, context: Context): DemoConfiguration? =
        when (configurationType) {
            TYPE_LIST -> DemoConfiguration().apply {
                styleResource = R.style.AppTheme
                layoutResource = R.layout.activity_list
                layoutManager = LinearLayoutManager(context)
                titleResource = R.string.ab_list_title
            }

            TYPE_GRID -> DemoConfiguration().apply {
                styleResource = R.style.AppThemeGrid
                layoutResource = R.layout.activity_grid
                layoutManager = GridLayoutManager(context, 2)
                titleResource = R.string.ab_grid_title
            }

            TYPE_SECOND_LIST -> DemoConfiguration().apply {
                styleResource = R.style.AppTheme
                layoutResource = R.layout.activity_second_list
                layoutManager = LinearLayoutManager(context)
                titleResource = R.string.ab_list_title
                itemDecoration = CardPaddingItemDecoration(context)
            }

            TYPE_SECOND_GRID -> DemoConfiguration().apply {
                styleResource = R.style.AppThemeGrid
                layoutResource = R.layout.activity_second_grid
                layoutManager = GridLayoutManager(context, 2)
                titleResource = R.string.ab_grid_title
            }

            else -> null
        }

    private fun createItemCard(
        resources: Resources,
        @StringRes title: Int,
        @StringRes imageUrl: Int,
        @StringRes description: Int,
        @StringRes summary: Int
    ): ItemCard = ItemCard().apply {
        this.title = resources.getString(title)
        this.thumbnailUrl = resources.getString(imageUrl)
        this.description = resources.getString(description)
        this.summaryText = resources.getString(summary)
    }
}