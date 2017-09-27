/*
 * Copyright (c) 2017 The sky Authors.
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

package com.sky.android.news.base

import com.sky.android.common.utils.Alog
import com.sky.android.news.data.DataException
import rx.Subscriber

/**
 * Created by sky on 17-9-21.
 */
abstract class BaseSubscriber<T> : Subscriber<T>() {

    private val TAG = BaseSubscriber::class.java.simpleName

    override fun onCompleted() {
    }

    override fun onError(e: Throwable) {

        if (e is DataException) {
            // 自己定义异常处理
            onHandlerError(e.message!!, e)
            return
        }

        // 其他异常
        onHandlerError(e.message!!, e)
    }

    private fun onHandlerError(msg: String, tr: Throwable) {

        val handler = onError(msg, tr)

        if (handler) return

        Alog.e(TAG, "数据处理异常", tr)
    }

    open fun onError(msg: String, tr: Throwable): Boolean {
        return false
    }
}