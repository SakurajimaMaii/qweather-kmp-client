/*
 * Copyright 2023 RTAkland
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

package com.qweather.vastgui.client.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateRange
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * @since 2.0.0
 */
internal object DateUtil {

    /**
     * yyyyMMdd 格式的时间。
     *
     * @since 2.0.0
     */
    @OptIn(FormatStringsInDatetimeFormats::class)
    internal val ymdFormat = LocalDate.Format { byUnicodePattern("yyyyMMdd") }

    /**
     * HHmm 格式的时间。
     *
     * @since 2.0.0
     */
    @OptIn(FormatStringsInDatetimeFormats::class)
    internal val hmFormat = LocalTime.Format { byUnicodePattern("HHmm") }

    /**
     * @since 2.0.0
     */
    @OptIn(ExperimentalTime::class)
    internal val now get() = Clock.System.now()

    /**
     * 判断 [date] 是否在 [range] 范围内。
     *
     * @since 2.0.0
     */
    @OptIn(ExperimentalTime::class)
    fun verifyYMD(date: String, range: LocalDateRange): Boolean {
        return ymdFormat.parseOrNull(date)?.let { it in range } ?: false
    }
}