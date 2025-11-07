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

package com.qwsdk.vastgui

import com.qwsdk.vastgui.QWeather.BasinType.AL
import com.qwsdk.vastgui.QWeather.BasinType.EP
import com.qwsdk.vastgui.QWeather.BasinType.NI
import com.qwsdk.vastgui.QWeather.BasinType.NP
import com.qwsdk.vastgui.QWeather.BasinType.SI
import com.qwsdk.vastgui.QWeather.BasinType.SP
import com.qwsdk.vastgui.QWeather.IndicesType.AIR_CONDITIONER
import com.qwsdk.vastgui.QWeather.IndicesType.AIR_POLLUTION_DIFFUSION_CONDITION
import com.qwsdk.vastgui.QWeather.IndicesType.ALL
import com.qwsdk.vastgui.QWeather.IndicesType.CLOTHING
import com.qwsdk.vastgui.QWeather.IndicesType.COLD
import com.qwsdk.vastgui.QWeather.IndicesType.COMFORT
import com.qwsdk.vastgui.QWeather.IndicesType.DRYING
import com.qwsdk.vastgui.QWeather.IndicesType.FISHING
import com.qwsdk.vastgui.QWeather.IndicesType.MAKEUP
import com.qwsdk.vastgui.QWeather.IndicesType.POLLEN_ALLERGY
import com.qwsdk.vastgui.QWeather.IndicesType.SPF
import com.qwsdk.vastgui.QWeather.IndicesType.SPORT
import com.qwsdk.vastgui.QWeather.IndicesType.SUNGLASSES
import com.qwsdk.vastgui.QWeather.IndicesType.TRAFFIC
import com.qwsdk.vastgui.QWeather.IndicesType.TRAVEL
import com.qwsdk.vastgui.QWeather.IndicesType.UV_RAY
import com.qwsdk.vastgui.QWeather.IndicesType.WASH_CAR
import com.qwsdk.vastgui.QWeather.POIType.CSTA
import com.qwsdk.vastgui.QWeather.POIType.TSTA
import com.qwsdk.vastgui.QWeather.POIType.scenic
import com.qwsdk.vastgui.api.Air
import com.qwsdk.vastgui.api.AirQuality
import com.qwsdk.vastgui.api.Astronomy
import com.qwsdk.vastgui.api.Geo
import com.qwsdk.vastgui.api.Grid
import com.qwsdk.vastgui.api.Indices
import com.qwsdk.vastgui.api.Minutely
import com.qwsdk.vastgui.api.Ocean
import com.qwsdk.vastgui.api.SolarRadiation
import com.qwsdk.vastgui.api.TimeMachine
import com.qwsdk.vastgui.api.Tropical
import com.qwsdk.vastgui.api.Warning
import com.qwsdk.vastgui.api.Weather
import com.qwsdk.vastgui.utils.SingletonHolder
import com.qwsdk.vastgui.error.InvalidDateException
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * [QWeather](https://dev.qweather.com/docs/api/)
 *
 * 和风天气开发服务提供了基于位置的天气数据，包括实况天气、30天预报、 逐小时预报、空气质量AQI，灾害预警、分钟级降水、生活指数等天气数据服务。
 * - [GeoAPI][Geo] 和风天气 GeoAPI 提供全球地理位位置、全球城市搜索服务， 支持经纬度坐标反查、多语言、模糊搜索等功能。
 * - [城市天气][Weather] 城市天气预报提供包括中国 3000+ 市县区在内的全球 20 万+城市的天气预报，支持实时天气、最多 30
 *   天预报及最多 7 天逐小时天气预报。
 * - [分钟预报][Minutely] 分钟级降水 API （临近预报）支持中国 1 公里精度的分钟
 *   级降雨预报数据，为每一分钟的降雨进行精准预测。
 * - [格点天气][Grid] 以经纬度为基准的全球高精度、公里级、格点化天气预报产品， 包括任意经纬度的实时天气和天气预报。
 * - [预警][Warning] 和风天气灾害预警API提供了全球极端天气预警服务，覆盖中国 及全球数十个国家或地区。
 * - [天气指数][Indices] 天气生活指数包括洗车指数、穿衣指数、感冒指数、过敏指数、 紫外线指数、钓鱼指数等数据。天气指数支持中国
 *   3000+ 个市县区和海外 15 万个城市 天气预报。
 * - [空气质量][AirQuality] 全球空气质量 API ，适配当地空气质量标准，可以轻松的获取指定位置的空气质量、
 *   污染物和健康建议。目前已经覆盖 100 多个国家或地区数据，包括实时和预报数据，分辨率为 1x1 公里。
 * - [时光机][TimeMachine] 时光机可以获取最近 10 天的历史天气和空气质量数据。
 * - [热带气旋（台风）][Tropical] 热带气旋（台风）API 提供全球主要海洋流域的
 *   台风信息，包括台风实时位置、等级、气压、风速，还可查询台风路径和台风预报信息。
 * - [海洋数据][Ocean] 海洋数据API提供全球主要港口和城市的潮汐和潮流数据。
 * - [太阳辐射][SolarRadiation] 太阳辐射 API 支持获取全球任意坐标的辐射数据，
 *   包括净太阳辐射，太阳散射辐射和太阳直接辐射。
 * - [天文][Astronomy] 天文API提供了全球任意地点未来 60 天的日出日落、太阳 高度角、月升月落和月相数据。
 *
 * ```kotlin
 * // 获取单例对象
 * val qw = QWeather.getInstance(Configuration(Plan.Standard, "<Your-Key>"))
 * ```
 */
@OptIn(ExperimentalSerializationApi::class)
class QWeather private constructor(internal val configuration: Configuration) {

    /**
     * [QWeather] 配置。
     *
     * @property plan 订阅计划。
     * @property apiKey Key，点击
     * [项目和KEY](https://dev.qweather.com/docs/configuration/project-and-key/)
     * 了解详情。
     * @property logger 允许你对日志进行处理。
     */
    class Configuration(
        internal val plan: Plan,
        internal val apiKey: String,
        /** @since 1.1.2 */
        internal val logger: ((String) -> Unit)? = null
    )

    companion object Companion : SingletonHolder<QWeather, Configuration>(::QWeather)

    @OptIn(ExperimentalTime::class)
    @get:Throws(InvalidDateException::class)
    internal val apiPlan: Plan
        get() {
            // https://blog.qweather.com/announce/public-api-domain-change-to-api-host/
            val limit = LocalDate(2026, 6, 1).atStartOfDayIn(TimeZone.UTC)
            if (Clock.System.now() >= limit && configuration.plan !is Plan.HostApi)
                throw InvalidDateException("相关 api 已经停止服务，具体参考 https://blog.qweather.com/announce/public-api-domain-change-to-api-host/")
            return configuration.plan
        }

    internal val apiKey: String = configuration.apiKey

    internal val httpClient: HttpClient = HttpClient {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = configuration.plan.host
                path("v7/")
                parameters.append("key", apiKey)
            }
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    configuration.logger?.invoke(message)
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
            })
        }
    }

    /**
     * 可以考虑使用 [AirQuality] 进行替换。
     *
     * @see Air
     * @see AirQuality
     */
    @Deprecated(
        message = "2025年10月1日起，新注册的开发者将无法使用弃用版本空气质量 API，2026年6月1日起，弃用版本空气质量 API 将对所有开发者关闭并停止服务，所有开发者将无法再访问弃用版本获取数据，包括使用API、SDK",
        level = DeprecationLevel.WARNING
    )
    fun air(): Air = Air(this)

    /** @see AirQuality */
    @Deprecated(
        message = "使用 airQuality() 替代",
        replaceWith = ReplaceWith("airQuality()"),
        level = DeprecationLevel.WARNING
    )
    fun airBeta(): AirQuality = AirQuality(this)

    /**
     * @see AirQuality
     * @since 1.1.3
     */
    fun airQuality(): AirQuality = AirQuality(this)

    /** @see Astronomy */
    fun astronomy(): Astronomy = Astronomy(this)

    /** @see Geo */
    fun geo(): Geo = Geo(this)

    /** @see Grid */
    fun grid(): Grid = Grid(this)

    /** @see Indices */
    fun indices(): Indices = Indices(this)

    /** @see Minutely */
    fun minutely(): Minutely = Minutely(this)

    /** @see Ocean */
    fun ocean(): Ocean = Ocean(this)

    /** @see SolarRadiation */
    fun solarRadiation(): SolarRadiation = SolarRadiation(this)

    /** @see TimeMachine */
    fun timeMachine(): TimeMachine = TimeMachine(this)

    /** @see Tropical */
    fun tropical(): Tropical = Tropical(this)

    /** @see Warning */
    fun warning(): Warning = Warning(this)

    /** @see Weather */
    fun weather(): Weather = Weather(this)

    /**
     * 需要查询的台风所在的流域，例如中国处于西北太平洋，即 basin=NP。当前仅支持 NP
     *
     * @property AL North Atlantic 北大西洋
     * @property EP Eastern Pacific 东太平洋
     * @property NP NorthWest Pacific 西北太平洋
     * @property SP SouthWestern Pacific 西南太平洋
     * @property NI North Indian 北印度洋
     * @property SI South Indian 南印度洋
     */
    enum class BasinType {
        NP, AL, EP, SP, NI, SI
    }

    /**
     * [ISO 3166
     * 国际标准化组织（ISO）发布的全球国家代码标准](https://github.com/qwd/LocationList/blob/master/iso3166.csv)
     */
    enum class CountryCode {
        AF, AX, AL, DZ, AS, AD, AO, AI, AQ, AG,
        AR, AM, AW, AU, AT, AZ, BS, BH, BD, BB,
        BY, BE, BZ, BJ, BM, BT, BO, BQ, BA, BW,
        BV, BR, IO, BN, BG, BF, BI, CV, KH, CM,
        CA, KY, CF, TD, CL, CN, CX, CC, CO, KM,
        CG, CD, CK, CR, CI, HR, CU, CW, CY, CZ,
        DK, DJ, DM, DO, EC, EG, SV, GQ, ER, EE,
        ET, FK, FO, FJ, FI, FR, GF, PF, TF, GA,
        GM, GE, DE, GH, GI, GR, GL, GD, GP, GU,
        GT, GG, GN, GW, GY, HT, HM, VA, HN, HK,
        HU, IS, IN, ID, IR, IQ, IE, IM, IL, IT,
        JM, JP, JE, JO, KZ, KE, KI, KP, KR, KW,
        KG, LA, LV, LB, LS, LR, LY, LI, LT, LU,
        MO, MK, MG, MW, MY, MV, ML, MT, MH, MQ,
        MR, MU, YT, MX, FM, MD, MC, MN, ME, MS,
        MA, MZ, MM, NA, NR, NP, NL, NC, NZ, NI,
        NE, NG, NU, NF, MP, NO, OM, PK, PW, PS,
        PA, PG, PY, PE, PH, PN, PL, PT, PR, QA,
        RE, RO, RU, RW, BL, SH, KN, LC, MF, PM,
        VC, WS, SM, ST, SA, SN, RS, SC, SL, SG,
        SX, SK, SI, SB, SO, ZA, GS, SS, ES, LK,
        SD, SR, SJ, SZ, SE, CH, SY, TW, TJ, TZ,
        TH, TL, TG, TK, TO, TT, TN, TR, TM, TC,
        TV, UG, UA, AE, GB, US, UM, UY, UZ, VU,
        VE, VN, VG, VI, WF, EH, YE, ZM, ZW;

        override fun toString(): String {
            return name.lowercase()
        }
    }

    /**
     * [天气指数类型](https://dev.qweather.com/docs/resource/indices-info/)
     *
     * @property ALL 全部天气指数
     * @property SPORT 运动指数
     * @property WASH_CAR 洗车指数
     * @property CLOTHING 穿衣指数
     * @property FISHING 钓鱼指数
     * @property UV_RAY 紫外线指数
     * @property TRAVEL 旅游指数
     * @property POLLEN_ALLERGY 花粉过敏指数
     * @property COMFORT 舒适度指数
     * @property COLD 感冒指数
     * @property AIR_POLLUTION_DIFFUSION_CONDITION 空气污染扩散条件指数
     * @property AIR_CONDITIONER 空调开启指数
     * @property SUNGLASSES 太阳镜指数
     * @property MAKEUP 化妆指数
     * @property DRYING 晾晒指数
     * @property TRAFFIC 交通指数
     * @property SPF 防晒指数
     */
    enum class IndicesType {
        ALL, SPORT, WASH_CAR, CLOTHING, FISHING,
        UV_RAY, TRAVEL, POLLEN_ALLERGY, COMFORT,
        COLD, AIR_POLLUTION_DIFFUSION_CONDITION,
        AIR_CONDITIONER, SUNGLASSES, MAKEUP, DRYING,
        TRAFFIC, SPF
    }

    /** [多语言代码](https://dev.qweather.com/docs/resource/language/#language-code) */
    enum class Lang {
        ZH, ZH_HANT, EN, DE, ES, FR, IT,
        JA, KO, RU, HI, TH, AR, PT, BN,
        MS, NL, EL, LA, SV, ID, PL, TR,
        CS, ET, VI, FIL, FI, HE, IS, NB;

        override fun toString(): String {
            return name.lowercase()
        }
    }

    /**
     * [订阅](https://dev.qweather.com/docs/finance/subscription) 。
     *
     * @since 1.1.3
     */
    sealed class Plan(internal val host: String) {

        /**
         * 用于 [Geo] 的请求根路径。
         *
         * @since 1.1.3
         */
        open val geoHost: String
            get() = throw NotImplementedError()

        /** @since 1.1.3 */
        @Deprecated(
            message = "devapi.qweather.com 该域名地址将于2026年6月1日停止服务",
            replaceWith = ReplaceWith("HostApi(your_hostapi)"),
            level = DeprecationLevel.WARNING
        )
        object Free : Plan("devapi.qweather.com") {
            override val geoHost: String = "https://geoapi.qweather.com/v2"
        }

        /** @since 1.1.3 */
        @Deprecated(
            message = "api.qweather.com 该域名地址将于2026年6月1日停止服务",
            replaceWith = ReplaceWith("HostApi(your_hostapi)"),
            level = DeprecationLevel.WARNING
        )
        object Standard : Plan("api.qweather.com") {
            override val geoHost: String = "https://geoapi.qweather.com/v2"
        }

        /**
         * 根据
         * [公共API域名变更为API Host](https://blog.qweather.com/announce/public-api-domain-change-to-api-host/)
         * ， 推荐你将 [Free] 和 [Standard] 更换为 [HostApi]。
         *
         * @param host 参考
         * [API Host](https://dev.qweather.com/docs/configuration/api-host/) 来获取。
         * @since 1.1.3
         */
        class HostApi(host: String) : Plan(host) {
            override val geoHost: String = "https://${host}/geo/v2"
        }

        /**
         * 判断是否是标准版。
         *
         * @since 1.1.2
         */
        internal fun isStandard() = this !is Free

        internal fun isFree() = this !is Standard
    }

    /**
     * POI类型
     *
     * @property scenic 景点
     * @property CSTA 潮流站点
     * @property TSTA 潮汐站点
     */
    enum class POIType {
        scenic, CSTA, TSTA;

        override fun toString(): String = name
    }

    /** [度量衡单位](https://dev.qweather.com/docs/resource/unit) */
    enum class Units {
        M, I;

        override fun toString(): String {
            return name.lowercase()
        }
    }
}