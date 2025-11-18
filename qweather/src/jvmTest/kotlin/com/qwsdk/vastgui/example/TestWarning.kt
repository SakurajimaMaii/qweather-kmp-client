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

import com.qwsdk.vastgui.QWeather.CountryCode
import com.qwsdk.vastgui.example.base.requireCode
import com.qwsdk.vastgui.qw
import com.qwsdk.vastgui.utils.Coordinate
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class TestWarning {
    @Test
    fun currentTest() = runTest {
        qw.warning().current(Coordinate(117.20, 39.10)).onSuccess {
            println(it.alerts.joinToString())
            assertEquals(200, it.requireCode())
        }.onFailure {
            println(it)
            assertNull(it)
        }
    }

    @Test
    fun nowTest() = runTest {
        qw.warning().list(CountryCode.CN).getOrNull()
            ?.warningLocList
            ?.get(0)
            ?.getLocationID()
            ?.run {
                qw.warning().now(this).onSuccess {
                    it.warning.forEach { warning ->
                        println(warning)
                    }
                    assertEquals(200, it.requireCode())
                }.onFailure {
                    println(it)
                    assertNull(it)
                }
            }
    }

    @Test
    fun listTest() = runTest {
        qw.warning().list(CountryCode.CN).onSuccess {
            it.warningLocList.forEach { warningLocList ->
                println(warningLocList)
            }
            assertEquals(200, it.requireCode())
        }.onFailure {
            println(it)
            assertNull(it)
        }
    }
}