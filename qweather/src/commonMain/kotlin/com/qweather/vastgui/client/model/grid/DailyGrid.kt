package com.qweather.vastgui.client.model.grid

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [格点每日天气预报](https://dev.qweather.com/docs/api/weather/grid-weather-daily-forecast/)
 * 。
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property daily 参考 [Daily] 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [Refer] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 * @since 2.0.0
 */
@Serializable
data class DailyGrid(
    val daily: List<Daily> = emptyList(),
    val fxLink: String? = null,
    val refer: Refer? = null,
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [格点每日天气预报](https://dev.qweather.com/docs/api/grid-weather/grid-weather-daily-forecast/)
     *
     * @property cloud 云量，百分比数值。可能为空。
     * @property fxDate 当前数据的响应式页面，便于嵌入网站或应用。
     * @property humidity 相对湿度，百分比数值。
     * @property iconDay 预报白天天气状况的
     * [图标代码](https://dev.qweather.com/docs/resource/icons/)
     * ， 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property iconNight 预报夜间天气状况的
     * [图标代码](https://dev.qweather.com/docs/resource/icons/)
     * ， 另请参考 [天气图标项目](https://icons.qweather.com/) 。
     * @property precip 预报当天总降水量，默认单位：毫米。
     * @property pressure 大气压强，默认单位：百帕。
     * @property tempMax 预报当天最高温度。
     * @property tempMin 预报当天最低温度。
     * @property textDay 预报白天天气状况文字描述，包括阴晴雨雪等天气状态的描述。
     * @property textNight 预报晚间天气状况文字描述，包括阴晴雨雪等天气状态的描述。
     * @property wind360Day 预报白天
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction)
     * 360角度。
     * @property wind360Night 预报夜间
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction)
     * 360角度。
     * @property windDirDay 预报白天
     * [风向](https://dev.qweather.com/docs/resource/wind-info/#wind-direction) 。
     * @property windDirNight 预报夜间
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
     * @since 2.0.0
     */
    @Serializable
    data class Daily(
        val cloud: String? = null,
        val fxDate: String? = null,
        val humidity: String? = null,
        val iconDay: String? = null,
        val iconNight: String? = null,
        val precip: String? = null,
        val pressure: String? = null,
        val tempMax: String? = null,
        val tempMin: String? = null,
        val textDay: String? = null,
        val textNight: String? = null,
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