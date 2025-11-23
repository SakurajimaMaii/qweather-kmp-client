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

package com.qweather.vastgui.client.model.minutely

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [分钟级降水](https://dev.qweather.com/docs/api/minutely/minutely-precipitation/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property minutely 参考 [Minutely] 。
 * @property refer 参考 [Refer] 。
 * @property summary 分钟降水描述。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 */
@Serializable
data class RainMinutely(
    val fxLink: String? = null,
    val minutely: List<Minutely> = emptyList(),
    val refer: Refer? = null,
    val summary: String? = null,
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [分钟级降水](https://dev.qweather.com/docs/api/minutely/minutely-precipitation/)
     *
     * @property fxTime 预报时间。
     * @property precip 5分钟累计降水量，单位毫米。
     * @property type 降水类型：rain = 雨，snow = 雪。
     */
    @Serializable
    data class Minutely(
        val fxTime: String? = null,
        val precip: String? = null,
        val type: String? = null
    )
}