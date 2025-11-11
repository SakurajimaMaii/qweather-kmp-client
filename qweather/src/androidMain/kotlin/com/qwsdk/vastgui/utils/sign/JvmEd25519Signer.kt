package com.qwsdk.vastgui.utils.sign

import android.util.Base64
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters
import org.bouncycastle.crypto.signers.Ed25519Signer

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
            .let { Ed25519PrivateKeyParameters(Base64.decode(it, Base64.DEFAULT), 0) }
    }

    override suspend fun sign(data: ByteArray): ByteArray {
        return with(Ed25519Signer()) {
            init(true, key)
            update(data, 0, data.size)
            generateSignature()
        }
    }

}