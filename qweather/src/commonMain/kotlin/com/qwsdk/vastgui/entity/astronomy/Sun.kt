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

package com.qwsdk.vastgui.entity.astronomy

import com.qwsdk.vastgui.entity.Refer
import com.qwsdk.vastgui.utils.QWSdkResponse
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

/**
 * [日出日落](https://dev.qweather.com/docs/api/astronomy/sunrise-sunset/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [Refer] 。
 * @property sunrise
 * [日出时间](https://dev.qweather.com/docs/resource/sun-moon-info/#sunrise-and-sunset)
 * ，在高纬度地区可能为空。
 * @property sunset
 * [日落时间](https://dev.qweather.com/docs/resource/sun-moon-info/#sunrise-and-sunset)
 * ，在高纬度地区可能为空。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)。
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class Sun(
    val fxLink: String? = null,
    val refer: Refer = Refer(),
    val sunrise: String? = null,
    val sunset: String? = null,
    val updateTime: String? = null,
    val error: ErrorInfo? = null,
    override val code: String = error?.status?.toString() ?: "200"
) : QWSdkResponse {
    /**
     * 请求错误信息。
     *
     * ```
     * {
     *   "error": {
     *     "status": 400,
     *     "type": "https://dev.qweather.com/docs/resource/error-code/#data-not-available",
     *     "title": "Data Not Available",
     *     "detail": "Data for this location is temporarily unavailable, please try another location."
     *   }
     * }
     * ```
     *
     * @property status HTTP 状态码。
     * @property type 错误类型链接。
     * @property title 错误标题。
     * @property detail 错误详情描述。
     * @since 1.1.3
     */
    @Serializable
    data class ErrorInfo(
        @EncodeDefault
        val status: Int = 0,
        @EncodeDefault
        val type: String = "",
        @EncodeDefault
        val title: String = "",
        @EncodeDefault
        val detail: String = ""
    )
}