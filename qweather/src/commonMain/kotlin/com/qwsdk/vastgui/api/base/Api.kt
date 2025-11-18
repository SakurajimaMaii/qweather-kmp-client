package com.qwsdk.vastgui.api.base

import com.qwsdk.vastgui.QWeather

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/12
// Reference:

/**
 * @since 1.1.3
 */
interface Api {

    /**
     * 客户端。
     *
     * @since 1.1.3
     */
    val client: QWeather

    /**
     *请求路径。
     *
     * @since 1.1.3
     */
    val url: String

}