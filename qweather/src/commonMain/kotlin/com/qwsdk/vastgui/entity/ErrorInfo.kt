package com.qwsdk.vastgui.entity

import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7

/**
 * [错误码](https://dev.qweather.com/docs/resource/error-code/)。
 *
 * ```json
 * {
 *     "status": 400,
 *     "type": "https://dev.qweather.com/docs/resource/error-code/#invalid-parameters",
 *     "title": "Invalid Parameters",
 *     "detail": "Invalid parameters, please check your request.",
 *     "invalidParams": [
 *         "lang"
 *     ]
 * }
 * ```
 *
 * @property code 对应这个错误的HTTP status code 。
 * @property type 这是一个 URL 用于标识错误类型。
 * @property title 对错误的简短描述。
 * @property detail 对错误的详细描述。
 * @property invalidParams 标识错误或缺失的参数。
 * @since 1.1.3
 */
@Serializable
data class ErrorInfo(
    val detail: String = "",
    val invalidParams: List<String> = emptyList(),
    val status: Int,
    val title: String,
    val type: String
)