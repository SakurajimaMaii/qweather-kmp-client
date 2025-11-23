/*
 * Copyright 2025 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qweather.vastgui.client.model.geo.poi

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [POI范围搜索](https://dev.qweather.com/docs/api/geoapi/poi-range/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property poi 参考 [POIGeo] 。
 * @property refer 参考 [Refer] 。
 * @since 2.0.0
 */
@Serializable
data class POIRangeGeo(
    val poi: List<POI> = emptyList(),
    val refer: Refer? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse