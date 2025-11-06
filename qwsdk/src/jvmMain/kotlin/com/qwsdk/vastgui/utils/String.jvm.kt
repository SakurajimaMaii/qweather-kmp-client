package com.qwsdk.vastgui.utils

actual fun String.Companion.format(format: String, vararg args: Any?): String {
    return format.format(args)
}