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

import com.qwsdk.vastgui.app.utils.randomID
import com.qwsdk.vastgui.utils.Coordinate
import com.qwsdk.vastgui.QWeather.POIType
import com.qwsdk.vastgui.qw
import com.qwsdk.vastgui.utils.LocationID
import com.qwsdk.vastgui.utils.Name
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull

class TestGeo {

    private val locationID = randomID()

    @Test
    fun citySearchTest() = runTest {
        qw.geo().cityLookup(LocationID(locationID)).onSuccess {
            it.location.forEach { location ->
                println(location)
            }
            assertEquals(200, if (it.error == null) 200 else it.code?.toInt())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun topCityTest() = runTest {
        qw.geo().topCity().onSuccess {
            it.topCityList.forEach { topCity ->
                println(topCity)
            }
            assertEquals(200, if (it.error == null) 200 else it.code?.toInt())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun poiLookupTest() = runTest {
        qw.geo().poiLookup(Name("青岛"), POIType.TSTA).onSuccess {
            it.poi.forEach { poi ->
                println(poi)
            }
            assertEquals(200, if (it.error == null) 200 else it.code?.toInt())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }

    @Test
    fun poiRangeTest() = runTest {
        qw.geo().poiRange(Coordinate(116.41, 39.92), POIType.scenic).onSuccess {
            it.poi.forEach { poi ->
                println(poi)
            }
            assertEquals(200, if (it.error == null) 200 else it.code?.toInt())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }
}