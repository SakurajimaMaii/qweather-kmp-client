package com.qwsdk.vastgui.entity.airquality

import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7

/**
 * [空气质量小时预报](https://dev.qweather.com/docs/api/air-quality/air-hourly-forecast/)。
 *
 * @property metadata 元数据信息。
 * @property hours 小时级空气质量数据列表。
 * @property error 错误信息。
 * @since 1.1.3
 */
@Serializable
data class HourlyAirQuality(
    val metadata: Metadata? = null,
    val hours: List<Hour> = emptyList(),
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {

    /**
     * 元数据信息。
     *
     * @property tag 数据标签。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(val tag: String? = null)

    /**
     * 每小时空气质量数据。
     *
     * @property forecastTime
     * 预报时间，[ISO8601](https://dev.qweather.com/docs/resource/glossary/#date-time)
     * 格式。
     * @property indexes 空气质量指数列表。
     * @property pollutants 污染物数据列表。
     * @since 1.1.3
     */
    @Serializable
    data class Hour(
        val forecastTime: String? = null,
        val indexes: List<Index> = emptyList(),
        val pollutants: List<Pollutant> = emptyList()
    ) {

        /**
         * 空气质量指数信息。
         *
         * @property aqi
         * [空气质量指数的数值](https://dev.qweather.com/docs/resource/air-info/#aqi-value)。
         * @property aqiDisplay
         * [空气质量指数值的文本显示](https://dev.qweather.com/docs/resource/air-info/#aqi-value)。
         * @property category 空气质量指数类别，可能为空。
         * @property code
         * [空气质量指数代码](https://dev.qweather.com/docs/resource/air-info/#supported-aqis)。
         * @property color 空气质量指数的颜色信息（RGBA）。
         * @property health 空气质量健康影响与建议。
         * @property level 空气质量指数等级，可能为空。
         * @property name 空气质量指数名称。
         * @property primaryPollutant 首要污染物信息。
         * @since 1.1.3
         */
        @Serializable
        data class Index(
            val aqi: Double? = null,
            val aqiDisplay: String? = null,
            val category: String? = null,
            val code: String? = null,
            val color: Color? = null,
            val health: Health? = null,
            val level: String? = null,
            val name: String? = null,
            val primaryPollutant: PrimaryPollutant? = null
        ) {

            /**
             * 颜色信息。
             *
             * @property red RGBA 颜色通道的 red 分量。
             * @property green RGBA 颜色通道的 green 分量。
             * @property blue RGBA 颜色通道的 blue 分量。
             * @property alpha RGBA 颜色通道的 alpha 分量。
             * @since 1.1.3
             */
            @Serializable
            data class Color(
                val red: Int? = null,
                val green: Int? = null,
                val blue: Int? = null,
                val alpha: Float? = null
            )

            /**
             * 健康相关信息。
             *
             * @property effect
             * [空气质量对健康的影响](https://dev.qweather.com/docs/resource/air-info/#health-effects-and-advice)，可能为空。
             * @property advice 对不同人群的健康指导建议。
             * @since 1.1.3
             */
            @Serializable
            data class Health(
                val effect: String? = null,
                val advice: Advice? = null
            ) {

                /**
                 * 健康指导建议。
                 *
                 * @property generalPopulation 对一般人群的健康指导意见，可能为空。
                 * @property sensitivePopulation 对敏感人群的健康指导意见，可能为空。
                 * @since 1.1.3
                 */
                @Serializable
                data class Advice(
                    val generalPopulation: String? = null,
                    val sensitivePopulation: String? = null
                )
            }

            /**
             * 首要污染物信息。
             *
             * @property code
             * [首要污染物的](https://dev.qweather.com/docs/resource/air-info/#primary-pollutant)
             * Code，可能为空。
             * @property name 首要污染物的名称，可能为空。
             * @property fullName 首要污染物的全称，可能为空。
             * @since 1.1.3
             */
            @Serializable
            data class PrimaryPollutant(
                val code: String? = null,
                val name: String? = null,
                val fullName: String? = null
            )
        }

        /**
         * 污染物信息。
         *
         * @property code
         * [污染物](https://dev.qweather.com/docs/resource/air-info/#pollutants)的
         * Code。
         * @property name 污染物的名称。
         * @property fullName 污染物的全称。
         * @property concentration 污染物的浓度信息。
         * @property subIndexes 污染物的分指数列表。
         * @since 1.1.3
         */
        @Serializable
        data class Pollutant(
            val code: String? = null,
            val name: String? = null,
            val fullName: String? = null,
            val concentration: Concentration? = null,
            val subIndexes: List<SubIndex> = emptyList()
        ) {

            /**
             * 污染物浓度信息。
             *
             * @property value 污染物的浓度值。
             * @property unit 污染物浓度值的单位。
             * @since 1.1.3
             */
            @Serializable
            data class Concentration(
                val value: Double? = null,
                val unit: String? = null
            )

            /**
             * 污染物分指数信息。
             *
             * @property code
             * [污染物分指数的 Code](https://dev.qweather.com/docs/resource/air-info/#pollutant-sub-index)，可能为空。
             * @property aqi
             * [污染物分指数的数值](https://dev.qweather.com/docs/resource/air-info/#pollutant-sub-index)，可能为空。
             * @property aqiDisplay 污染物分指数数值的显示名称。
             * @since 1.1.3
             */
            @Serializable
            data class SubIndex(
                val code: String? = null,
                val aqi: Double? = null,
                val aqiDisplay: String? = null
            )
        }
    }
}