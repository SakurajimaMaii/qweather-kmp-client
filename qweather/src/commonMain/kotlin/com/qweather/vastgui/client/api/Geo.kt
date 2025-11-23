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
import com.qweather.vastgui.client.model.geo.LookupGeo
import com.qweather.vastgui.client.model.geo.TopGeo
import com.qweather.vastgui.client.model.geo.poi.POIGeo
import com.qweather.vastgui.client.model.geo.poi.POIRangeGeo
import com.qweather.vastgui.client.utils.Coordinate
import com.qweather.vastgui.client.utils.Location
import com.qweather.vastgui.client.utils.apiCatching
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * [GeoAPI](https://dev.qweather.com/docs/api/geoapi/)
 *
 * 和风天气 GeoAPI 提供全球地理位位置、全球城市搜索服务，支持经纬度坐标反查、多语言、模糊搜索等功能。
 *
 * 天气数据是基于地理位置的数据，因此获取天气之前需要先知道具体的位置信息。和风天气提供一个功能强大
 * 的位置信息搜索 API 服务： GeoAPI 。通过 GeoAPI ，你可获取到需要查询城市或 POI 的基本信息，包
 * 括查询地区的 Location ID（你需要这个 ID 去查询天气），多语言名称、经纬度、时区、海拔、Rank 值、
 * 归属上级行政区域、所在行政区域等。
 *
 * 除此之外，GeoAPI还可以帮助你：
 * - 避免重名城市的困扰
 * - 支持名称模糊搜索
 * - 在你的应用或网站中根据用户输入的名称返回多个城市结果，便于用户选择准确的城市并返回该城市天气
 * - 在你的应用或网站中展示热门城市
 * - 不需要维护城市列表，城市信息更新实时获取
 */
class Geo internal constructor(override val client: QWeather) : Api {

    override val url: String
        get() = if (client.plan is QWeather.Plan.HostApi) "/geo/v2" else client.plan.geoHost

    /**
     * [城市搜索](https://dev.qweather.com/docs/api/geoapi/city-lookup/)
     *
     * 城市搜索API提供全球地理位位置、全球城市搜索服务，支持经纬度坐标反查、多语言、模糊搜索等功能。
     *
     * 天气数据是基于地理位置的数据，因此获取天气之前需要先知道具体的位置信息。使用城市搜索，可获取
     * 到该城市的基本信息，包括城市的Location ID（你需要这个ID去查询天气），多语言名称、经纬度、
     * 时区、海拔、Rank值、归属上级行政区域、所在行政区域等。
     *
     * 另外，城市搜索也可以帮助你在你的APP中实现模糊搜索，用户只需要输入1-2个字即可获得结果。
     *
     * @param location 需要查询地区的名称，支持文字 [com.qweather.vastgui.client.utils.Name] 、以英文逗号分隔的经度，纬度坐标
     * [Coordinate] 、[com.qweather.vastgui.client.utils.LocationID] 或 [com.qweather.vastgui.client.utils.Adcode]（仅限中国城市) 。
     * @param adm 城市的上级行政区划，可设定只在某个行政区划范围内进行搜索，用于排除重名城市
     * 或对结果进行过滤。例如 adm=beijing
     * @param range 搜索范围，可设定只在某个国家或地区范围内进行搜索，国家和地区名称需使用
     * [ISO 3166 所定义的国家代码][QWeather.CountryCode] 。如果不设置此参数，搜索范围将在
     * 所有城市。例如 range=cn 。
     * @param number 返回结果的数量，取值范围1-20，默认返回10个结果。
     * @param lang 多语言设置，请阅读 [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     * @throws IllegalStateException 当 [number] 未在 0-20 范围内会抛出该异常。
     */
    @Throws(IllegalStateException::class)
    suspend fun cityLookup(
        location: Location,
        adm: String? = null,
        range: QWeather.CountryCode = QWeather.CountryCode.CN,
        number: Int = 10,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<LookupGeo> = apiCatching {
        check(number in 1..20) { "无效的数量: $number, 可用的范围：1-20!" }
        client.httpClient.get("$url/city/lookup") {
            url {
                parameter("location", location.location)
                parameter("adm", adm)
                parameter("range", range)
                parameter("number", number)
                parameter("lang", lang)
            }
        }.body()
    }

    /**
     * [热门城市查询](https://dev.qweather.com/docs/api/geoapi/top-city/)
     *
     * 获取全球各国热门城市列表。
     *
     * @param range 搜索范围，可设定只在某个国家或地区范围内进行搜索，国家和地区名称需使用
     * [ISO 3166 所定义的国家代码][QWeather.CountryCode] 。如果不设置此参数，搜索范围将在
     * 所有城市。例如 range=cn 。
     * @param number 返回结果的数量，取值范围1-20，默认返回10个结果。
     * @param lang 多语言设置，请阅读 [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     * @throws IllegalStateException 当 [number] 未在 0-20 范围内会抛出该异常。
     */
    @Throws(IllegalStateException::class)
    suspend fun topCity(
        range: QWeather.CountryCode = QWeather.CountryCode.CN,
        number: Int = 10,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<TopGeo> = apiCatching {
        check(number in 1..20) { "无效的数量: $number, 可用的范围：1-20!" }
        client.httpClient.get("$url/city/top") {
            url {
                parameter("range", range)
                parameter("number", number)
                parameter("lang", lang)
            }
        }.body()
    }

    /**
     * [POI搜索](https://dev.qweather.com/docs/api/geoapi/poi-lookup/)
     *
     * 使用关键字和坐标查询 POI 信息（景点、火车站、飞机场、港口等）。
     *
     * @param location 需要查询地区的名称 [com.qweather.vastgui.client.utils.Name] ，支持文字、以英文逗号分隔的经度，纬度坐标
     * [Coordinate] 、[com.qweather.vastgui.client.utils.LocationID] 或 [com.qweather.vastgui.client.utils.Adcode]（仅限中国城市) 。
     * @param type POI类型，可选择搜索某一类型的 POI 。
     * @param city 选择 POI 所在城市，可设定只搜索在特定城市内的 POI 信息。城市名称可以是
     * [com.qweather.vastgui.client.utils.Name] 或城市的 [com.qweather.vastgui.client.utils.LocationID] 。默认不限制特定城市。
     * @param number 返回结果的数量，取值范围1-20，默认返回10个结果。
     * @param lang 多语言设置，请阅读 [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     * @throws IllegalStateException 当 [number] 未在 0-20 范围内会抛出该异常。
     */
    @Throws(IllegalStateException::class)
    suspend fun poiLookup(
        location: Location,
        type: QWeather.POIType,
        city: String? = null,
        number: Int = 10,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<POIGeo> = apiCatching {
        check(number in 1..20) { "无效的数量: $number, 可用的范围：1-20!" }
        client.httpClient.get("$url/poi/lookup") {
            url {
                parameter("location", location.location)
                parameter("type", type)
                parameter("city", city)
                parameter("number", number)
                parameter("lang", lang)
            }
        }.body()
    }

    /**
     * [POI范围搜索](https://dev.qweather.com/docs/api/geoapi/poi-range/)
     *
     * 提供指定区域范围内查询所有POI信息。
     *
     * @param location 需要查询地区的以英文逗号分隔的经度,纬度坐标 [Coordinate] 。
     * @param type POI类型，可选择搜索某一类型的 POI 。
     * @param radius 搜索范围，可设置搜索半径，取值范围 1-50 ，单位：公里。默认 5 公里。
     * @param number 返回结果的数量，取值范围 1-20 ，默认返回 10 个结果。
     * @param lang 多语言设置，请阅读 [多语言](https://dev.qweather.com/docs/resource/language/)
     * 文档，了解我们的多语言是如何工作、如何设置以及数据是否支持多语言。
     * @throws IllegalStateException 当 [number] 或 [radius] 未在规范范围内会抛出该异常。
     */
    @Throws(IllegalStateException::class)
    suspend fun poiRange(
        location: Coordinate,
        type: QWeather.POIType,
        radius: Int = 5,
        number: Int = 10,
        lang: QWeather.Lang = QWeather.Lang.ZH
    ): Result<POIRangeGeo> = apiCatching {
        check(number in 1..20) { "无效的数量: $number, 可用的范围：1-20!" }
        check(radius in 1..50) { "无效的数量: $radius, 可用的范围：1-50!" }
        client.httpClient.get("$url/poi/range") {
            parameter("location", location.location)
            parameter("type", type)
            parameter("radius", radius)
            parameter("number", number)
            parameter("lang", lang)
        }.body()
    }
}
