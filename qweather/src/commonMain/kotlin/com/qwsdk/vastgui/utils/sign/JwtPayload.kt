package com.qwsdk.vastgui.utils.sign

import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/12
// Documentation:

/**
 * Jwt payload
 *
 * @since 1.1.3
 */
@Serializable
data class JwtPayload(val sub: String, val iat: Long, val exp: Long)