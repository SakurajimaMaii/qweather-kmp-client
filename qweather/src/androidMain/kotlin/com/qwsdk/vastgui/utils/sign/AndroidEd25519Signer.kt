package com.qwsdk.vastgui.utils.sign

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
 * @since 1.1.3
 */
class AndroidEd25519Signer(
    override val keyId: String,
    override val projectId: String,
    override val privateKey: String
) : JwtSigner {

    private val key by lazy {
        privateKey.replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\n", "").trim()
            .let { EdDSAPrivateKey(PKCS8EncodedKeySpec(it.decodeBase64Bytes())) }
    }

    override suspend fun getSign(data: ByteArray): ByteArray {
        val spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)
        val signature = EdDSAEngine(MessageDigest.getInstance(spec.hashAlgorithm))
        return with(signature) {
            initSign(key)
            update(data, 0, data.size)
            sign()
        }
    }

}