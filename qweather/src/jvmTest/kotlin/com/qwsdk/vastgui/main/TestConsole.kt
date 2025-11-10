package com.qwsdk.vastgui.main

import com.qwsdk.vastgui.api.Console
import com.qwsdk.vastgui.qw
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
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
        }.onFailure {
            println(it)
            assertEquals(expected = true, actual = false)
        }
    }

    @Test
    fun requestMetricsTest() = runTest {
        qw.console().metricsStatus(Console.Id.ProjectId("")).onSuccess {
            println(it)
        }.onFailure {
            println(it)
            assertEquals(expected = true, actual = false)
        }
    }

}