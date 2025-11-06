package com.qwsdk.vastgui.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "QWSdk",
    ) {
        App()
    }
}