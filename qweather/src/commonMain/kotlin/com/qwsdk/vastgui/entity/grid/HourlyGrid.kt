package com.qwsdk.vastgui.entity.grid

import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import com.qwsdk.vastgui.entity.Refer
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

/**
 * [格点逐小时天气预报](https://dev.qweather.com/docs/api/weather/grid-weather-hourly-forecast/) 。
 *
 * @property code 请参考 [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property hourly 参考 [Hourly] 。
 * @property refer 参考 [com.qwsdk.vastgui.entity.Refer] 。
 * @property updateTime 当前 [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time) 。
 */
@Serializable
data class HourlyGrid(
    val fxLink: String? = null,
    val hourly: List<Hourly> = emptyList(),
    val refer: Refer = Refer(),
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [格点逐小时天气预报](https://dev.qweather.com/docs/api/grid-weather/grid-weather-hourly-forecast/)
     *
     * @property cloud 云量，百分比数值。可能为空。
     * @property dew 露点温度。可能为空。
     * @property fxTime 预报时间。
     * @property humidity 相对湿度，百分比数值。
     * @property icon 天气状况的 [图标代码](https://dev.qweather.com/docs/resource/icons/) ，
     * 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property precip 当前小时累计降水量，默认单位：毫米。
     * @property pressure 大气压强，默认单位：百帕。
     * @property temp 温度，默认单位：摄氏度。
     * @property text 天气状况的文字描述，包括阴晴雨雪等天气状态的描述。
     * @property wind360 [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 360角度。
     * @property windDir [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 。
     * @property windScale [风力等级](https://dev.qweather.com/docs/resource/wind-info/#wind-scale) 。
     * @property windSpeed [风速](https://dev.qweather.com/docs/resource/wind-info/#wind-speed) ，公里/小时。
     */
    @OptIn(ExperimentalSerializationApi::class)
    @Serializable
    data class Hourly(
        @EncodeDefault
        val cloud: String? = null,
        @EncodeDefault
        val dew: String? = null,
        val fxTime: String,
        val humidity: String,
        val icon: String,
        val precip: String,
        val pressure: String,
        val temp: String,
        val text: String,
        val wind360: String,
        val windDir: String,
        val windScale: String,
        val windSpeed: String
    )
}