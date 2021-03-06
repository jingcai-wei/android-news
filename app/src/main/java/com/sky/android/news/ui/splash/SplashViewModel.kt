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

package com.sky.android.news.ui.splash

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sky.android.news.ui.base.NewsViewModel
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by sky on 2020-12-17.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
        application: Application
) : NewsViewModel(application) {

    private val mGranted: MutableLiveData<List<String>> = MutableLiveData()
    private val mDenied: MutableLiveData<List<String>> = MutableLiveData()

    val granted: LiveData<List<String>>
        get() = mGranted

    val denied: LiveData<List<String>>
        get() = mDenied

    /**
     * 请求权限
     */
    fun requestPermission(activity: Activity) {

        AndPermission.with(activity)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted{ mGranted.value = it }
                .onDenied{ mDenied.value = it }
                .start()
    }
}