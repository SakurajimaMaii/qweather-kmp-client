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
 * [月升月落和月相](https://dev.qweather.com/docs/api/astronomy/moon-and-moon-phase/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property moonPhase 参考 [MoonPhase] 。
 * @property moonrise 当天
 * [月升时间](https://dev.qweather.com/docs/resource/sun-moon-info/#moonrise-and-moonset)
 * ，可能为空。
 * @property moonset 当天
 * [月落时间](https://dev.qweather.com/docs/resource/sun-moon-info/#moonrise-and-moonset)
 * ，可能为空。
 * @property refer 参考 [Refer] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)。
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class Moon(
    val fxLink: String? = null,
    val moonPhase: List<MoonPhase> = emptyList(),
    val moonrise: String? = null,
    val moonset: String? = null,
    val refer: Refer = Refer(),
    val updateTime: String? = null,
    val error: ErrorInfo? = null,
    override val code: String = error?.status?.toString() ?: "200"
) : QWSdkResponse {
    /**
     * [月升月落和月相](https://dev.qweather.com/docs/api/astronomy/moon-and-moon-phase/)
     *
     * @property fxTime 月相逐小时预报时间
     * @property icon 月相 [图标代码](https://dev.qweather.com/docs/resource/icons/)
     * ， 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property illumination 月亮照明度，百分比数值。
     * @property name
     * [月相名称](https://dev.qweather.com/docs/resource/sun-moon-info/#moon-phase)
     * 。
     * @property value 月相数值。
     */
    @Serializable
    data class MoonPhase(
        val fxTime: String,
        val icon: String,
        val illumination: String,
        val name: String,
        val value: String
    )

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