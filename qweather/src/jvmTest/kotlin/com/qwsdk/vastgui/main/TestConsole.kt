package com.qwsdk.vastgui.main

import com.qwsdk.vastgui.api.Console
import com.qwsdk.vastgui.main.base.requireCode
import com.qwsdk.vastgui.qw
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
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