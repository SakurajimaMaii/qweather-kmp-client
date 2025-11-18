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

package com.qwsdk.vastgui.example

import com.qwsdk.vastgui.QWeather.Lang
import com.qwsdk.vastgui.qw
import com.qwsdk.vastgui.app.utils.randomID
import com.qwsdk.vastgui.example.base.requireCode
import com.qwsdk.vastgui.utils.Coordinate
import com.qwsdk.vastgui.utils.LocationID
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class TestAir {
    private val locationId = randomID()

    @Test
    @Suppress("DEPRECATION")
    fun airTest() = runTest {
        qw.air().now(LocationID(locationId), Lang.EN).onSuccess {
            it.station.forEach { station ->
                println(station)
            }
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    @Suppress("DEPRECATION")
    fun dailyTest() = runTest {
        qw.air().daily(LocationID(locationId), Lang.EN).onSuccess {
            it.daily.forEach { daily ->
                println(daily)
            }
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun airNowTest() = runTest {
        qw.airQuality().current(Coordinate(117.20, 39.10)).onSuccess {
            it.stations.forEach { station ->
                println(station)
            }
            @Suppress("DEPRECATION")
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun hourlyTest() = runTest {
        qw.airQuality().hourly(Coordinate(117.20, 39.10)).onSuccess {
            it.hours.forEach { station ->
                println(station)
            }
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun dailiesTest() = runTest {
        qw.airQuality().daily(Coordinate(117.20, 39.10)).onSuccess {
            it.days.forEach { station ->
                println(station)
            }
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun stationTest() = runTest {
        qw.airQuality().station(LocationID("P58911")).onSuccess {
            it.pollutants.forEach { pollutant ->
                println(pollutant)
            }
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }
}