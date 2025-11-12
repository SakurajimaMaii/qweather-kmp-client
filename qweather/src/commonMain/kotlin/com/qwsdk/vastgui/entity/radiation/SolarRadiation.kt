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

package com.qwsdk.vastgui.entity.radiation

import com.qwsdk.vastgui.entity.Refer
import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [太阳辐射逐小时预报](https://dev.qweather.com/docs/api/solar-radiation/webapi-v7-solar-radiation-hourly-forecast/) 。
 *
 * @property code 请参考 [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property radiation 参考 [Radiation] 。
 * @property refer 参考 [Refer] 。
 * @property updateTime 当前 [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time) 。
 */
@Serializable
@Deprecated(
    message = "当前 API 已弃用，预计在2026年9月1日停止服务。",
    level = DeprecationLevel.WARNING
)
data class SolarRadiation(
        @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    val radiation: List<Radiation> = emptyList(),
    val refer: Refer,
    val updateTime: String,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [太阳辐射逐小时预报](https://dev.qweather.com/docs/api/solar-radiation/solar-radiation-hourly-forecast/)
     *
     * @property diffuse 太阳散射辐射，W/m²。
     * @property direct 太阳直接辐射，W/m²。
     * @property fxTime 逐小时预报时间。
     * @property net 净太阳辐射，W/m²。
     */
    @Serializable
    data class Radiation(
        val diffuse: String,
        val direct: String,
        val fxTime: String,
        val net: String
    )
}