package com.qwsdk.vastgui.entity.geo

import com.qwsdk.vastgui.entity.base.BaseResponse
import com.qwsdk.vastgui.entity.base.error.ErrorInfo
import com.qwsdk.vastgui.entity.base.Refer
import com.qwsdk.vastgui.utils.GeoLocationID
import com.qwsdk.vastgui.utils.LocationID
import kotlinx.serialization.Serializable

/**
 * [热门城市查询](https://dev.qweather.com/docs/api/geoapi/top-city/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property refer 参考 [Refer] 。
 * @property topCityList 参考 [TopCity] 。
 * @since 1.1.3
 */
@Serializable
data class TopGeo(
    val refer: Refer? = null,
    val topCityList: List<TopCity> = emptyList(),
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [热门城市](https://dev.qweather.com/docs/api/geoapi/top-city/)
     *
     * @property adm1 地区/城市所属一级行政区域。
     * @property adm2 地区/城市的上级行政区划名称。
     * @property country 地区/城市所属国家名称。
     * @property fxLink 该地区的天气预报网页链接，便于嵌入你的网站或应用。
     * @property id 地区/城市ID。
     * @property isDst 地区/城市是否当前处于
     * [夏令时](https://dev.qweather.com/docs/resource/glossary/#daylight-saving-time)。
     * 1 表示当前处于夏令时， 0 表示当前不是夏令时。
     * @property lat 地区/城市纬度。
     * @property lon 地区/城市经度。
     * @property name 地区/城市名称。
     * @property rank
     * [地区评分](https://dev.qweather.com/docs/resource/glossary/#rank) 。
     * @property type 地区/城市的属性。
     * @property tz 地区/城市所在
     * [时区](https://dev.qweather.com/docs/resource/glossary/#timezone) 。
     * @property utcOffset 地区/城市目前与 UTC 时间偏移的小时数。
     * @since 1.1.3
     */
    @Serializable
    data class TopCity(
        val adm1: String? = null,
        val adm2: String? = null,
        val country: String? = null,
        val fxLink: String? = null,
        val id: String? = null,
        val isDst: String? = null,
        val lat: String? = null,
        val lon: String? = null,
        val name: String? = null,
        val rank: String? = null,
        val type: String? = null,
        val tz: String? = null,
        val utcOffset: String? = null
    ) : GeoLocationID {
        override fun getLocationID(): LocationID? = id?.let(::LocationID)
    }
}