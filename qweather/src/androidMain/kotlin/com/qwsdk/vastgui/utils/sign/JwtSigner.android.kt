package com.qwsdk.vastgui.utils.sign

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

/**
 * @since 1.1.3
 */
actual fun getJwtSigner(keyId: String, projectId: String, privateKey: String): JwtSigner =
    AndroidEd25519Signer(keyId, projectId, privateKey)