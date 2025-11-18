package com.qwsdk.vastgui.utils.sign

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.time.ExperimentalTime

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

/** @since 1.1.3 */
interface JwtSigner {
    /** @since 1.1.3 */
    val keyId: String

    /** @since 1.1.3 */
    val projectId: String

    val privateKey: String

    /** @since 1.1.3 */
    suspend fun getSign(data: ByteArray): ByteArray

    /** @since 1.1.3 */
    @OptIn(ExperimentalTime::class)
    suspend fun getJwt(): String = withContext(Dispatchers.IO) {
        val headerJson = Json.encodeToString(mapOf("alg" to "EdDSA", "kid" to keyId))
        val now = kotlin.time.Clock.System.now().epochSeconds
        val payloadJson = Json.encodeToString(JwtPayload(projectId, now - 30, now + 900))
        val headerEnc = base64UrlEncodeString(headerJson)
        val payloadEnc = base64UrlEncodeString(payloadJson)
        val data = "$headerEnc.$payloadEnc"
        val signature = getSign(data.encodeToByteArray())
        val signatureEnc = base64UrlEncode(signature)
        return@withContext "$data.$signatureEnc"
    }
}

/** @since 1.1.3 */
expect fun getJwtSigner(keyId: String, projectId: String, privateKey: String): JwtSigner

/** @since 1.1.3 */
fun base64UrlEncode(input: ByteArray): String = Base64.UrlSafe.encode(input).trimEnd('=')

/** @since 1.1.3 */
fun base64UrlEncodeString(str: String) = base64UrlEncode(str.encodeToByteArray())