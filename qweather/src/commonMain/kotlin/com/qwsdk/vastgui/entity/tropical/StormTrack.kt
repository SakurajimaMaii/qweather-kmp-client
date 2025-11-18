package com.qwsdk.vastgui.entity.tropical

import com.qwsdk.vastgui.entity.base.BaseResponse
import com.qwsdk.vastgui.entity.base.error.ErrorInfo
import com.qwsdk.vastgui.entity.base.Refer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

/**
 * [台风实况和路径](https://dev.qweather.com/docs/api/tropical-cyclone/storm-track/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property fxLink 当前数据的响应式页面，便于嵌入网站或应用。
 * @property isActive 是否为活跃台风。1 活跃台风，0 停编。
 * @property now 参考 [Now] ，如果台风已经停止，即 [isActive]=0 ，接口中的 now 字段将不再返回数据 。
 * @property refer 参考 [Refer] 。
 * @property track 参考 [Track] 。
 * @property updateTime 当前
 * [API的最近更新时间](https://dev.qweather.com/docs/resource/glossary/#update-time)
 * 。
 */
@Serializable
data class StormTrack(
    val fxLink: String? = null,
    val isActive: String? = null,
    val now: Now? = null,
    val refer: Refer? = null,
    val track: List<Track> = emptyList(),
    val updateTime: String? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [台风实况和路径](https://dev.qweather.com/docs/api/tropical-cyclone/storm-track/)
     *
     * @property lat 台风所处纬度。
     * @property lon 台风所处经度。
     * @property move360 台风移动方位 360 度方向。
     * @property moveDir 台风移动方位。
     * @property moveSpeed 台风移动速度。
     * @property pressure 台风中心气压。
     * @property pubTime 台风信息发布时间。
     * @property type 台风类型。
     * @property windRadius30 台风 7 级风圈半径 。
     * @property windRadius50 台风 10 级风圈半径 。
     * @property windRadius64 台风 12 级风圈半径 。
     * @property windSpeed 台风附近最大风速。
     */
    @Serializable
    data class Now(
        val lat: String? = null,
        val lon: String? = null,
        val move360: String? = null,
        val moveDir: String? = null,
        val moveSpeed: String? = null,
        val pressure: String? = null,
        val pubTime: String? = null,
        val type: String? = null,
        val windRadius30: WindRadius30? = null,
        val windRadius50: WindRadius30? = null,
        val windRadius64: WindRadius30? = null,
        val windSpeed: String? = null
    )

    /**
     * [台风实况和路径](https://dev.qweather.com/docs/api/tropical-cyclone/storm-track/)
     *
     * @property lat 台风所处纬度。
     * @property lon 台风所处经度。
     * @property move360 台风移动方位 360 度方向。
     * @property moveDir 台风移动方位。
     * @property moveSpeed 台风移动速度。
     * @property pressure 台风中心气压。
     * @property time 当前台风信息发布时间。
     * @property type 台风类型。
     * @property windRadius30 台风 7 级风圈半径 。
     * @property windRadius50 台风 10 级风圈半径 。
     * @property windRadius64 台风 12 级风圈半径 。
     * @property windSpeed 台风附近最大风速。
     */
    @Serializable
    @OptIn(ExperimentalSerializationApi::class)
    data class Track(
        val lat: String? = null,
        val lon: String? = null,
        val move360: String? = null,
        val moveDir: String? = null,
        val moveSpeed: String? = null,
        val pressure: String? = null,
        val time: String? = null,
        val type: String? = null,
        val windRadius30: WindRadius30? = null,
        val windRadius50: WindRadius30? = null,
        val windRadius64: WindRadius30? = null,
        val windSpeed: String? = null
    )

    /**
     * [台风实况和路径](https://dev.qweather.com/docs/api/tropical-cyclone/storm-track/)
     *
     * @property neRadius 台风风圈东北半径。
     * @property nwRadius 台风风圈西北半径。
     * @property seRadius 台风风圈东南半径。
     * @property swRadius 台风风圈西南半径。
     */
    @Serializable
    data class WindRadius30(
        val neRadius: String? = null,
        val nwRadius: String? = null,
        val seRadius: String? = null,
        val swRadius: String? = null
    )
}