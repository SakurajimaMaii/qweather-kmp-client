package com.qweather.vastgui.client.model.base

import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/13

/**
 * @property license 原始数据来源，或数据源说明，可能为空。
 * @property sources 数据许可或版权声明，可能为空。
 */
@Serializable
data class Refer(
    val license: List<String> = emptyList(),
    val sources: List<String> = emptyList()
)