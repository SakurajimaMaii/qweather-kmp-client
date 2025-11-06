/*
 * Copyright 2024 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qwsdk.vastgui

import com.log.vastgui.core.base.Logger
import com.log.vastgui.core.getLogFactory
import com.log.vastgui.core.plugin.LogPrinter
import com.log.vastgui.core.plugin.LogSwitch
import com.log.vastgui.desktop.desktop
import com.qwsdk.vastgui.QWSdk.Plan

val logCat = getLogFactory {
    install(LogSwitch) {
        open = true
    }
    install(LogPrinter) {
        logger = Logger.desktop()
    }
}("QWSdk")

// 新增日志打印
val configuration = QWSdk.Configuration(Plan.Standard, "e335b9063d7547c0bb2b14fe2468baee") {
    logCat.d(it)
}

val qw = QWSdk.getInstance(configuration)