package com.qwsdk.vastgui.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform