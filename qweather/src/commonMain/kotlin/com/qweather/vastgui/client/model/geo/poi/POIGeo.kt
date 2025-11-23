package com.qweather.vastgui.client.model.geo.poi

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [POI搜索](https://dev.qweather.com/docs/api/geoapi/poi-lookup/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property poi 参考 [POI] 。
 * @property refer 参考 [Refer] 。
 * @since 2.0.0
 */
@Serializable
data class POIGeo(
    val poi: List<POI> = emptyList(),
    val refer: Refer? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse