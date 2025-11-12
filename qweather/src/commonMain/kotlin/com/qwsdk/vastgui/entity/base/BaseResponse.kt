package com.qwsdk.vastgui.entity.base

import com.qwsdk.vastgui.entity.base.error.ErrorInfo

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/13

/**
 * Base response of api.
 *
 * @since 1.1.3
 */
internal interface BaseResponse {
    /**
     * 目前同时存在两种版本的错误码，如果存在 [error]，
     * 建议使用 [ErrorInfo.status] 。
     *
     * @since 1.1.3
     */
    @Deprecated(message = "建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    val code: String? get() = null

    /**
     * [错误码](https://dev.qweather.com/docs/resource/error-code/) 。
     *
     * @since 1.1.3
     */
    val error: ErrorInfo? get() = null
}