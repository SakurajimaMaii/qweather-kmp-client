package com.qwsdk.vastgui.entity.airquality.daily

import com.qwsdk.vastgui.utils.QWSdkResponse
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7

/**
 * [空气质量每日预报](https://dev.qweather.com/docs/api/air-quality/air-daily-forecast/)。
 *
 * @property days 每日空气质量预报数据列表。
 * @property metadata 元数据，包含数据标签信息。
 * @since 1.1.3
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class AirQualityDailyBean(
    val days: List<Day> = emptyList(),
    val metadata: Metadata? = null,
    val error: ErrorInfo? = null,
    @Transient override val code: String = error?.status?.toString() ?: "200"
) : QWSdkResponse {

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
     * @since 1.1.3
     */
    @Serializable
    data class Day(
        val forecastEndTime: String = "",
        val forecastStartTime: String = "",
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
         * @since 1.1.3
         */
        @Serializable
        data class Index(
            val aqi: Double = 0.0,
            val aqiDisplay: String = "",
            val category: String = "",
            val code: String = "",
            val color: Color = Color(),
            val health: Health = Health(),
            val level: String = "",
            val name: String = "",
            val primaryPollutant: PrimaryPollutant? = null
        ) {

            /**
             * 空气质量指数的颜色信息（RGBA）。
             *
             * @property red 红色分量。
             * @property green 绿色分量。
             * @property blue 蓝色分量。
             * @property alpha 透明度分量。
             * @since 1.1.3
             */
            @Serializable
            data class Color(
                val alpha: Int = 0,
                val blue: Int = 0,
                val green: Int = 0,
                val red: Int = 0
            )

            /**
             * 空气质量对健康的影响信息。
             *
             * @property effect
             * [空气质量对健康的影响描述](https://dev.qweather.com/docs/resource/air-info/#health-effects-and-advice)，可能为空。
             * @property advice 针对不同人群的健康指导意见。
             * @since 1.1.3
             */
            @Serializable
            data class Health(
                val advice: Advice = Advice(),
                val effect: String = ""
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
                    val generalPopulation: String = "",
                    val sensitivePopulation: String = ""
                )
            }

            /**
             * 首要污染物信息。
             *
             * @property code
             * [首要污染物](https://dev.qweather.com/docs/resource/air-info/#primary-pollutant)的
             * Code，可能为空。
             * @property name 首要污染物的名字，可能为空。
             * @property fullName 首要污染物的全称，可能为空。
             * @since 1.1.3
             */
            @Serializable
            data class PrimaryPollutant(
                val code: String = "",
                val fullName: String = "",
                val name: String = ""
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
         * @since 1.1.3
         */
        @Serializable
        data class Pollutant(
            val code: String = "",
            val concentration: Concentration = Concentration(),
            val fullName: String = "",
            val name: String = "",
            val subIndexes: List<SubIndex> = emptyList()
        ) {

            /**
             * 污染物的浓度信息。
             *
             * @property value 污染物的浓度值。
             * @property unit 浓度值的单位。
             * @since 1.1.3
             */
            @Serializable
            data class Concentration(
                val unit: String = "",
                val value: Double = 0.0
            )

            /**
             * 污染物的分指数信息。
             *
             * @property code
             * [污染物的分指数 Code](https://dev.qweather.com/docs/resource/air-info/#pollutant-sub-index)，可能为空。
             * @property aqi
             * [污染物的分指数值](https://dev.qweather.com/docs/resource/air-info/#pollutant-sub-index)，可能为空。
             * @property aqiDisplay 污染物的分指数值的文本显示名称。
             * @since 1.1.3
             */
            @Serializable
            data class SubIndex(
                val aqi: Double = 0.0,
                val aqiDisplay: String = "",
                val code: String = ""
            )
        }
    }

    /**
     * 元数据信息。
     *
     * @property tag 数据标签。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(
        val tag: String = ""
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