package com.qweather.vastgui.client.model.airquality

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7

/**
 * [空气质量每日预报](https://dev.qweather.com/docs/api/air-quality/air-daily-forecast/)。
 *
 * @property days 每日空气质量预报数据列表。
 * @property metadata 元数据，包含数据标签信息。
 * @since 2.0.0
 */
@Serializable
data class DailyAirQuality(
    val days: List<Day> = emptyList(),
    val metadata: Metadata? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {

    /**
     * 每日空气质量数据。
     *
     * @property forecastStartTime
     * 预报数据的开始时间（[ISO8601](https://dev.qweather.com/docs/resource/glossary/#date-time)
     * 格式）。
     * @property forecastEndTime
     * 预报数据的结束时间（[ISO8601](https://dev.qweather.com/docs/resource/glossary/#date-time)
     * 格式）。
     * @property indexes 空气质量指数信息。
     * @property pollutants 各污染物数据。
     * @since 2.0.0
     */
    @Serializable
    data class Day(
        val forecastEndTime: String? = null,
        val forecastStartTime: String? = null,
        val indexes: List<Index> = emptyList(),
        val pollutants: List<Pollutant> = emptyList()
    ) {

        /**
         * 空气质量指数信息。
         *
         * @property aqi
         * [空气质量指数的值](https://dev.qweather.com/docs/resource/air-info/#aqi-value)。
         * @property aqiDisplay
         * [空气质量指数值的文本显示](https://dev.qweather.com/docs/resource/air-info/#aqi-value)。
         * @property category 空气质量指数类别，可能为空。
         * @property code
         * [空气质量指数](https://dev.qweather.com/docs/resource/air-info/#supported-aqis)
         * Code。
         * @property color 空气质量指数的颜色（RGBA）。
         * @property health 空气质量对健康的影响信息。
         * @property level 空气质量指数等级，可能为空。
         * @property name 空气质量指数的名字。
         * @property primaryPollutant 首要污染物信息。
         * @since 2.0.0
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
             * 空气质量指数的颜色信息（RGBA）。
             *
             * @property red 红色分量。
             * @property green 绿色分量。
             * @property blue 蓝色分量。
             * @property alpha 透明度分量。
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
             * 空气质量对健康的影响信息。
             *
             * @property effect
             * [空气质量对健康的影响描述](https://dev.qweather.com/docs/resource/air-info/#health-effects-and-advice)，可能为空。
             * @property advice 针对不同人群的健康指导意见。
             * @since 2.0.0
             */
            @Serializable
            data class Health(
                val advice: Advice? = null,
                val effect: String? = null
            ) {

                /**
                 * 健康指导建议。
                 *
                 * @property generalPopulation 对一般人群的健康指导意见，可能为空。
                 * @property sensitivePopulation 对敏感人群的健康指导意见，可能为空。
                 * @since 2.0.0
                 */
                @Serializable
                data class Advice(val generalPopulation: String? = null, val sensitivePopulation: String? = null)
            }

            /**
             * 首要污染物信息。
             *
             * @property code
             * [首要污染物](https://dev.qweather.com/docs/resource/air-info/#primary-pollutant)的
             * Code，可能为空。
             * @property name 首要污染物的名字，可能为空。
             * @property fullName 首要污染物的全称，可能为空。
             * @since 2.0.0
             */
            @Serializable
            data class PrimaryPollutant(
                val code: String? = null,
                val fullName: String? = null,
                val name: String? = null
            )
        }

        /**
         * 污染物信息。
         *
         * @property code
         * [污染物](https://dev.qweather.com/docs/resource/air-info/#pollutants)的
         * Code。
         * @property name 污染物的名字。
         * @property fullName 污染物的全称。
         * @property concentration 污染物的浓度信息。
         * @property subIndexes 污染物的分指数信息。
         * @since 2.0.0
         */
        @Serializable
        data class Pollutant(
            val code: String? = null,
            val concentration: Concentration? = null,
            val fullName: String? = null,
            val name: String? = null,
            val subIndexes: List<SubIndex> = emptyList()
        ) {

            /**
             * 污染物的浓度信息。
             *
             * @property value 污染物的浓度值。
             * @property unit 浓度值的单位。
             * @since 2.0.0
             */
            @Serializable
            data class Concentration(val unit: String? = null, val value: Double? = null)

            /**
             * 污染物的分指数信息。
             *
             * @property code
             * [污染物的分指数 Code](https://dev.qweather.com/docs/resource/air-info/#pollutant-sub-index)，可能为空。
             * @property aqi
             * [污染物的分指数值](https://dev.qweather.com/docs/resource/air-info/#pollutant-sub-index)，可能为空。
             * @property aqiDisplay 污染物的分指数值的文本显示名称。
             * @since 2.0.0
             */
            @Serializable
            data class SubIndex(val aqi: Double? = null, val aqiDisplay: String? = null, val code: String? = null)
        }
    }

    /**
     * 元数据信息。
     *
     * @property tag 数据标签。
     * @since 2.0.0
     */
    @Serializable
    data class Metadata(val tag: String? = null)
}