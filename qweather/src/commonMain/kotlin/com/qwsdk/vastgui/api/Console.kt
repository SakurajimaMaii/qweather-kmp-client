package com.qwsdk.vastgui.api

import com.qwsdk.vastgui.QWeather
import com.qwsdk.vastgui.entity.console.FinanceSummary
import com.qwsdk.vastgui.entity.console.RequestMetrics
import com.qwsdk.vastgui.utils.apiCatching
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7
// Documentation:

/**
 * [控制台 API](https://dev.qweather.com/docs/api/console/)
 *
 * 帐号所有者可以为指定凭据开启控制台API权限，以便轻松的在本地访问控制台数据，了解当前财务和请求量统计。
 *
 * 启用控制台 API ：
 *
 * 默认情况下，所有凭据均没有权限请求控制台 API ，你必须在凭据设置中启用控制台 API 才可以请求对应的数据。
 * 1. 前往控制台-项目管理。
 * 2. 点击需要启用控制台 API 的凭据。
 * 3. 向下滑动至“控制台权限”。
 * 4. 勾选需要启用的控制台 API 权限。
 * 5. 点击“保存”按钮。
 *
 * @since 1.1.3
 */
class Console internal constructor(private val client: QWeather) {

    /**
     * [财务汇总](https://dev.qweather.com/docs/api/console/finance/)
     *
     * 查询你的财务和计费的汇总信息。
     *
     * 提示：返回的数据截止至上一个小时或更早时候，它与控制台中显示的数据相比可能有1个小时或更长的延迟。
     *
     * 必须由帐号所有者在控制台为凭据开通权限后才可以访问本接口。
     * 1. 前往控制台-项目管理
     * 2. 点击需要启用控制台API的凭据
     * 3. 向下滑动至“控制台权限”
     * 4. 勾选“允许访问财务汇总数据”
     * 5. 点击“保存”按钮
     *
     * @see QWeather.Plan.HostApi
     * @since 1.1.3
     */
    @Throws(IllegalStateException::class)
    suspend fun financeSummary(): Result<FinanceSummary> = apiCatching {
        client.httpClient.get("https://${client.apiPlan.host}/finance/v1/summary") {
            parameter("key", client.apiKey)
        }.body()
    }

    /**
     * [请求量统计](https://dev.qweather.com/docs/api/console/stats/)
     *
     * 查询最近 24 小时的 API 请求量统计。
     *
     * 提示：返回的数据截止至上一个小时或更早时候，它与控制台中显示的数据相比可能有 1 个小时或更长的延迟。
     *
     * 访问本接口必须由帐号所有者在控制台为凭据开通权限。
     * 1. 前往控制台-项目管理。
     * 2. 点击需要启用控制台 API 的凭据。
     * 3. 向下滑动至“控制台权限”。
     * 4. 在“指标和统计”中勾选所需要的权限：。
     *    - “允许访问请求量汇总统计”，汇总所有项目和凭据的请求量，包括已删除的。
     *    - “允许访问指定项目的请求量统计”，使用 project 参数过滤。
     *    - “允许访问指定凭据的请求量统计”，使用 credential 参数过滤。
     * 5. 点击“保存”按钮。
     *
     * 请注意：已删除的项目或凭据无法单独查询（例如使用 project 或 credential 参数），但其请求量仍计入汇总统计中。
     *
     * 请求路径
     *
     * @since 1.1.3
     */
    suspend fun metricsStatus(id: Id): Result<RequestMetrics> = apiCatching {
        client.httpClient.get("https://${client.apiPlan.host}/metrics/v1/stats") {
            parameter("key", client.apiKey)
            when (id) {
                is Id.ProjectId -> parameter("project", id.id)
                is Id.CredentialId -> parameter("credential", id.id)
            }
        }.body()
    }

    /**
     * [请求量统计支持的 id
     * 类型](https://dev.qweather.com/docs/api/console/stats/#parameters)。
     *
     * @since 1.1.3
     */
    sealed class Id(val id: String) {
        class ProjectId(id: String) : Id(id)
        class CredentialId(id: String) : Id(id)
    }

}