package com.qwsdk.vastgui.utils.sign

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

actual fun getJwtSigner(keyId: String, projectId: String, privateKey: String): JwtSigner =
    JvmEd25519Signer(keyId, projectId, privateKey)