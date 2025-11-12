@file:Suppress("DEPRECATION")

package com.qwsdk.vastgui.main.base

import com.qwsdk.vastgui.entity.base.BaseResponse

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/13
// Documentation:

internal fun BaseResponse.requireCode() =
    error?.status ?: code?.toIntOrNull() ?: 200