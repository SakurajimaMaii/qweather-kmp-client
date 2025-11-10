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

package com.qwsdk.vastgui.main

import com.qwsdk.vastgui.api.SolarRadiation
import com.qwsdk.vastgui.qw
import com.qwsdk.vastgui.utils.Coordinate
import com.qwsdk.vastgui.utils.Hour
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2024/1/17

class TestSolarRadiation {
    @Test
    fun radiationTest() = runTest {
        qw.solarRadiation()
            .radiation(Hour.Hour24, Coordinate(116.41, 39.92))
            .onSuccess {
                it.radiation.forEach { radiation ->
                    println(radiation)
                }
                assertEquals(it.code.toInt(), 200)
            }
            .onFailure {
                println(it)
            }
    }

    @Test
    fun solarRadiation() = runTest {
        qw.solarRadiation().radiation(Coordinate(116.41, 39.92), tilt = 30, azimuth = 180, extra = SolarRadiation.SolarRadiationExtra.Poa)
            .onSuccess {
                it.forecasts?.forEach { radiation ->
                    println(radiation)
                }
            }
            .onFailure {
                println(it)
                assertEquals(false,true)
            }
    }
}