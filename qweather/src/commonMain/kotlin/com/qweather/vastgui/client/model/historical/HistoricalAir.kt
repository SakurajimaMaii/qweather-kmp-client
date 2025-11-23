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

package com.qweather.vastgui.client.model.historical

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [空气质量时光机](https://dev.qweather.com/docs/api/time-machine/time-machine-air/)
 *
 * @property airHourly 参考 [AirHourly] 。
 * @property code 请参考 [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [Refer] 。
 */
@Serializable
data class HistoricalAir(
    val airHourly: List<AirHourly> = emptyList(),
    val fxLink: String? = null,
    val refer: Refer? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [空气质量时光机](https://dev.qweather.com/docs/api/time-machine/time-machine-air/)
     *
     * @property aqi 空气质量指数。
     * @property category 空气质量指数级别。
     * @property co 一氧化碳。
     * @property level 空气质量指数等级。
     * @property no2 二氧化氮。
     * @property o3 臭氧。
     * @property pm10 PM10 。
     * @property pm2p5 PM2.5 。
     * @property primary 空气质量的主要污染物，空气质量为优时，返回值为 NA 。
     * @property pubTime 空气质量数据发布时间。
     * @property so2 二氧化硫。
     */
    @Serializable
    data class AirHourly(
        val aqi: String? = null,
        val category: String? = null,
        val co: String? = null,
        val level: String? = null,
        val no2: String? = null,
        val o3: String? = null,
        val pm10: String? = null,
        val pm2p5: String? = null,
        val primary: String? = null,
        val pubTime: String? = null,
        val so2: String? = null
    )
}