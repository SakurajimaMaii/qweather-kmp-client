package com.qwsdk.vastgui.entity.air

import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import com.qwsdk.vastgui.entity.Refer
import kotlinx.serialization.Serializable

/**
 * [实时空气质量](https://dev.qweather.com/docs/api/air-quality/webapi-v7-air-now/) 。
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property now 参考 [Now] 。
 * @property refer 参考 [com.qwsdk.vastgui.entity.Refer] 。
 * @property station 参考 [Station] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 */
@Serializable
data class NowAir(
    val fxLink: String? = null,
    val now: Now? = null,
    val refer: Refer? = null,
    val station: List<Station> = emptyList(),
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [实时空气质量](https://dev.qweather.com/docs/api/air/air-now/)
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
    data class Now(
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

    /**
     * [实时空气质量](https://dev.qweather.com/docs/api/air/air-now/)
     *
     * @property aqi 空气质量指数。
     * @property category 空气质量指数级别。
     * @property co 一氧化碳。
     * @property id 监测站 ID 。
     * @property level 空气质量指数等级。
     * @property name 监测站名称。
     * @property no2 二氧化氮。
     * @property o3 臭氧。
     * @property pm10 PM10 。
     * @property pm2p5 PM2.5 。
     * @property primary 空气质量的主要污染物，空气质量为优时，返回值为 NA 。
     * @property pubTime 空气质量数据发布时间。
     * @property so2 二氧化硫。
     */
    @Serializable
    data class Station(
        val aqi: String? = null,
        val category: String? = null,
        val co: String? = null,
        val id: String? = null,
        val level: String? = null,
        val name: String? = null,
        val no2: String? = null,
        val o3: String? = null,
        val pm10: String? = null,
        val pm2p5: String? = null,
        val primary: String? = null,
        val pubTime: String? = null,
        val so2: String? = null
    )
}