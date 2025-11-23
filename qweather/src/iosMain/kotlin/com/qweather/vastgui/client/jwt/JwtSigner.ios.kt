package com.qweather.vastgui.client.jwt

/** @since 2.0.0 */
internal actual fun platformJwtSigner(
    keyId: String,
    projectId: String,
    privateKey: String
): JwtSigner = IosJwtSigner(keyId, projectId, privateKey)