package com.qweather.vastgui.client.model.airquality

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

/**
 * [监测站数据](https://dev.qweather.com/docs/api/air-quality/air-station/)。
 *
 * @property metadata 成功返回的元数据信息。
 * @property pollutants 污染物列表。
 * @since 2.0.0
 */
@Serializable
data class StationAirQuality(
    val metadata: Metadata? = null,
    val pollutants: List<Pollutant> = emptyList(),
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {

    /**
     * 响应的元数据。
     *
     * @property tag 数据标签。
     * @property sources 数据来源或声明，开发者必须将此内容与当前数据一起展示。
     * @since 2.0.0
     */
    @Serializable
    data class Metadata(
        val tag: String? = null,
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
     * @since 2.0.0
     */
    @Serializable
    data class Pollutant(
        val code: String? = null,
        val name: String? = null,
        val fullName: String? = null,
        val concentration: Concentration? = null
    )

    /**
     * 污染物浓度信息。
     *
     * @property value 污染物的浓度值。
     * @property unit 污染物的浓度值的单位。
     * @since 2.0.0
     */
    @Serializable
    data class Concentration(val value: Double? = null, val unit: String? = null)
}