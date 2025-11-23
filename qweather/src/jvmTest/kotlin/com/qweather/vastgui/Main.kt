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

package com.qweather.vastgui

import com.log.vastgui.core.base.Logger
import com.log.vastgui.core.getLogFactory
import com.log.vastgui.core.plugin.LogPrinter
import com.log.vastgui.core.plugin.LogSwitch
import com.log.vastgui.desktop.desktop
import com.qweather.vastgui.client.QWeather
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