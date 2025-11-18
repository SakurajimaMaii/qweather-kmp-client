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
import com.qwsdk.vastgui.entity.air.DailyAir
import com.qwsdk.vastgui.entity.air.NowAir
import com.qwsdk.vastgui.utils.Coordinate
import com.qwsdk.vastgui.utils.Location
import com.qwsdk.vastgui.utils.LocationID
import com.qwsdk.vastgui.utils.apiCatching
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * [空气质量(beta)](https://dev.qweather.com/docs/api/air-quality/)
 *
 * 中国 3000+ 市县区及 1700+ 监测站点的空气质量 AQI 数据，包括空气质量 （AQI） 实时数据，空气质量未来 5 天预报。
 */
@OptIn(ExperimentalTime::class)
@Deprecated(
    message = "2025年10月1日起，新注册的开发者将无法使用弃用版本空气质量 API，2026年6月1日起，弃用版本空气质量 API 将对所有开发者关闭并停止服务，所有开发者将无法再访问弃用版本获取数据，包括使用API、SDK",
    level = DeprecationLevel.WARNING
)
class Air internal constructor(override val client: QWeather) : Api {

    override val url: String = "/v7/air"

    /**
     * [实时空气质量](https://dev.qweather.com/docs/api/air-quality/webapi-v7-air-now/)
     *
     * 实时空气质量API，支持中国3000+市县区以及1700+国控站点实时空气质量的查询，
     * 包括AQI数值、空气质量等级、首要污染物、PM10、PM2.5、臭氧、二氧化氮、二氧化硫、 一氧化碳数值。
     *
     * @param location 需要查询地区的 [LocationID] 或以英文逗号分隔的经度,纬度坐标 [Coordinate] ，
     * LocationID 可通过 [GeoAPI][Geo] 获取。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     */
    @Deprecated(
        message = "2025年10月1日起，新注册的开发者将无法使用弃用版本空气质量 API，2026年6月1日起，弃用版本空气质量 API 将对所有开发者关闭并停止服务，所有开发者将无法再访问弃用版本获取数据，包括使用API、SDK",
        level = DeprecationLevel.WARNING
    )
    suspend fun now(
        location: Location,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<NowAir> = apiCatching {
        val limit = LocalDate(2026, 6, 1).atStartOfDayIn(TimeZone.UTC)
        check(Clock.System.now() < limit) { "2025年10月1日起，新注册的开发者将无法使用弃用版本空气质量 API，2026年6月1日起，弃用版本空气质量 API 将对所有开发者关闭并停止服务，所有开发者将无法再访问弃用版本获取数据，包括使用API、SDK" }
        check(location is LocationID || location is Coordinate) { "无效类型，当前仅支持 Coordinate 或 LocationID" }
        client.httpClient.get("$url/now") {
            url {
                parameter("location", location.location)
                parameter("lang", lang)
            }
        }.body()
    }

    /**
     * [空气质量每日预报](https://dev.qweather.com/docs/api/air-quality/webapi-v7-air-daily-forecast/)
     *
     * 空气质量每日预报 API ，支持全国 3000+ 市县区空气质量预报数据的查询，包括 AQI 预报、 首要污染物预报、空气质量等级预报。
     *
     * @param location 需要查询地区的 [LocationID] 或以英文逗号分隔的经度,纬度坐标 [Coordinate] ，
     * LocationID 可通过 [GeoAPI][Geo] 获取。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     */
    @Deprecated(
        message = "2025年10月1日起，新注册的开发者将无法使用弃用版本空气质量 API，2026年6月1日起，弃用版本空气质量 API 将对所有开发者关闭并停止服务，所有开发者将无法再访问弃用版本获取数据，包括使用API、SDK",
        level = DeprecationLevel.WARNING
    )
    suspend fun daily(
        location: Location,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<DailyAir> = apiCatching {
        val limit = LocalDate(2026, 6, 1).atStartOfDayIn(TimeZone.UTC)
        check(Clock.System.now() < limit) { "2025年10月1日起，新注册的开发者将无法使用弃用版本空气质量 API，2026年6月1日起，弃用版本空气质量 API 将对所有开发者关闭并停止服务，所有开发者将无法再访问弃用版本获取数据，包括使用API、SDK" }
        check(location is LocationID || location is Coordinate) { "无效类型，当前仅支持 Coordinate 或 LocationID" }
        client.httpClient.get("$url/5d") {
            url {
                parameter("location", location.location)
                parameter("lang", lang)
            }
        }.body()
    }
}