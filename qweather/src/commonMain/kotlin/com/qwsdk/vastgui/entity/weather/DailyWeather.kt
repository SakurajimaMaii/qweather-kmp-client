package com.qwsdk.vastgui.entity.weather

import com.qwsdk.vastgui.entity.base.BaseResponse
import com.qwsdk.vastgui.entity.base.error.ErrorInfo
import com.qwsdk.vastgui.entity.base.Refer
import kotlinx.serialization.Serializable

/**
 * [每日天气预报](https://dev.qweather.com/docs/api/weather/weather-daily-forecast/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property daily 参考 [Daily] 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [Refer] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 * @since 1.1.3
 */
@Serializable
data class DailyWeather(
    val daily: List<Daily> = emptyList(),
    val fxLink: String? = null,
    val refer: Refer? = null,
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [每日天气预报](https://dev.qweather.com/docs/api/weather/weather-daily-forecast/)
     *
     * @property cloud 云量，百分比数值。可能为空。
     * @property fxDate 预报日期。
     * @property humidity 相对湿度，百分比数值。
     * @property iconDay 预报白天天气状况的
     * [图标代码](https://dev.qweather.com/docs/resource/icons/)
     * ， 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property iconNight 预报夜间天气状况的
     * [图标代码](https://dev.qweather.com/docs/resource/icons/)
     * ， 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property moonPhase
     * [月相名称](https://dev.qweather.com/docs/resource/sun-moon-info/#moon-phase)
     * 。
     * @property moonPhaseIcon 月相
     * [图标代码](https://dev.qweather.com/docs/resource/icons/)
     * ，另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property moonrise 当天
     * [月升时间](https://dev.qweather.com/docs/resource/sun-moon-info/#moonrise-and-moonset)
     * ， 可能为空。
     * @property moonset 当天
     * [月落时间](https://dev.qweather.com/docs/resource/sun-moon-info/#moonrise-and-moonset)
     * ， 可能为空。
     * @property pressure 大气压强，默认单位：百帕。
     * @property sunrise
     * [日出时间](https://dev.qweather.com/docs/resource/sun-moon-info/#sunrise-and-sunset)
     * ， 在高纬度地区可能为空。
     * @property sunset
     * [日落时间](https://dev.qweather.com/docs/resource/sun-moon-info/#sunrise-and-sunset)
     * ， 在高纬度地区可能为空。
     * @property tempMax 预报当天最高温度。
     * @property tempMin 预报当天最低温度。
     * @property textDay 预报白天天气状况文字描述，包括阴晴雨雪等天气状态的描述。
     * @property textNight 预报晚间天气状况文字描述，包括阴晴雨雪等天气状态的描述
     * @property uvIndex 紫外线强度指数。
     * @property vis 能见度，默认单位：公里。
     * @property wind360Day 预报白天
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction)
     * 360角度。
     * @property wind360Night 预报夜间
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction)
     * 360角度。
     * @property windDirDay 预报白天
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 。
     * @property windDirNight 预报夜间当天
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 。
     * @property windScaleDay 预报白天
     * [风力等级](https://dev.qweather.com/docs/resource/wind-info/#wind-scale) 。
     * @property windScaleNight 预报夜间
     * [风力等级](https://dev.qweather.com/docs/resource/wind-info/#wind-scale) 。
     * @property windSpeedDay 预报白天
     * [风速](https://dev.qweather.com/docs/resource/wind-info/#wind-speed)
     * ，公里/小时。
     * @property windSpeedNight 预报夜间
     * [风速](https://dev.qweather.com/docs/resource/wind-info/#wind-speed)
     * ，公里/小时。
     * @since 1.1.3
     */
    @Serializable
    data class Daily(
        val cloud: String? = null,
        val fxDate: String? = null,
        val humidity: String? = null,
        val iconDay: String? = null,
        val iconNight: String? = null,
        val moonPhase: String? = null,
        val moonPhaseIcon: String? = null,
        val moonrise: String? = null,
        val moonset: String? = null,
        val precip: String? = null,
        val pressure: String? = null,
        val sunrise: String? = null,
        val sunset: String? = null,
        val tempMax: String? = null,
        val tempMin: String? = null,
        val textDay: String? = null,
        val textNight: String? = null,
        val uvIndex: String? = null,
        val vis: String? = null,
        val wind360Day: String? = null,
        val wind360Night: String? = null,
        val windDirDay: String? = null,
        val windDirNight: String? = null,
        val windScaleDay: String? = null,
        val windScaleNight: String? = null,
        val windSpeedDay: String? = null,
        val windSpeedNight: String? = null
    )
}