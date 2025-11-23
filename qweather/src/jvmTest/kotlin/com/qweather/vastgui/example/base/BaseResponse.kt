@file:Suppress("DEPRECATION")

package com.qweather.vastgui.example.base

import com.qweather.vastgui.client.model.base.BaseResponse

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/13
// Documentation:

internal fun BaseResponse.requireCode() =
    error?.status ?: code?.toIntOrNull() ?: 200