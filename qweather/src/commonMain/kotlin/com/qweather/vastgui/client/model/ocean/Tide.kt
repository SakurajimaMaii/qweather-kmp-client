package com.qweather.vastgui.client.model.ocean

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [潮汐](https://dev.qweather.com/docs/api/ocean/tide/)
 *
 * @property code
 * 请参考[状态码](https://dev.qweather.com/docs/resource/status-code/)。
 * @property updateTime
 * 当前[API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property tideHourly 参考 [TideHourly] 。
 * @property tideTable 参考 [TideTable] 。
 * @property refer 参考 [Refer] 。
 */
@Serializable
data class Tide(
    val fxLink: String? = null,
    val refer: Refer? = null,
    val tideHourly: List<TideHourly> = emptyList(),
    val tideTable: List<TideTable> = emptyList(),
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [潮汐](https://dev.qweather.com/docs/api/ocean/tide/)
     *
     * @property fxTime 逐小时预报时间。
     * @property height 海水高度，单位：米。对于一些地点，此数据可能为空。
     */
    @Serializable
    data class TideHourly(
        val fxTime: String? = null,
        val height: String? = null
    )

    /**
     * [潮汐](https://dev.qweather.com/docs/api/ocean/tide/)
     *
     * @property fxTime 满潮或干潮时间。
     * @property height 海水高度，单位：米。
     * @property type 满潮（H）或干潮（L）。
     */
    @Serializable
    data class TideTable(
        val fxTime: String? = null,
        val height: String? = null,
        val type: String? = null
    )
}