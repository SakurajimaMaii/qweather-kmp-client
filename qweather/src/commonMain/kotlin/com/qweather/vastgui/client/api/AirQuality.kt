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

package com.qweather.vastgui.client.api

import com.qweather.vastgui.client.QWeather
import com.qweather.vastgui.client.api.base.Api
import com.qweather.vastgui.client.model.airquality.CurrentAirQuality
import com.qweather.vastgui.client.model.airquality.DailyAirQuality
import com.qweather.vastgui.client.model.airquality.HourlyAirQuality
import com.qweather.vastgui.client.model.airquality.StationAirQuality
import com.qweather.vastgui.client.utils.Coordinate
import com.qweather.vastgui.client.utils.LocationID
import com.qweather.vastgui.client.utils.apiCatching
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

/**
 * [空气质量](https://dev.qweather.com/docs/api/air-quality/)
 *
 * 全球空气质量 API ，适配当地空气质量标准，可以轻松的获取指定位置的空气质量、污染物和健康建议。目前已经覆盖 100
 * 多个国家或地区数据，包括实时和预报数据，分辨率为 1x1 公里。
 *
 * @since 2.0.0
 */
class AirQuality internal constructor(override val client: QWeather) : Api {

    override val url: String = "/airquality/v1"

    /**
     * [实时空气质量](https://dev.qweather.com/docs/api/air-quality/air-current/)
     *
     * 实时空气质量 API 提供指定地点的实时空气质量数据，精度为1x1公里。
     * - 基于各个国家或地区当地标准的AQI、AQI等级、颜色和首要污染物
     * - 和风天气通用AQI
     * - 污染物浓度值、分指数
     * - 健康建议
     * - 相关联的监测站信息
     *
     * 我们推荐阅读[空气质量信息文档](https://dev.qweather.com/docs/resource/air-info/)，以便了解指数类型、污染物、支持的国家等信息。
     *
     * @param location 需要查询的地区，仅支持 [Coordinate] 。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     * @since 2.0.0
     */
    suspend fun current(
        location: Coordinate,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<CurrentAirQuality> = apiCatching {
        client.httpClient.get("$url/current/${location.latitude}/${location.longitude}") {
            parameter("lang", lang)
        }.body()
    }

    /**
     * [空气质量小时预报](https://dev.qweather.com/docs/api/air-quality/air-hourly-forecast/)。
     *
     * 空气质量小时预报 API 提供未来 24 小时空气质量的数据，包括 AQI 、污染物浓度、分指数以及健康建议。
     *
     * 我们推荐阅读[空气质量信息文档](https://dev.qweather.com/docs/resource/air-info/)，以便了解
     * AQI 的类型、污染物、支持的国家等信息。
     *
     * @param location 需要查询的地区，仅支持 [Coordinate] 。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     * @since 2.0.0
     */
    suspend fun hourly(
        location: Coordinate,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<HourlyAirQuality> = apiCatching {
        client.httpClient.get("$url/hourly/${location.latitude}/${location.longitude}") {
            parameter("lang", lang)
        }.body()
    }

    /**
     * [空气质量每日预报](https://dev.qweather.com/docs/api/air-quality/air-daily-forecast/)。
     *
     * 空气质量每日预报 API 提供未来 3 天的空气质量（AQI）预报、污染物浓度值和健康建议。
     *
     * 我们推荐阅读[空气质量信息文档](https://dev.qweather.com/docs/resource/air-info/)，以便了解
     * AQI 的类型、污染物、支持的国家等信息。
     *
     * @param location 需要查询的地区，仅支持 [Coordinate] 。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     * @since 2.0.0
     */
    suspend fun daily(
        location: Coordinate,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<DailyAirQuality> = apiCatching {
        client.httpClient.get("$url/daily/${location.latitude}/${location.longitude}") {
            parameter("lang", lang)
        }.body()
    }

    /**
     * [监测站数据](https://dev.qweather.com/docs/api/air-quality/air-station/)
     *
     * 监测站数据 API 提供各个国家或地区监测站的污染物浓度值。
     *
     * 警告：监测站的观测值仅供参考，可能由于故障、移除、维护或当地法律法规等各种原因导致数据延迟或缺失，我们无法确保该数据的可用性。
     *
     * @param location 需要查询的地区，仅支持 [LocationID] ，LocationID 可通过 [GeoAPI][Geo]
     * 获取。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     */
    suspend fun station(
        location: LocationID,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<StationAirQuality> = apiCatching {
        client.httpClient.get("$url/station/${location.location}") {
            parameter("lang", lang)
        }.body()
    }
}