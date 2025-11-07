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

package com.qwsdk.vastgui.entity.airquality.now

import com.qwsdk.vastgui.utils.QWSdkResponse
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

/**
 * [实时空气质量](https://dev.qweather.com/docs/api/air-quality/air-now/)
 *
 * @property metadata 元数据。
 * @property indexes 空气质量指数列表。
 * @property pollutants 污染物信息列表。
 * @property stations AQI 关联的监测站信息。
 * @since 1.1.3
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class AirQualityCurrentBean(
    val metadata: Metadata? = null,
    val indexes: List<Index> = emptyList(),
    val pollutants: List<Pollutant> = emptyList(),
    val stations: List<Station> = emptyList(),
    val error: ErrorInfo? = null,
    @Transient override val code: String = error?.status?.toString() ?: "200"
) : QWSdkResponse {

    /**
     * @property tag 数据标签。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(
        @EncodeDefault
        val tag: String = ""
    )

    /**
     * 空气质量指数信息。
     *
     * @property code
     * [空气质量指数](https://dev.qweather.com/docs/resource/air-info/#supported-aqis)代码。
     * @property name 空气质量指数名称。
     * @property aqi
     * [空气质量指数值](https://dev.qweather.com/docs/resource/air-info/#aqi-value)。
     * @property aqiDisplay
     * [空气质量指数值的文本显示](https://dev.qweather.com/docs/resource/air-info/#aqi-value)。
     * @property level 空气质量指数等级，可能为空。
     * @property category 空气质量指数类别，可能为空。
     * @property color 指数颜色信息。
     * @property primaryPollutant 首要污染物信息，可能为空。
     * @property health 健康影响与建议，可能为空。
     * @since 1.1.3
     */
    @Serializable
    data class Index(
        @EncodeDefault
        val code: String = "",
        @EncodeDefault
        val name: String = "",
        @EncodeDefault
        val aqi: Int = 0,
        @EncodeDefault
        val aqiDisplay: String = "",
        val level: String? = null,
        val category: String? = null,
        @EncodeDefault
        val color: Color = Color(),
        val primaryPollutant: PrimaryPollutant? = null,
        val health: Health? = null
    )

    /**
     * RGBA 颜色信息。
     *
     * @property red 红色分量。
     * @property green 绿色分量。
     * @property blue 蓝色分量。
     * @property alpha 透明度分量，范围 0~1。
     * @since 1.1.3
     */
    @Serializable
    data class Color(
        @EncodeDefault
        val red: Int = 0,
        @EncodeDefault
        val green: Int = 0,
        @EncodeDefault
        val blue: Int = 0,
        @EncodeDefault
        val alpha: Float = 1f
    )

    /**
     * 首要污染物信息。
     *
     * @property code
     * [首要污染物代码](https://dev.qweather.com/docs/resource/air-info/#primary-pollutant)，可能为空。
     * @property name 首要污染物名称，可能为空。
     * @property fullName 首要污染物全称，可能为空。
     * @since 1.1.3
     */
    @Serializable
    data class PrimaryPollutant(
        val code: String? = null,
        val name: String? = null,
        val fullName: String? = null
    )

    /**
     * 健康影响与建议。
     *
     * @property effect
     * [空气质量对健康的总体影响](https://dev.qweather.com/docs/resource/air-info/#health-effects-and-advice)，可能为空。
     * @property advice 健康建议信息。
     * @since 1.1.3
     */
    @Serializable
    data class Health(
        val effect: String? = null,
        @EncodeDefault
        val advice: Advice = Advice()
    )

    /**
     * 健康建议信息。
     *
     * @property generalPopulation 一般人群健康指导意见，可能为空。
     * @property sensitivePopulation 敏感人群健康指导意见，可能为空。
     * @since 1.1.3
     */
    @Serializable
    data class Advice(
        val generalPopulation: String? = null,
        val sensitivePopulation: String? = null
    )

    /**
     * 污染物信息。
     *
     * @property code
     * [污染物代码](https://dev.qweather.com/docs/resource/air-info/#pollutants)。
     * @property name 污染物名称。
     * @property fullName 污染物全称。
     * @property concentration 污染物浓度信息。
     * @property subIndexes 污染物分指数信息列表。
     * @since 1.1.3
     */
    @Serializable
    data class Pollutant(
        @EncodeDefault
        val code: String = "",
        @EncodeDefault
        val name: String = "",
        @EncodeDefault
        val fullName: String = "",
        @EncodeDefault
        val concentration: Concentration = Concentration(),
        @EncodeDefault
        val subIndexes: List<SubIndex> = emptyList()
    )

    /**
     * 污染物浓度信息。
     *
     * @property value 浓度值。
     * @property unit 浓度单位。
     * @since 1.1.3
     */
    @Serializable
    data class Concentration(
        @EncodeDefault
        val value: Double = 0.0,
        @EncodeDefault
        val unit: String = ""
    )

    /**
     * 污染物分指数信息。
     *
     * @property code 分指数代码，可能为空。
     * @property aqi 分指数数值，可能为空。
     * @property aqiDisplay 分指数显示名称。
     * @since 1.1.3
     */
    @Serializable
    data class SubIndex(
        val code: String? = null,
        val aqi: Int? = null,
        @EncodeDefault
        val aqiDisplay: String = ""
    )

    /**
     * AQI 监测站信息。
     *
     * @property id 监测站 ID，可能为空。
     * @property name 监测站名称，可能为空。
     * @since 1.1.3
     */
    @Serializable
    data class Station(
        val id: String? = null,
        val name: String? = null
    )

    /**
     * 请求错误信息。
     *
     * ```
     * {
     *   "error": {
     *     "status": 400,
     *     "type": "https://dev.qweather.com/docs/resource/error-code/#data-not-available",
     *     "title": "Data Not Available",
     *     "detail": "Data for this location is temporarily unavailable, please try another location."
     *   }
     * }
     * ```
     *
     * @property status HTTP 状态码。
     * @property type 错误类型链接。
     * @property title 错误标题。
     * @property detail 错误详情描述。
     * @since 1.1.3
     */
    @Serializable
    data class ErrorInfo(
        @EncodeDefault
        val status: Int = 0,
        @EncodeDefault
        val type: String = "",
        @EncodeDefault
        val title: String = "",
        @EncodeDefault
        val detail: String = ""
    )
}