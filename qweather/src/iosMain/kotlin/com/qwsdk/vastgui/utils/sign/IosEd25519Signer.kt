package com.qwsdk.vastgui.utils.sign

import kotlinx.cinterop.ExperimentalForeignApi

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

class IosEd25519Signer(
    override val keyId: String,
    override val projectId: String,
    override val privateKey: String
) : JwtSigner {

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun sign(data: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }

}