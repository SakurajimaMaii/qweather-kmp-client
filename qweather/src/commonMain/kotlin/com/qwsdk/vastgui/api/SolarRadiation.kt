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

package com.qwsdk.vastgui.api

import com.qwsdk.vastgui.QWeather
import com.qwsdk.vastgui.api.base.Api
import com.qwsdk.vastgui.entity.radiation.SolarRadiation
import com.qwsdk.vastgui.entity.radiation.SolarRadiationForecast
import com.qwsdk.vastgui.utils.Coordinate
import com.qwsdk.vastgui.utils.Hour
import com.qwsdk.vastgui.utils.apiCatching
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/17

/**
 * [太阳辐射](https://dev.qweather.com/docs/api/solar-radiation/)
 *
 * 太阳辐射 API 支持获取全球辐射数据，包括 DNI、DHI、GHI 以及相关联的气象数据，最高 15 分钟间隔， 1x1 公里分辨率。
 */
class SolarRadiation internal constructor(override val client: QWeather) : Api {

    override val url: String = "/solarradiation/v1"

    /**
     * [太阳辐射逐小时预报](https://dev.qweather.com/docs/api/solar-radiation/webapi-v7-solar-radiation-hourly-forecast/) 。
     *
     * 太阳辐射API支持获取全球任意坐标的辐射数据，包括净太阳辐射，太阳散射辐射和太阳直接辐射。
     *
     * **注意：在一些特殊情况下，未来72小时预报有可能会缺少最后若干小时的数据。**
     *
     * @param location 需要查询地区的以英文逗号分隔的经度,纬度坐标 [Coordinate] 。
     */
    @OptIn(ExperimentalTime::class)
    @Deprecated(
        message = "当前 API 已弃用，预计在2026年9月1日停止服务。",
        level = DeprecationLevel.WARNING
    )
    suspend fun radiation(
        hour: Hour = Hour.Hour24,
        location: Coordinate
    ): Result<SolarRadiation> = runCatching {
        val limit = LocalDate(2026, 9, 1).atStartOfDayIn(TimeZone.UTC)
        check(Clock.System.now() < limit) { "当前 API 已弃用，预计在2026年9月1日停止服务。详情参考：https://dev.qweather.com/docs/api/solar-radiation/webapi-v7-solar-radiation-hourly-forecast/" }
        check(client.plan.isStandard()) { "无效权限，请参考：https://dev.qweather.com/docs/finance/subscription/#comparison" }
        check(hour is Hour.Hour24 || hour is Hour.Hour72) { "无效时间范围：仅支持 Hour24 或 Hour72。" }
        client.httpClient.get("/v7/solar-radiation/${hour.range}") {
            parameter("location", location.location)
        }.body()
    }

    /**
     * [太阳辐射预报](https://dev.qweather.com/docs/api/solar-radiation/solar-radiation-forecast/) 。
     *
     * @param location 查询地区的经纬度坐标。
     * @param hours 预报小时数，可选 1-60，默认 24。例如： hours=12。
     * @param internal 预报数据时间间隔，可选 15、30、60 分钟，默认 60。例如：interval=15。
     * @param tilt 光伏系统的倾斜角度，可选 0-90，整数。当 extra=poa 时该参数必传。例如：tilt=30。
     * @param azimuth 光伏系统的方位角，可选 0-359，整数，0 = 北。当 extra=poa 时该参数必传。例如：azimuth=180。
     * @param extra 为当前太阳辐照预报提供的额外信息，可选 weather（基本天气数据）和 poa（Plane of array，阵列平面辐照度，
     * 必须同时传递 tilt 和 azimuth），多项使用英文逗号分割。例如： extra=weather。
     * @since 1.1.3
     */
    suspend fun radiation(
        location: Coordinate,
        hours: Int = 24,
        internal: Int = 60,
        tilt: Int? = null,
        azimuth: Int? = null,
        extra: SolarRadiationExtra = SolarRadiationExtra.Weather
    ): Result<SolarRadiationForecast> = apiCatching {
        check(hours in 1..60 && (internal == 15 || internal == 30 || internal == 60)) { "预报小时数无效或者预报时间间隔无效" }
        if (extra == SolarRadiationExtra.Poa) {
            check(tilt in 0..90 && azimuth in 0..359) { "额外信息为 poa，必须提供 tilt 和 azimuth，并且在值范围内" }
        }
        client.httpClient.get("$url/forecast/${location.latitude}/${location.longitude}") {
            parameter("hours", hours)
            parameter("internal", internal)
            parameter("tilt", tilt)
            parameter("azimuth", azimuth)
            parameter("extra", extra.extra)
        }.body()
    }

    /**
     * @since 1.1.3
     */
    enum class SolarRadiationExtra(val extra: String) {
        Weather("weather"), Poa("poa")
    }
}