/*
 * Copyright 2025 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qweather.vastgui.client.model.base.error

import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7

/**
 * [错误码](https://dev.qweather.com/docs/resource/error-code/)。
 *
 * ```json
 * {
 *     "status": 400,
 *     "type": "https://dev.qweather.com/docs/resource/error-code/#invalid-parameters",
 *     "title": "Invalid Parameters",
 *     "detail": "Invalid parameters, please check your request.",
 *     "invalidParams": [
 *         "lang"
 *     ]
 * }
 * ```
 *
 * @property code 对应这个错误的HTTP status code 。
 * @property type 这是一个 URL 用于标识错误类型。
 * @property title 对错误的简短描述。
 * @property detail 对错误的详细描述。
 * @property invalidParams 标识错误或缺失的参数。
 * @since 2.0.0
 */
@Serializable
data class ErrorInfo(
    val detail: String = "",
    val invalidParams: List<String> = emptyList(),
    val status: Int? = null,
    val title: String? = null,
    val type: String? = null
)