package com.qwsdk.vastgui.entity.historical

import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import com.qwsdk.vastgui.entity.Refer
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

/**
 * [天气时光机](https://dev.qweather.com/docs/api/time-machine/time-machine-weather/)
 *
 * @property code 请参考 [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [com.qwsdk.vastgui.entity.Refer] 。
 * @property weatherDaily 参考 [weatherDaily] 。
 * @property weatherHourly 参考 [WeatherHourly] 。
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class HistoricalWeather(
    val fxLink: String? = null,
    val refer: Refer? = null,
    val weatherDaily: WeatherDaily? = null,
    val weatherHourly: List<WeatherHourly> = emptyList(),
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [天气时光机](https://dev.qweather.com/docs/api/time-machine/time-machine-weather/)
     *
     * @property date 当天日期。
     * @property humidity 当天每小时相对湿度，百分比数值。
     * @property moonPhase 当天 [月相名称](https://dev.qweather.com/docs/resource/sun-moon-info/#moon-phase) 。
     * @property moonrise 当天 [月升时间](https://dev.qweather.com/docs/resource/sun-moon-info/#moonrise-and-moonset) ，可能为空。
     * @property moonset 当天 [月落时间](https://dev.qweather.com/docs/resource/sun-moon-info/#moonrise-and-moonset) ，可能为空
     * @property precip 当天总降水量，默认单位：毫米。
     * @property pressure 大气压强，默认单位：百帕。
     * @property sunrise 当天 [日出时间](https://dev.qweather.com/docs/api/time-machine/time-machine-weather/) ，在高纬度地区可能为空。
     * @property sunset 当天 [日落时间](https://dev.qweather.com/docs/api/time-machine/time-machine-weather/) ，在高纬度地区可能为空。
     * @property tempMax 当天最高温度。
     * @property tempMin 当天最低温度。
     */
    @Serializable
    data class WeatherDaily(
        val date: String? = null,
        val humidity: String? = null,
        val moonPhase: String? = null,
        val moonrise: String? = null,
        val moonset: String? = null,
        val precip: String? = null,
        val pressure: String? = null,
        val sunrise: String? = null,
        val sunset: String? = null,
        val tempMax: String? = null,
        val tempMin: String? = null
    )

    /**
     * [天气时光机](https://dev.qweather.com/docs/api/time-machine/time-machine-weather/)
     *
     * @property humidity 当天每小时相对湿度，百分比数值。
     * @property icon 当天每小时天气状况的 [图标代码](https://dev.qweather.com/docs/resource/icons/) ，
     * 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property precip 当天总降水量，默认单位：毫米。
     * @property pressure 大气压强，默认单位：百帕。
     * @property temp 当天每小时温度，默认单位：摄氏度。
     * @property text 当天每小时天气状况的文字描述，包括阴晴雨雪等天气状态的描述。
     * @property time 当天时间。
     * @property wind360 当天每小时 [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 360 角度。
     * @property windDir 当天每小时 [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 。
     * @property windScale 当天每小时 [风力等级](https://dev.qweather.com/docs/api/time-machine/time-machine-weather/) 。
     * @property windSpeed 当天每小时 [风速](https://dev.qweather.com/docs/resource/wind-info/#wind-speed) ，公里/小时。
     */
    @Serializable
    data class WeatherHourly(
        val humidity: String? = null,
        val icon: String? = null,
        val precip: String? = null,
        val pressure: String? = null,
        val temp: String? = null,
        val text: String? = null,
        val time: String? = null,
        val wind360: String? = null,
        val windDir: String? = null,
        val windScale: String? = null,
        val windSpeed: String? = null
    )
}