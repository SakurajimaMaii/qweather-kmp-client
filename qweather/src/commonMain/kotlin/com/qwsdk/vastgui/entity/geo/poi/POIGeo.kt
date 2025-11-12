package com.qwsdk.vastgui.entity.geo.poi

import com.qwsdk.vastgui.entity.base.BaseResponse
import com.qwsdk.vastgui.entity.base.error.ErrorInfo
import com.qwsdk.vastgui.entity.base.Refer
import kotlinx.serialization.Serializable

/**
 * [POI搜索](https://dev.qweather.com/docs/api/geoapi/poi-lookup/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property poi 参考 [POI] 。
 * @property refer 参考 [Refer] 。
 * @since 1.1.3
 */
@Serializable
data class POIGeo(
    val poi: List<POI> = emptyList(),
    val refer: Refer? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse