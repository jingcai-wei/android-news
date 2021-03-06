/*
 * Copyright (c) 2020 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.android.news.ui.main.details

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hi.dhl.binding.viewbind
import com.sky.android.news.R
import com.sky.android.news.databinding.AppBarFrameBinding
import com.sky.android.news.ext.applyTo
import com.sky.android.news.ui.common.CommonActivity
import com.sky.android.news.ui.base.NewsActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by sky on 17-9-23.
 */
@AndroidEntryPoint
class DetailsActivity : NewsActivity() {

    companion object {

        const val F_NAME = "fName"
    }

    private val binding: AppBarFrameBinding by viewbind()

    override val layoutId: Int
        get() = R.layout.app_bar_frame

    override fun initView(intent: Intent) {

        // 设置ActionBar
        binding.apply {
            toolbar.applyTo(this@DetailsActivity, R.string.app_name, true)
        }

        val fName = intent.getStringExtra(CommonActivity.F_NAME)

        val args = Bundle().apply {
            putSerializable("item", intent.getSerializableExtra("item"))
        }

        val fragmentManager = supportFragmentManager
        val fragment = Fragment.instantiate(this, fName, args)
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }
}