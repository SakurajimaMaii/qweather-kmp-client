package com.qweather.vastgui.client.model.air

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [空气质量每日预报](https://dev.qweather.com/docs/api/air-quality/webapi-v7-air-daily-forecast/) 。
 *
 * @property code 请参考 [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property daily 参考 [Daily] 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [Refer] 。
 * @property updateTime 当前 [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time) 。
 */
@Serializable
data class DailyAir(
    val daily: List<Daily> = emptyList(),
    val fxLink: String? = null,
    val refer: Refer? = null,
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [空气质量每日预报](https://dev.qweather.com/docs/api/air/air-daily-forecast/)
     *
     * @property aqi 空气质量指数。
     * @property category 空气质量指数级别。
     * @property fxDate 预报日期。
     * @property level 空气质量指数等级。
     * @property primary 空气质量的主要污染物，空气质量为优时，返回值为 NA 。
     */
    @Serializable
    data class Daily(
        val aqi: String? = null,
        val category: String? = null,
        val fxDate: String? = null,
        val level: String? = null,
        val primary: String? = null
    )
}