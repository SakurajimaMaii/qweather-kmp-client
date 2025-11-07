package com.qwsdk.vastgui.entity

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

/**
 * Base response of api.
 *
 * @since 1.1.3
 */
internal interface BaseResponse {
    val error: ErrorInfo? get() = null
}