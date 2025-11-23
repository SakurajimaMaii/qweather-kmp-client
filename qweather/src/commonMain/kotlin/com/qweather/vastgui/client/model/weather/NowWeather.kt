package com.qweather.vastgui.client.model.weather

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [实时天气](https://dev.qweather.com/docs/api/weather/weather-now/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property now 参考 [Now] 。
 * @property refer 参考 [Refer] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 * @since 2.0.0
 */
@Serializable
data class NowWeather(
    val fxLink: String? = null,
    val now: Now? = null,
    val refer: Refer? = null,
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [实时天气](https://dev.qweather.com/docs/api/weather/weather-now/)
     *
     * @property cloud 云量，百分比数值。可能为空。
     * @property dew 露点温度。可能为空。
     * @property feelsLike 体感温度，默认单位：摄氏度。
     * @property humidity 相对湿度，百分比数值。
     * @property icon 天气状况的
     * [图标代码](https://dev.qweather.com/docs/resource/icons/)
     * ， 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property obsTime 数据观测时间。
     * @property precip 当前小时累计降水量，默认单位：毫米。
     * @property pressure 大气压强，默认单位：百帕。
     * @property temp 温度，默认单位：摄氏度。
     * @property text 天气状况的文字描述，包括阴晴雨雪等天气状态的描述。
     * @property vis 能见度，默认单位：公里。
     * @property wind360
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction)
     * 360 角度。
     * @property windDir
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 。
     * @property windScale
     * [风力等级](https://dev.qweather.com/docs/resource/wind-info/#wind-scale) 。
     * @property windSpeed
     * [风速](https://dev.qweather.com/docs/resource/wind-info/#wind-speed)
     * ，公里/小时。
     * @since 2.0.0
     */
    @Serializable
    data class Now(
        val cloud: String? = null,
        val dew: String? = null,
        val feelsLike: String? = null,
        val humidity: String? = null,
        val icon: String? = null,
        val obsTime: String? = null,
        val precip: String? = null,
        val pressure: String? = null,
        val temp: String? = null,
        val text: String? = null,
        val vis: String? = null,
        val wind360: String? = null,
        val windDir: String? = null,
        val windScale: String? = null,
        val windSpeed: String? = null
    )
}