package com.qwsdk.vastgui.entity.tropical

import com.qwsdk.vastgui.entity.base.BaseResponse
import com.qwsdk.vastgui.entity.base.error.ErrorInfo
import com.qwsdk.vastgui.entity.base.Refer
import kotlinx.serialization.Serializable

/**
 * [台风预报](https://dev.qweather.com/docs/api/tropical-cyclone/storm-forecast/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property forecast 参考 [Forecast] 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [Refer] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 */
@Serializable
data class StormForecast(
    val forecast: List<Forecast> = emptyList(),
    val fxLink: String? = null,
    val refer: Refer? = null,
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [台风预报](https://dev.qweather.com/docs/api/tropical-cyclone/storm-forecast/)
     *
     * @property fxTime 台风预报时间
     * @property lat 台风所处纬度。
     * @property lon 台风所处经度。
     * @property move360 台风移动方位360度方向。
     * @property moveDir 台风移动方位。
     * @property moveSpeed 台风移动速度。
     * @property pressure 台风中心气压。
     * @property type 台风类型。
     * @property windSpeed 台风附近最大风速。
     */
    @Serializable
    data class Forecast(
        val fxTime: String? = null,
        val lat: String? = null,
        val lon: String? = null,
        val move360: String? = null,
        val moveDir: String? = null,
        val moveSpeed: String? = null,
        val pressure: String? = null,
        val type: String? = null,
        val windSpeed: String? = null
    )
}