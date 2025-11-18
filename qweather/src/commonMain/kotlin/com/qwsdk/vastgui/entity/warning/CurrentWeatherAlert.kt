package com.qwsdk.vastgui.entity.warning

import com.qwsdk.vastgui.entity.base.BaseResponse
import com.qwsdk.vastgui.entity.base.error.ErrorInfo
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/12
// Documentation:

/**
 * [实时天气预警](https://dev.qweather.com/docs/api/warning/weather-alert/) 。
 *
 * @property alerts 当前地区的预警信息列表，参考 [Alert]。
 * @property metadata 参考 [Metadata]。
 * @since 1.1.3
 */
@Serializable
data class CurrentWeatherAlert(
    val alerts: List<Alert> = emptyList(),
    val metadata: Metadata? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {

    /**
     * 预警信息详情。
     *
     * @property id 本条预警信息的唯一标识。
     * @property senderName 预警发布机构的名称，可能为空。
     * @property issuedTime 原始预警信息生成的时间，实际发布或接收时间会略有延迟。
     * @property messageType 预警信息的性质，参考 [MessageType]。
     * @property eventType 预警事件类型，参考 [EventType]。
     * @property urgency
     * 预警信息的[紧迫程度](https://dev.qweather.com/docs/resource/warning-info/#urgency)，可能为空。
     * @property severity
     * 预警信息的[严重程度](https://dev.qweather.com/docs/resource/warning-info/#severity)。
     * @property certainty
     * 预警信息的[确定性或可信度](https://dev.qweather.com/docs/resource/warning-info/#certainty)，可能为空。
     * @property icon
     * 预警对应的[图标代码](https://dev.qweather.com/docs/resource/icons/)。
     * @property color 预警信息的颜色，参考 [Color]。
     * @property effectiveTime 预警信息的生效时间，可能为空。
     * @property onsetTime 预警事件预计开始的时间，可能为空。
     * @property expireTime 预警信息的失效时间。
     * @property headline 预警信息的简要描述或标题。
     * @property description 预警信息的详细描述。
     * @property criteria 当前预警信息的触发标准或条件。仅供参考，可能滞后于官方标准。可能为空。
     * @property instruction 对当前预警的防御指南或行动指导，可能为空。
     * @property responseTypes 对当前预警的应对方式的类型代码，可能为空。
     * @since 1.1.3
     */
    @Serializable
    data class Alert(
        val certainty: String? = null,
        val color: Color? = null,
        val criteria: String? = null,
        val description: String? = null,
        val effectiveTime: String? = null,
        val eventType: EventType? = null,
        val expireTime: String? = null,
        val headline: String? = null,
        val icon: String? = null,
        val id: String? = null,
        val instruction: String? = null,
        val issuedTime: String? = null,
        val messageType: MessageType? = null,
        val onsetTime: String? = null,
        val responseTypes: List<String> = emptyList(),
        val senderName: String? = null,
        val severity: String? = null,
        val urgency: String? = null
    ) {

        /**
         * 预警颜色信息。
         *
         * @property code
         * [预警信息的颜色代码](https://dev.qweather.com/docs/resource/warning-info/#color)。
         * @property red 预警颜色的红色分量值（RGBA），范围 0–255。
         * @property green 预警颜色的绿色分量值（RGBA），范围 0–255。
         * @property blue 预警颜色的蓝色分量值（RGBA），范围 0–255。
         * @property alpha 预警颜色的透明度分量值（RGBA），范围 0–1。
         * @since 1.1.3
         */
        @Serializable
        data class Color(
            val alpha: Int? = null,
            val blue: Int? = null,
            val code: String? = null,
            val green: Int? = null,
            val red: Int? = null
        )

        /**
         * 预警事件类型信息。
         *
         * @property code
         * [预警事件类型的代码](https://dev.qweather.com/docs/resource/warning-info/#event-and-code)。
         * @property name
         * [预警事件类型的名称](https://dev.qweather.com/docs/resource/warning-info/#event-and-code)。
         * @since 1.1.3
         */
        @Serializable
        data class EventType(
            val code: String? = null,
            val name: String? = null
        )

        /**
         * 预警信息类型。
         *
         * @property code
         * [预警信息性质的代码](https://dev.qweather.com/docs/resource/warning-info/#message-type)，开发者可以了解当前预警是新发布的还是对之前预警的更新。
         * @property supersedes 当前预警取代或取消的预警 ID 列表，仅在 [code] 为 update 或 cancel 时返回。
         * @since 1.1.3
         */
        @Serializable
        data class MessageType(
            val code: String? = null,
            val supersedes: List<String> = emptyList()
        )
    }

    /**
     * 元数据。
     *
     * @property tag 数据标签。
     * @property zeroResult `true` 表示请求成功，但无数据返回，例如查询地点无预警。
     * @property attributions 数据来源或声明，开发者必须将此内容与当前数据一起展示。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(
        val attributions: List<String> = emptyList(),
        val tag: String? = null,
        val zeroResult: Boolean? = null
    )
}