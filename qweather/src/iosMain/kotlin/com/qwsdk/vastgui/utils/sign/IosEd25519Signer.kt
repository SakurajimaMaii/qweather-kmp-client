package com.qwsdk.vastgui.utils.sign

import com.qwsdk.vastgui.cryptokit.IosEd25519CryptoKit
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create
import platform.posix.memcpy

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
    private val delegate = IosEd25519CryptoKit(
        keyId = keyId,
        projectId = projectId,
        privateKeyPem = privateKey
    )

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    override suspend fun getSign(data: ByteArray): ByteArray {
        val nsData = data.usePinned { pinned ->
            NSData.create(bytes = pinned.addressOf(0), length = data.size.toULong())
        }
        val signature = delegate.signWithData(nsData)
        return signature.toByteArray()
    }

}

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray {
    val bytes = ByteArray(length.toInt())
    bytes.usePinned {
        memcpy(it.addressOf(0), this.bytes, length)
    }
    return bytes
}