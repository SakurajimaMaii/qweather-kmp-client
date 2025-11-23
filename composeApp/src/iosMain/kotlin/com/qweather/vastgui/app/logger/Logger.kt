package com.qweather.vastgui.app.logger

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NSLogWriter

fun setupLogger() {
    Logger.setLogWriters(NSLogWriter())
}