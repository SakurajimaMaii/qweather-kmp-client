/*
 * Copyright 2024 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qwsdk.vastgui.entity.airquality.station

import com.qwsdk.vastgui.utils.QWSdkResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

/**
 * [监测站数据](https://dev.qweather.com/docs/api/air-quality/air-station/)。
 *
 * @property metadata 成功返回的元数据信息。
 * @property pollutants 污染物列表。
 * @since 1.1.3
 */
@Serializable
data class AirQualityStationBean(
    val metadata: Metadata? = null,
    val pollutants: List<Pollutant> = emptyList(),
    val error: ErrorInfo? = null,
    @Transient override val code: String = error?.status?.toString() ?: "200"
) : QWSdkResponse {

    /**
     * 响应的元数据。
     *
     * @property tag 数据标签。
     * @property sources 数据来源或声明，开发者必须将此内容与当前数据一起展示。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(
        val tag: String = "",
        val sources: List<String> = emptyList()
    )

    /**
     * 污染物信息。
     *
     * @property code
     * [污染物](https://dev.qweather.com/docs/resource/air-info/#pollutants)的
     * Code。
     * @property name 污染物的名字。
     * @property fullName 污染物的全称。
     * @property concentration 污染物浓度信息。
     * @since 1.1.3
     */
    @Serializable
    data class Pollutant(
        val code: String = "",
        val name: String = "",
        val fullName: String = "",
        val concentration: Concentration = Concentration()
    )

    /**
     * 污染物浓度信息。
     *
     * @property value 污染物的浓度值。
     * @property unit 污染物的浓度值的单位。
     * @since 1.1.3
     */
    @Serializable
    data class Concentration(
        val value: Double = 0.0,
        val unit: String = ""
    )

    /**
     * 请求错误信息。
     *
     * ```
     * {
     *   "error": {
     *     "status": 400,
     *     "type": "https://dev.qweather.com/docs/resource/error-code/#invalid-parameter",
     *     "title": "Invalid Parameter",
     *     "detail": "Invalid parameter, please check your request.",
     *     "invalidParams": [
     *       "LocationID"
     *     ]
     *   }
     * }
     * ```
     *
     * @property status HTTP 状态码，例如 400。
     * @property type 错误类型链接，指向官方错误码文档。
     * @property title 错误标题。
     * @property detail 错误的详细描述。
     * @property invalidParams 无效的参数字段列表（可选）。
     * @since 1.1.3
     */
    @Serializable
    data class ErrorInfo(
        val status: Int = 0,
        val type: String = "",
        val title: String = "",
        val detail: String = "",
        val invalidParams: List<String> = emptyList()
    )
}