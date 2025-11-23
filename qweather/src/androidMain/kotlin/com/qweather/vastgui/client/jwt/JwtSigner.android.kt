package com.qweather.vastgui.client.jwt

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

/** @since 2.0.0 */
internal actual fun platformJwtSigner(
    keyId: String,
    projectId: String,
    privateKey: String
): JwtSigner = AndroidJwtSigner(keyId, projectId, privateKey)