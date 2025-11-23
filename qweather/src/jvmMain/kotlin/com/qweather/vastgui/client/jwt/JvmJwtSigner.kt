package com.qweather.vastgui.client.jwt

import com.qweather.vastgui.client.jwt.JwtSigner
import io.ktor.util.decodeBase64Bytes
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

/**
 * JVM 平台的 [JwtSigner] 实现。
 *
 * @since 2.0.0
 */
class JvmJwtSigner(
    override val keyId: String,
    override val projectId: String,
    override val key: String
) : JwtSigner {
    /** @since 2.0.0 */
    private val privateKey by lazy {
        val encodeKey = key.replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\n", "").trim()
        KeyFactory.getInstance("EdDSA")
            .generatePrivate(PKCS8EncodedKeySpec(encodeKey.decodeBase64Bytes()))
    }

    override suspend fun getSign(data: ByteArray): ByteArray {
        return with(Signature.getInstance("EdDSA")) {
            initSign(privateKey)
            update(data, 0, data.size)
            sign()
        }
    }
}