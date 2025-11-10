package com.qwsdk.vastgui.entity.console

import com.qwsdk.vastgui.entity.BaseResponse
import com.qwsdk.vastgui.entity.ErrorInfo
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2025/11/7

/**
 * 账单汇总信息。
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
    @EncodeDefault
    val accruedCharges: AccruedCharges = AccruedCharges(),
    @EncodeDefault
    val asOf: String = "",
    @EncodeDefault
    val availableResourcePlans: List<AvailableResourcePlan> = emptyList(),
    @EncodeDefault
    val availableSavingsPlans: List<AvailableSavingsPlan> = emptyList(),
    @EncodeDefault
    val balance: Double = 0.0,
    @EncodeDefault
    val currency: String = "",
    @EncodeDefault
    val metadata: Metadata = Metadata(),
    @EncodeDefault
    val pendingBills: List<PendingBill> = emptyList(),
    override val error: ErrorInfo? = null
): BaseResponse {

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
        @EncodeDefault
        val previousDay: Double = 0.0,
        @EncodeDefault
        val thisMonth: Double = 0.0,
        @EncodeDefault
        val sinceLastBill: Double = 0.0
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
        @EncodeDefault
        val billNumber: String = "",
        @EncodeDefault
        val status: String = "",
        @EncodeDefault
        val term: String = "",
        @EncodeDefault
        val commitments: Int = 0,
        @EncodeDefault
        val utilized: Int = 0,
        @EncodeDefault
        val effectiveTime: String = ""
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
        @EncodeDefault
        val billNumber: String = "",
        @EncodeDefault
        val status: String = "",
        @EncodeDefault
        val requests: Int = 0,
        @EncodeDefault
        val utilized: Int = 0,
        @EncodeDefault
        val effectiveTime: String = ""
    )

    /**
     * 元信息。
     *
     * @property tag 元数据标识。
     * @since 1.1.3
     */
    @Serializable
    data class Metadata(
        @EncodeDefault
        val tag: String = ""
    )

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
        @EncodeDefault
        val number: String = "",
        @EncodeDefault
        val type: String = "",
        @EncodeDefault
        val amount: Double = 0.0,
        @EncodeDefault
        val amountDue: Double = 0.0,
        @EncodeDefault
        val date: String = "",
        @EncodeDefault
        val dueDate: String = "",
        @EncodeDefault
        val status: String = ""
    )
}