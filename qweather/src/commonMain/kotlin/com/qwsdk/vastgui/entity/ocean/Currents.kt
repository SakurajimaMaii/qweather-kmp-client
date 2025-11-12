package com.qwsdk.vastgui.entity.ocean

import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import com.qwsdk.vastgui.entity.Refer
import kotlinx.serialization.Serializable

/**
 * [潮流](https://dev.qweather.com/docs/api/ocean/currents/)
 *
 * @property code 请参考 [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property updateTime 当前 [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property currentsHourly 参考 [CurrentsHourly] 。
 * @property currentsTable 参考 [CurrentsTable] 。
 * @property refer 参考 [com.qwsdk.vastgui.entity.Refer] 。
 */
@Serializable
data class Currents(
    val currentsHourly: List<CurrentsHourly> = emptyList(),
    val currentsTable: List<CurrentsTable> = emptyList(),
    val fxLink: String? = null,
    val refer: Refer = Refer(),
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [潮流](https://dev.qweather.com/docs/api/ocean/currents/)
     *
     * @property dir360 潮流360度方向。
     * @property fxTime 逐小时预报时间。
     * @property speed 潮流流速，单位：厘米/秒。
     */
    @Serializable
    data class CurrentsHourly(
        val dir360: String,
        val fxTime: String,
        val speed: String
    )

    /**
     * [潮流](https://dev.qweather.com/docs/api/ocean/currents/)
     *
     * @property dir360 潮流360度方向。
     * @property fxTime 潮流最大流速时间。
     * @property speedMax 潮流最大流速，单位：厘米/秒。
     */
    @Serializable
    data class CurrentsTable(
        val dir360: String,
        val fxTime: String,
        val speedMax: String
    )
}