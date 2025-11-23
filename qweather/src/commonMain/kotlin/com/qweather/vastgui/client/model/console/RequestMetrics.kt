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

package com.qweather.vastgui.client.model.console

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11

/**
 * [请求量统计](https://dev.qweather.com/docs/api/console/stats/#request-example)
 *
 * @property asOf 当前数据的截止日期。
 * @property errors 成功失败的 API 列表
 * @property metadata 元信息。
 * @property success 成功请求的 API 列表。
 * @since 2.0.0
 */
@Serializable
data class RequestMetrics(
    val asOf: String? = null,
    val errors: List<Error>? = null,
    val metadata: Metadata? = null,
    val success: List<Success>? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * 错误请求的 API 。
     *
     * @property api 错误请求的 API 名称。
     * @property hours 最近24小时每小时的错误请求量，结束时间以 [asOf] 为准。例如
     * [asOf]=2025-03-20T09:59Z，则数组中的最后一条数据代表 09:00～09:59（UTC）的请求量。
     * @since 2.0.0
     */
    @Serializable
    data class Error(val api: String? = null, val hours: List<Int> = emptyList())

    /** @since 2.0.0 */
    @Serializable
    data class Metadata(val tag: String? = null)

    /**
     * 成功请求的 API 。
     *
     * @property api 成功请求的 API 名称。
     * @property hours 最近24小时每小时的成功请求量，结束时间以 [asOf] 为准。例如
     * [asOf]=2025-03-20T09:59Z，则数组中的最后一条数据代表09:00～09:59（UTC）的请求量。
     * @since 2.0.0
     */
    @Serializable
    data class Success(val api: String? = null, val hours: List<Int> = emptyList())
}