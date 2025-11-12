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

import com.qwsdk.vastgui.qw
import com.qwsdk.vastgui.app.utils.getCurrentDate
import com.qwsdk.vastgui.app.utils.randomID
import com.qwsdk.vastgui.utils.Coordinate
import com.qwsdk.vastgui.utils.LocationID
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class TestAstronomy {
    private val locationID = randomID()

    @Test
    fun sunTest() = runTest {
        qw.astronomy().sun(LocationID(locationID), getCurrentDate()).onSuccess {
            println("${it.sunrise} ${it.sunset}")
            assertEquals(200, if (it.error == null) 200 else it.code?.toInt())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun moonTest() = runTest {
        qw.astronomy().moon(LocationID(locationID), getCurrentDate()).onSuccess {
            println("${it.moonrise} ${it.moonset} ${it.moonPhase}")
            assertEquals(200, if (it.error == null) 200 else it.code?.toInt())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun solarElevationAngleTest() = runTest {
        qw.astronomy().solarElevationAngle(
            Coordinate(120.34, 36.08), "20240117", "1230", "0800", 43
        ).onSuccess {
            println("${it.solarAzimuthAngle} ${it.solarElevationAngle} ${it.hourAngle}")
            assertEquals(200, if (it.error == null) 200 else it.code?.toInt())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }
}