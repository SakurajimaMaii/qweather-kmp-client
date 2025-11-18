package com.qwsdk.vastgui

import com.log.vastgui.core.base.Logger
import com.log.vastgui.core.getLogFactory
import com.log.vastgui.core.plugin.LogPrinter
import com.log.vastgui.core.plugin.LogSwitch
import com.log.vastgui.desktop.desktop
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/18

@Serializable
data class Jwt(val hostApi: String, val keyId: String, val projectId: String)

val logCat = getLogFactory {
    install(LogSwitch) {
        open = true
    }
    install(LogPrinter) {
        logger = Logger.desktop()
    }
}("QWeather")


val apikey = QWeather.Configuration(
    plan = QWeather.Plan.Standard,
    key = object {}.javaClass.getResource("/key/api-key.txt")!!.readText(),
    logger = { logCat.d(it) }
)

val jwt = run {
    val jwt = Json.decodeFromString<Jwt>(
        object {}.javaClass.getResource("/key/jwt.txt")!!.readText()
    )
    QWeather.Configuration(
        plan = QWeather.Plan.HostApi(jwt.hostApi),
        auth = QWeather.Authentication.Jwt(
            keyId = jwt.keyId,
            projectId = jwt.projectId,
            privateKey = object {}.javaClass.getResource("/key/ed25519-private.pem")!!.readText()
        ),
        logger = { logCat.d(it) }
    )
}

val qw = QWeather.getInstance(jwt)