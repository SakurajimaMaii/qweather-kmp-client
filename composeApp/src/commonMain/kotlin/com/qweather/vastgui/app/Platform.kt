package com.qweather.vastgui.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform