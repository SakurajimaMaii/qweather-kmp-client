package com.qwsdk.vastgui.entity.console

import com.qwsdk.vastgui.entity.base.BaseResponse
import com.qwsdk.vastgui.entity.base.error.ErrorInfo
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7

/**
 * [财务汇总](https://dev.qweather.com/docs/api/console/finance/) 。
 *
 * @property accruedCharges 应计费用信息。
 * @property asOf 当前数据的截止日期。
 * @property availableResourcePlans 生效中或待生效的资源包列表。
 * @property availableSavingsPlans 生效中或待生效的节省计划列表。
 * @property balance 可用额度。
 * @property currency 货币代码，包括：CNY 和 USD。
 * @property metadata 元信息。
 * @property pendingBills 待支付账单列表。
 * @since 1.1.3
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class FinanceSummary(
    val accruedCharges: AccruedCharges? = null,
    val asOf: String? = null,
    val availableResourcePlans: List<AvailableResourcePlan> = emptyList(),
    val availableSavingsPlans: List<AvailableSavingsPlan> = emptyList(),
    val balance: Double? = null,
    val currency: String? = null,
    val metadata: Metadata? = null,
    val pendingBills: List<PendingBill> = emptyList(),
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {

    /**
     * 应计费用信息。
     *
     * @property previousDay 前一天应计费用总额。
     * @property thisMonth 本月应计费用总额。
     * @property sinceLastBill 从上次出账以来的应计费用总额。
     * @since 1.1.3
     */
    @Serializable
    data class AccruedCharges(
        val previousDay: Double? = null,
        val thisMonth: Double? = null,
        val sinceLastBill: Double? = null
    )

    /**
     * 生效中或待生效的节省计划信息。
     *
     * @property billNumber 节省计划账单号。
     * @property status 节省计划状态，包括 pending 和 active。
     * @property term 承诺期限。
     * @property commitments 承诺金额。
     * @property utilized 已用承诺金额。
     * @property effectiveTime 生效时间。
     * @since 1.1.3
     */
    @Serializable
    data class AvailableSavingsPlan(
        val billNumber: String? = null,
        val status: String? = null,
        val term: String? = null,
        val commitments: Int? = null,
        val utilized: Int? = null,
        val effectiveTime: String? = null
    )

    /**
     * 生效中或待生效的资源包信息。
     *
     * @property billNumber 资源包账单号。
     * @property status 资源包状态，包括 pending 和 active。
     * @property requests 总请求量。
     * @property utilized 已用请求量。
     * @property effectiveTime 生效时间。
     * @since 1.1.3
     */
    @Serializable
    data class AvailableResourcePlan(
        val billNumber: String? = null,
        val status: String? = null,
        val requests: Int? = null,
        val utilized: Int? = null,
        val effectiveTime: String? = null
    )

    /**
     * 元信息。
     *
     * @property tag 元数据标识。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(val tag: String? = null)

    /**
     * 待支付账单信息。
     *
     * @property number 待支付账单号。
     * @property type 待支付账单的类型。
     * @property amount 账单的总金额。
     * @property amountDue 账单剩余应付金额。
     * @property date 账单日期。
     * @property dueDate 应付日期。
     * @property status 账单状态。
     * @since 1.1.3
     */
    @Serializable
    data class PendingBill(
        val number: String? = null,
        val type: String? = null,
        val amount: Double? = null,
        val amountDue: Double? = null,
        val date: String? = null,
        val dueDate: String? = null,
        val status: String? = null
    )
}