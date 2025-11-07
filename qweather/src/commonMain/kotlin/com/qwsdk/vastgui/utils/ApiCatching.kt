/*
 * Copyright 2024 VastGui
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

package com.qwsdk.vastgui.utils

import com.qwsdk.vastgui.entity.BaseResponse

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

internal inline fun <T, R : BaseResponse> T.apiCatching(block: T.() -> R): Result<R> {
    val response = try {
        block()
    } catch (exception: Throwable) {
        return Result.failure(exception)
    }
    val error = response.error
    return if (error != null)
        Result.failure(RuntimeException("错误代码：${error.status}，错误信息：${error.title}(${error.detail})，详情参考：${error.type}"))
    else
        Result.success(response)
}