package com.qwsdk.vastgui.entity.radiation

import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import kotlinx.serialization.Serializable

/**
 * [太阳辐射预报](https://dev.qweather.com/docs/api/solar-radiation/solar-radiation-forecast/) 。
 *
 * @property forecasts 太阳辐射预报数据列表。
 * @property metadata 元数据，包含数据标签等信息。
 * @since 1.1.3
 */
@Serializable
data class SolarRadiationForecast(
    val forecasts: List<Forecast>? = null,
    val metadata: Metadata? = null,
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
     * @property forecastTime 预报时间，[ISO8601](https://dev.qweather.com/docs/resource/glossary/#date-time) 格式。
     * @since 1.1.3
     */
    @Serializable
    data class Forecast(
        val dhi: Dhi,
        val dni: Dni,
        val ghi: Ghi,
        val solarAngle: SolarAngle,
        val forecastTime: String,
        val weather: Weather? = null,
        val poa: Poa? = null,
    ) {

        /**
         * 散射水平面辐照数据。
         *
         * @property unit 单位：瓦特每平方米（W/m²）。
         * @property value 辐照数值。
         * @since 1.1.3
         */
        @Serializable
        data class Dhi(val unit: String, val value: Double)

        /**
         * 法向直接辐照数据。
         *
         * @property unit 单位：瓦特每平方米（W/m²）。
         * @property value 辐照数值。
         * @since 1.1.3
         */
        @Serializable
        data class Dni(val unit: String, val value: Double)

        /**
         * 总水平面辐照数据。
         *
         * @property unit 单位：瓦特每平方米（W/m²）。
         * @property value 辐照数值。
         * @since 1.1.3
         */
        @Serializable
        data class Ghi(val unit: String, val value: Double)

        /**
         * 组件平面辐照数据（包括散射、直接、总辐照和反射辐照）。
         *
         * @since 1.1.3
         */
        @Serializable
        data class Poa(
            val diffuse: Diffuse,
            val direct: Direct,
            val global: Global,
            val reflected: Reflected
        ) {
            /**
             * 组件平面散射辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 1.1.3
             */
            @Serializable
            data class Diffuse(val unit: String, val value: Double)

            /**
             * 组件平面直接辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 1.1.3
             */
            @Serializable
            data class Direct(val unit: String, val value: Double)

            /**
             * 组件平面总辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 1.1.3
             */
            @Serializable
            data class Global(val unit: String, val value: Double)

            /**
             * 组件平面地面反射辐照数据。
             *
             * @property unit 单位：瓦特每平方米（W/m²）。
             * @property value 辐照数值。
             * @since 1.1.3
             */
            @Serializable
            data class Reflected(val unit: String, val value: Double)
        }

        /**
         * 太阳角度数据（包括方位角和高度角）。
         *
         * @property azimuth 方位角，正北为 0 度，顺时针增加，单位：度。
         * @property elevation 高度角，单位：度。
         * @since 1.1.3
         */
        @Serializable
        data class SolarAngle(val azimuth: Int, val elevation: Int)

        /**
         * 天气数据（温度、湿度、风速）。
         *
         * @since 1.1.3
         */
        @Serializable
        data class Weather(val humidity: Int, val temperature: Temperature, val windSpeed: WindSpeed) {
            /**
             * 温度数据。
             *
             * @property unit 单位：摄氏度（°C）。
             * @property value 温度值。
             * @since 1.1.3
             */
            @Serializable
            data class Temperature(val unit: String, val value: Double)

            /**
             * 风速数据。
             *
             * @property unit 单位：米每秒（m/s）。
             * @property value 风速数值。
             * @since 1.1.3
             */
            @Serializable
            data class WindSpeed(val unit: String, val value: Double)
        }
    }

    /**
     * 元数据，包含数据标签等附加信息。
     *
     * @property tag 数据标签。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(val tag: String)
}