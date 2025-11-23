package com.qweather.vastgui.client.model.tropical

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import com.qweather.vastgui.client.utils.StormId
import kotlinx.serialization.Serializable

/**
 * [台风列表](https://dev.qweather.com/docs/api/tropical-cyclone/storm-list/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property refer 参考 [Refer] 。
 * @property storm 参考 [Storm] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 */
@Serializable
data class StormList(
    val fxLink: String? = null,
    val refer: Refer? = null,
    val storm: List<Storm> = emptyList(),
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [台风列表](https://dev.qweather.com/docs/api/tropical-cyclone/storm-list/)
     *
     * @property isActive 是否为活跃台风。1 活跃台风，0 停编。
     * @property basin 台风所处流域。
     * @property id 台风 ID 。
     * @property name 台风名称。
     * @property year 台风所处年份。
     */
    @Serializable
    data class Storm(
        val isActive: String? = null,
        val basin: String? = null,
        val id: String? = null,
        val name: String? = null,
        val year: String? = null
    ) {
        /**
         * 获取需要查询的台风 ID 。
         *
         * @since 2.0.0
         */
        fun getStormId(): StormId? = id?.let(::StormId)
    }
}