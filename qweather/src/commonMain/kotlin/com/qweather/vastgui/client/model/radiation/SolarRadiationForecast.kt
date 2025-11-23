package com.qweather.vastgui.client.model.radiation

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [太阳辐射预报](https://dev.qweather.com/docs/api/solar-radiation/solar-radiation-forecast/)
 * 。
 *
 * @property forecasts 太阳辐射预报数据列表。
 * @property metadata 元数据，包含数据标签等信息。
 * @since 2.0.0
 */
@Serializable
data class SolarRadiationForecast(
    val forecasts: List<Forecast> = emptyList(),
    val metadata: Metadata? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * 太阳辐射逐小时预报数据。
     *
     * @property dhi 散射水平面辐照数据。
     * @property dni 法向直接辐照数据。
     * @property ghi 总水平面辐照数据。
     * @property poa 组件平面辐照数据。
     * @property solarAngle 太阳角度（方位角和高度角）。
     * @property weather 天气数据（温度、湿度、风速）。
     * @property forecastTime
     * 预报时间，[ISO8601](https://dev.qweather.com/docs/resource/glossary/#date-time)
     * 格式。
     * @since 2.0.0
     */
    @Serializable
    data class Forecast(
        val dhi: Dhi? = null,
        val dni: Dni? = null,
        val ghi: Ghi? = null,
        val solarAngle: SolarAngle? = null,
        val forecastTime: String? = null,
        val weather: Weather? = null,
        val poa: Poa? = null,
    ) {

        /**
         * 散射水平面辐照数据。
         *
         * @property unit 单位：瓦特每平方米（W/m²）。
         * @property value 辐照数值。
         * @since 2.0.0
         */
        @Serializable
        data class Dhi(val unit: String? = null, val value: Double? = null)

        /**
         * 法向直接辐照数据。
         *
         * @property unit 单位：瓦特每平方米（W/m²）。
         * @property value 辐照数值。
         * @since 2.0.0
         */
        @Serializable
        data class Dni(val unit: String? = null, val value: Double? = null)

        /**
         * 总水平面辐照数据。
         *
         * @property unit 单位：瓦特每平方米（W/m²）。
         * @property value 辐照数值。
         * @since 2.0.0
         */
        @Serializable
        data class Ghi(val unit: String? = null, val value: Double? = null)

        /**
         * 组件平面辐照数据（包括散射、直接、总辐照和反射辐照）。
         *
         * @since 2.0.0
         */
        @Serializable
        data class Poa(
            val diffuse: Diffuse? = null,
            val direct: Direct? = null,
            val global: Global? = null,
            val reflected: Reflected? = null
        ) {
            /**
             * 组件平面散射辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 2.0.0
             */
            @Serializable
            data class Diffuse(val unit: String? = null, val value: Double? = null)

            /**
             * 组件平面直接辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 2.0.0
             */
            @Serializable
            data class Direct(val unit: String? = null, val value: Double? = null)

            /**
             * 组件平面总辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 2.0.0
             */
            @Serializable
            data class Global(val unit: String? = null, val value: Double? = null)

            /**
             * 组件平面地面反射辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 2.0.0
             */
            @Serializable
            data class Reflected(val unit: String? = null, val value: Double? = null)
        }

        /**
         * 太阳角度数据（包括方位角和高度角）。
         *
         * @property azimuth 方位角，正北为 0 度，顺时针增加，单位：度。
         * @property elevation 高度角，单位：度。
         * @since 2.0.0
         */
        @Serializable
        data class SolarAngle(val azimuth: Int? = null, val elevation: Int? = null)

        /**
         * 天气数据（温度、湿度、风速）。
         *
         * @since 2.0.0
         */
        @Serializable
        data class Weather(
            val humidity: Int? = null,
            val temperature: Temperature? = null,
            val windSpeed: WindSpeed? = null
        ) {
            /**
             * 温度数据。
             *
             * @property unit 单位：摄氏度（°C）。
             * @property value 温度值。
             * @since 2.0.0
             */
            @Serializable
            data class Temperature(val unit: String? = null, val value: Double? = null)

            /**
             * 风速数据。
             *
             * @property unit 单位：米每秒（m/s）。
             * @property value 风速数值。
             * @since 2.0.0
             */
            @Serializable
            data class WindSpeed(val unit: String? = null, val value: Double? = null)
        }
    }

    /**
     * 元数据，包含数据标签等附加信息。
     *
     * @property tag 数据标签。
     * @since 2.0.0
     */
    @Serializable
    data class Metadata(val tag: String? = null)
}