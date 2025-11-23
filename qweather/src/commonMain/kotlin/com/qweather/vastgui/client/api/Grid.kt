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
import com.qweather.vastgui.client.model.grid.DailyGrid
import com.qweather.vastgui.client.model.grid.HourlyGrid
import com.qweather.vastgui.client.model.grid.NowGrid
import com.qweather.vastgui.client.utils.Coordinate
import com.qweather.vastgui.client.utils.Day
import com.qweather.vastgui.client.utils.Hour
import com.qweather.vastgui.client.utils.apiCatching
import io.ktor.client.call.*
import io.ktor.client.request.*

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

/**
 * 格点天气。
 */
class Grid internal constructor(override val client: QWeather) : Api {

    override val url: String = "/v7/grid-weather"

    /**
     * [格点实时天气](https://dev.qweather.com/docs/api/weather/grid-weather-now/) 。
     *
     * 基于数值模式的天气预报数据，提供全球指定坐标的实时天气，分辨率 3-5 公里。
     *
     * 提示： 格点天气预报是基于数值预报模型生成，不适宜与观测站数据对比。
     * 如需基于观测站的城市天气，请使用 [Weather.now]。格点天气采用 UTC 0
     * 时区表示时间。
     *
     * @param location 需要查询地区的以英文逗号分隔的经度,纬度坐标 [Coordinate] 。
     * @param unit 数据单位设置，可选值包括unit=m（公制单位，默认）和unit=i（英制单位）。更多选项和说明参考
     * [度量衡单位](https://dev.qweather.com/docs/resource/unit) 。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     */
    suspend fun now(
        location: Coordinate,
        unit: QWeather.Units = QWeather.Units.M,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<NowGrid> = apiCatching {
        client.httpClient.get("$url/now") {
            url {
                parameter("location", location.location)
                parameter("lang", lang)
                parameter("unit", unit)
            }
        }.body()
    }

    /**
     * [格点每日天气预报](https://dev.qweather.com/docs/api/weather/grid-weather-daily-forecast/) 。
     *
     * 基于数值模式的天气预报数据，提供全球指定坐标的实时天气，分辨率 3-5 公里。
     *
     * 提示： 格点天气预报是基于数值预报模型生成，不适宜与观测站数据对比。
     * 如需基于观测站的城市天气，请使用 [Weather.now]。格点天气采用 UTC 0
     * 时区表示时间。
     *
     * @param location 需要查询地区的以英文逗号分隔的经度,纬度坐标 [Coordinate] 。
     * @param unit 数据单位设置，可选值包括unit=m（公制单位，默认）和unit=i（英制单位）。更多选项和说明参考
     * [度量衡单位](https://dev.qweather.com/docs/resource/unit) 。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     */
    suspend fun daily(
        days: Day,
        location: Coordinate,
        unit: QWeather.Units = QWeather.Units.M,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<DailyGrid> = apiCatching {
        check(Day.Day3 == days || Day.Day7 == days) { "无效的时间范围，请参考：https://dev.qweather.com/docs/finance/subscription/#comparison" }
        client.httpClient.get("$url/${days.range}") {
            parameter("location", location.location)
            parameter("lang", lang)
            parameter("unit", unit)
        }.body()
    }

    /**
     * [格点逐小时天气预报](https://dev.qweather.com/docs/api/weather/grid-weather-hourly-forecast/) 。
     *
     * 基于数值模式的天气预报数据，提供全球指定坐标的实时天气，分辨率 3-5 公里。
     *
     * 提示： 格点天气预报是基于数值预报模型生成，不适宜与观测站数据对比。
     * 如需基于观测站的城市天气，请使用 [Weather.now]。格点天气采用 UTC 0
     * 时区表示时间。
     *
     * @param location 需要查询地区的以英文逗号分隔的经度,纬度坐标 [Coordinate] 。
     * @param unit 数据单位设置，可选值包括unit=m（公制单位，默认）和unit=i（英制单位）。更多选项和说明参考
     * [度量衡单位](https://dev.qweather.com/docs/resource/unit) 。
     * @param lang 多语言设置，请阅读
     * [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     */
    suspend fun hourly(
        hours: Hour,
        location: Coordinate,
        unit: QWeather.Units = QWeather.Units.M,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<HourlyGrid> = apiCatching {
        val standardRange =
            client.plan.isStandard() && (Hour.Hour24 == hours || Hour.Hour72 == hours)
        val freeRange = client.plan.isFree() && Hour.Hour24 == hours
        check(standardRange || freeRange) { "无效的时间范围，请参考：https://dev.qweather.com/docs/finance/subscription/#comparison" }
        client.httpClient.get("$url/${hours.range}") {
            parameter("location", location.location)
            parameter("lang", lang)
            parameter("unit", unit)
        }.body()
    }
}