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
import com.qwsdk.vastgui.app.utils.getCurrentYear
import com.qwsdk.vastgui.main.base.requireCode
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class TestTropical {
    @Test
    fun forecastTest() = runTest {
        qw.tropical().list(getCurrentYear()).getOrNull()?.storm?.get(0)?.getStormId()?.run {
            qw.tropical().forecast(this).onSuccess {
                it.forecast.forEach { forecast ->
                    println(forecast)
                }
                assertEquals(200, it.requireCode())
            }.onFailure {
                println(it)
                assertNull(it)
            }
        }
    }

    @Test
    fun trackTest() = runTest {
        qw.tropical().list(getCurrentYear()).getOrNull()?.storm?.get(0)?.getStormId()?.run {
            qw.tropical().track(this).onSuccess {
                it.track.forEach { track ->
                    println(track)
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
        qw.tropical().list(getCurrentYear()).onSuccess {
            it.storm.forEach { track ->
                println(track)
            }
            assertEquals(200, it.requireCode())
        }.onFailure { t ->
            println(t)
            assertNull(t)
        }
    }
}