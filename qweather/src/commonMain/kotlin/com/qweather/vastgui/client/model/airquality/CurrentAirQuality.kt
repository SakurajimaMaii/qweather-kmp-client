package com.qweather.vastgui.client.model.airquality

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

/**
 * [实时空气质量](https://dev.qweather.com/docs/api/air-quality/air-current/)
 *
 * @property metadata 元数据。
 * @property indexes 空气质量指数列表。
 * @property pollutants 污染物信息列表。
 * @property stations AQI 关联的监测站信息。
 * @since 2.0.0
 */
@Serializable
data class CurrentAirQuality(
    val metadata: Metadata? = null,
    val indexes: List<Index> = emptyList(),
    val pollutants: List<Pollutant> = emptyList(),
    val stations: List<Station> = emptyList(),
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {

    /**
     * @property tag 数据标签。
     * @since 2.0.0
     */
    @Serializable
    data class Metadata(val tag: String? = null)

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
     * @since 2.0.0
     */
    @Serializable
    data class Index(
        val code: String? = null,
        val name: String? = null,
        val aqi: Int? = null,
        val aqiDisplay: String? = null,
        val level: String? = null,
        val category: String? = null,
        val color: Color? = null,
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
     * @since 2.0.0
     */
    @Serializable
    data class Color(
        val red: Int? = null,
        val green: Int? = null,
        val blue: Int? = null,
        val alpha: Float? = null
    )

    /**
     * 首要污染物信息。
     *
     * @property code
     * [首要污染物代码](https://dev.qweather.com/docs/resource/air-info/#primary-pollutant)，可能为空。
     * @property name 首要污染物名称，可能为空。
     * @property fullName 首要污染物全称，可能为空。
     * @since 2.0.0
     */
    @Serializable
    data class PrimaryPollutant(val code: String? = null, val name: String? = null, val fullName: String? = null)

    /**
     * 健康影响与建议。
     *
     * @property effect
     * [空气质量对健康的总体影响](https://dev.qweather.com/docs/resource/air-info/#health-effects-and-advice)，可能为空。
     * @property advice 健康建议信息。
     * @since 2.0.0
     */
    @Serializable
    data class Health(val effect: String? = null, val advice: Advice? = null)

    /**
     * 健康建议信息。
     *
     * @property generalPopulation 一般人群健康指导意见，可能为空。
     * @property sensitivePopulation 敏感人群健康指导意见，可能为空。
     * @since 2.0.0
     */
    @Serializable
    data class Advice(val generalPopulation: String? = null, val sensitivePopulation: String? = null)

    /**
     * 污染物信息。
     *
     * @property code
     * [污染物代码](https://dev.qweather.com/docs/resource/air-info/#pollutants)。
     * @property name 污染物名称。
     * @property fullName 污染物全称。
     * @property concentration 污染物浓度信息。
     * @property subIndexes 污染物分指数信息列表。
     * @since 2.0.0
     */
    @Serializable
    data class Pollutant(
        val code: String? = null,
        val name: String? = null,
        val fullName: String? = null,
        val concentration: Concentration? = null,
        val subIndexes: List<SubIndex> = emptyList()
    )

    /**
     * 污染物浓度信息。
     *
     * @property value 浓度值。
     * @property unit 浓度单位。
     * @since 2.0.0
     */
    @Serializable
    data class Concentration(val value: Double? = null, val unit: String? = null)

    /**
     * 污染物分指数信息。
     *
     * @property code 分指数代码，可能为空。
     * @property aqi 分指数数值，可能为空。
     * @property aqiDisplay 分指数显示名称。
     * @since 2.0.0
     */
    @Serializable
    data class SubIndex(val code: String? = null, val aqi: Int? = null, val aqiDisplay: String? = null)

    /**
     * AQI 监测站信息。
     *
     * @property id 监测站 ID，可能为空。
     * @property name 监测站名称，可能为空。
     * @since 2.0.0
     */
    @Serializable
    data class Station(val id: String? = null, val name: String? = null)
}