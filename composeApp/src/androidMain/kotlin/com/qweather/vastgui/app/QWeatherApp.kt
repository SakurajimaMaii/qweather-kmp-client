package com.qweather.vastgui.app

import android.app.Application
import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter

class QWeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setLogWriters(platformLogWriter())
    }

}