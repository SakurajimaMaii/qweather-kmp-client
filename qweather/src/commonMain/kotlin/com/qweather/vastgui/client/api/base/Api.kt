package com.qweather.vastgui.client.api.base

import com.qweather.vastgui.client.QWeather

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/12
// Reference:

/**
 * @since 2.0.0
 */
interface Api {

    /**
     * 客户端。
     *
     * @since 2.0.0
     */
    val client: QWeather

    /**
     *请求路径。
     *
     * @since 2.0.0
     */
    val url: String

}