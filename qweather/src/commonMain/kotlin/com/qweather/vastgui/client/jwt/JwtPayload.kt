package com.qweather.vastgui.client.jwt

import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/12
// Documentation:

/**
 * Jwt payload
 *
 * @since 2.0.0
 */
@Serializable
internal data class JwtPayload(val sub: String, val iat: Long, val exp: Long)