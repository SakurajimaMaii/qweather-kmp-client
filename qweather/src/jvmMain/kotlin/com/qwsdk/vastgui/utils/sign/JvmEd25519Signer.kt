package com.qwsdk.vastgui.utils.sign

import io.ktor.util.decodeBase64Bytes
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

class JvmEd25519Signer(
    override val keyId: String,
    override val projectId: String,
    override val privateKey: String
) : JwtSigner {

    private val key by lazy {
        privateKey.replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\n", "").trim()
            .let { KeyFactory.getInstance("EdDSA").generatePrivate(PKCS8EncodedKeySpec(it.decodeBase64Bytes())) }
    }

    override suspend fun getSign(data: ByteArray): ByteArray {
        return with(Signature.getInstance("EdDSA")) {
            initSign(key)
            update(data, 0, data.size)
            sign()
        }
    }

}