/*
 * Copyright 2025 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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