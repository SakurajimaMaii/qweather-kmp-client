package com.qwsdk.vastgui.utils.sign

actual fun getJwtSigner(keyId: String, projectId: String, privateKey: String): JwtSigner =
    JvmEd25519Signer(keyId, projectId, privateKey)