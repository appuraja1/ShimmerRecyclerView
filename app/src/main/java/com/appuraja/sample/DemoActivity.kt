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

package com.appuraja.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appuraja.sample.adapters.CardAdapter
import com.appuraja.sample.utils.BaseUtils
import com.appuraja.views.shimmer.ShimmerRecyclerView

class DemoActivity : AppCompatActivity() {

    private lateinit var adapter: CardAdapter
    private lateinit var shimmerRecyclerView: ShimmerRecyclerView

    private val type: Int
        get() = intent.getIntExtra(EXTRA_TYPE, BaseUtils.TYPE_LIST)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentType = type
        val demoConfiguration = BaseUtils.getDemoConfiguration(currentType, this)

        demoConfiguration?.let { config ->
            setTheme(config.styleResource)
            setContentView(config.layoutResource)
            setTitle(config.titleResource)

            shimmerRecyclerView = findViewById(R.id.shimmer_recycler_view)

            config.itemDecoration?.let { decoration ->
                shimmerRecyclerView.addItemDecoration(decoration)
            }

            adapter = CardAdapter().apply {
                setType(currentType)
            }

            shimmerRecyclerView.layoutManager = config.layoutManager
            shimmerRecyclerView.adapter = adapter
            shimmerRecyclerView.showShimmerAdapter()

            shimmerRecyclerView.postDelayed({ loadCards() }, 3000)
        }
    }

    private fun loadCards() {
        adapter.setCards(BaseUtils.getCards(resources, type))
        shimmerRecyclerView.hideShimmerAdapter()
    }

    companion object {
        const val EXTRA_TYPE = "type"
    }
}