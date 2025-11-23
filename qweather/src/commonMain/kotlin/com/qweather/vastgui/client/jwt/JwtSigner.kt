package com.qweather.vastgui.client.jwt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/11
// Reference:

/**
 * [身份认证](https://dev.qweather.com/docs/configuration/authentication/)。
 *
 * @since 2.0.0
 */
internal interface JwtSigner {
    /**
     * 凭据 Id 。
     *
     * @since 2.0.0
     */
    val keyId: String

    /**
     * 凭据所属的项目 Id 。
     *
     * @since 2.0.0
     */
    val projectId: String

    /**
     * 凭据 Id 对应的私钥，以下为示例：
     *
     * ```
     * // 以下是 key 的示例值：
     * -----BEGIN PUBLIC KEY-----
     * MCowBQYDK2VwAyEAARbeZ5AhklFG4gg1Gx5g5bWxMMdsUd6b2MC4wV0/M9Q=
     * -----END PUBLIC KEY-----
     * ```
     *
     * @since 2.0.0
     */
    val key: String

    /** @since 2.0.0 */
    suspend fun getSign(data: ByteArray): ByteArray

    /** @since 2.0.0 */
    @OptIn(ExperimentalTime::class)
    suspend fun getJwt(): String = withContext(Dispatchers.IO) {
        val headerJson = Json.encodeToString(mapOf("alg" to "EdDSA", "kid" to keyId))
        val now = Clock.System.now().epochSeconds
        val payloadJson = Json.encodeToString(JwtPayload(projectId, now - 30, now + 900))
        val headerEnc = base64UrlEncodeString(headerJson)
        val payloadEnc = base64UrlEncodeString(payloadJson)
        val data = "$headerEnc.$payloadEnc"
        val signature = getSign(data.encodeToByteArray())
        val signatureEnc = base64UrlEncode(signature)
        return@withContext "$data.$signatureEnc"
    }
}

/**
 * 返回对应平台的 [JwtSigner] 实现。
 *
 * @since 2.0.0 */
internal expect fun platformJwtSigner(
    keyId: String, projectId: String, privateKey: String
): JwtSigner

/** @since 2.0.0 */
internal fun base64UrlEncode(input: ByteArray): String = Base64.UrlSafe.encode(input).trimEnd('=')

/** @since 2.0.0 */
internal fun base64UrlEncodeString(str: String) = base64UrlEncode(str.encodeToByteArray())