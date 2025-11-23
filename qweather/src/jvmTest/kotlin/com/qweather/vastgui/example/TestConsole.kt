/*
 * Copyright 2025 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qweather.vastgui.example

import com.qweather.vastgui.client.api.Console
import com.qweather.vastgui.example.base.requireCode
import com.qweather.vastgui.qw
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull
import kotlin.test.assertEquals

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7
// Documentation:

class TestConsole {

    @Test
    fun financeTest() = runTest {
        qw.console().financeSummary().onSuccess {
            println(it)
            assertEquals(200, it.requireCode())
        }.onFailure {
            println(it)
            assertNull(it)
        }
    }

    @Test
    fun requestMetricsTest() = runTest {
        qw.console().metricsStatus(Console.Id.ProjectId("")).onSuccess {
            println(it)
            assertEquals(200, it.requireCode())
        }.onFailure {
            println(it)
            assertNull(it)
        }
    }

}