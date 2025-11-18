package com.qwsdk.vastgui.utils.sign

actual fun getJwtSigner(keyId: String, projectId: String, privateKey: String): JwtSigner =
    IosEd25519Signer(keyId, projectId, privateKey)