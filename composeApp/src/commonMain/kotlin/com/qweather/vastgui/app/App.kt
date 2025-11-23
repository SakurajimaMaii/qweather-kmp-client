package com.qweather.vastgui.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.qweather.vastgui.client.QWeather
import com.qweather.vastgui.client.utils.Coordinate
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import qweather_kmp.composeapp.generated.resources.Res
import qweather_kmp.composeapp.generated.resources.compose_multiplatform
import qweather_kmp.composeapp.generated.resources.jwt
import qweather_kmp.composeapp.generated.resources.key

@Serializable
data class Jwt(val hostApi: String, val keyId: String, val projectId: String)

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var greeting by remember { mutableStateOf("") }
        val jwt = stringResource(Res.string.jwt)
        val key = stringResource(Res.string.key)

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }

            LaunchedEffect(Unit) {
                val jwt = run {
                    val jwt = Json.decodeFromString<Jwt>(jwt)
                    QWeather.Configuration(
                        plan = QWeather.Plan.HostApi(jwt.hostApi),
                        auth = QWeather.Authentication.Jwt(
                            keyId = jwt.keyId,
                            projectId = jwt.projectId,
                            privateKey = key
                        ),
                        logger = { Logger.d(it) }
                    )
                }

                QWeather.getInstance(jwt)
                    .air()
                    .now(Coordinate(117.20, 39.10))
                    .onSuccess {
                        greeting = it.toString()
                    }
                    .onFailure {
                        greeting = Coordinate(117.20, 39.10).location
                    }
            }
        }
    }
}