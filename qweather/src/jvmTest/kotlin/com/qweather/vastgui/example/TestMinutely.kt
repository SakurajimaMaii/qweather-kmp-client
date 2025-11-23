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

import com.qweather.vastgui.example.base.requireCode
import com.qweather.vastgui.qw
import com.qweather.vastgui.client.utils.Coordinate
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/16

class TestMinutely {
    private val coordinate = Coordinate(116.41, 39.92)

    @Test
    fun weatherMinutelyTest() = runTest {
        qw.minutely().rain(coordinate).onSuccess {
            it.minutely.forEach { minutely ->
                println(minutely)
            }
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }
}