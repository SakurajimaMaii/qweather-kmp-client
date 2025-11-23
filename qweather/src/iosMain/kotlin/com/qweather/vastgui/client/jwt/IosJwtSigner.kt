package com.qweather.vastgui.client.jwt

import com.qweather.vastgui.client.ios.IosEd25519CryptoKit
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

/**
 * Ios 平台的 [JwtSigner] 实现。
 *
 * @since 2.0.0
 */
class IosJwtSigner(
    override val keyId: String,
    override val projectId: String,
    override val key: String
) : JwtSigner {
    /** @since 2.0.0 */
    @OptIn(ExperimentalForeignApi::class)
    private val delegate = IosEd25519CryptoKit(
        keyId = keyId,
        projectId = projectId,
        privateKeyPem = key
    )

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    override suspend fun getSign(data: ByteArray): ByteArray {
        val nsData = data.usePinned { pinned ->
            NSData.create(pinned.addressOf(0), data.size.toULong())
        }
        val signature = delegate.signWithData(nsData)
        return signature.let { sign ->
            ByteArray(sign.length.toInt()).apply {
                usePinned { pinned ->
                    memcpy(pinned.addressOf(0), sign.bytes, sign.length)
                }
            }
        }
    }
}