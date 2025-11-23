package com.qweather.vastgui.client.jwt

import io.ktor.util.decodeBase64Bytes
import net.i2p.crypto.eddsa.EdDSAEngine
import net.i2p.crypto.eddsa.EdDSAPrivateKey
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import java.security.MessageDigest
import java.security.spec.PKCS8EncodedKeySpec

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/12
// Reference:

/**
 * Android 平台的 [JwtSigner] 实现。
 *
 * @since 2.0.0
 */
internal class AndroidJwtSigner(
    override val keyId: String,
    override val projectId: String,
    override val key: String
) : JwtSigner {
    /** @since 2.0.0 */
    private val privateKey by lazy {
        key.replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\n", "").trim()
            .let { EdDSAPrivateKey(PKCS8EncodedKeySpec(it.decodeBase64Bytes())) }
    }

    override suspend fun getSign(data: ByteArray): ByteArray {
        val spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)
        val signature = EdDSAEngine(MessageDigest.getInstance(spec.hashAlgorithm))
        return with(signature) {
            initSign(privateKey)
            update(data, 0, data.size)
            sign()
        }
    }
}