package com.qwsdk.vastgui.utils.sign

import kotlin.io.encoding.Base64

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

/**
 * @since 1.1.3
 */
interface JwtSigner {
    /**
     * @since 1.1.3
     */
    val keyId: String

    /**
     * @since 1.1.3
     */
    val projectId: String

    val privateKey: String

    /**
     * @since 1.1.3
     */
    suspend fun sign(data: ByteArray): ByteArray
}

expect fun getJwtSigner(keyId: String, projectId: String, privateKey: String): JwtSigner

fun base64UrlEncode(input: ByteArray): String = Base64.UrlSafe.encode(input).trimEnd('=')

fun base64UrlEncodeString(str: String) = base64UrlEncode(str.encodeToByteArray())