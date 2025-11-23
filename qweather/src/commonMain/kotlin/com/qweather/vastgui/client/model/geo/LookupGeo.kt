package com.qweather.vastgui.client.model.geo

import com.qweather.vastgui.client.model.base.BaseResponse
import com.qweather.vastgui.client.model.base.Refer
import com.qweather.vastgui.client.model.base.error.ErrorInfo
import com.qweather.vastgui.client.utils.GeoLocationID
import com.qweather.vastgui.client.utils.LocationID
import kotlinx.serialization.Serializable

/**
 * [城市搜索](https://dev.qweather.com/docs/api/geoapi/city-lookup/)
 *
 * @property code 请参考
 * [状态码](https://dev.qweather.com/docs/resource/status-code/) 。
 * @property location 参考 [Location] 。
 * @property refer 参考 [Refer] 。
 * @since 2.0.0
 */
@Serializable
data class LookupGeo(
    val location: List<Location> = emptyList(),
    val refer: Refer? = null,
    @Deprecated("建议使用 ErrorInfo.status", level = DeprecationLevel.WARNING)
    override val code: String? = null,
    override val error: ErrorInfo? = null
) : BaseResponse {
    /**
     * [城市搜索](https://dev.qweather.com/docs/api/geoapi/city-lookup/)
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
     * @property utcOffset 地区/城市目前与UTC时间偏移的小时数。
     * @since 2.0.0
     */
    @Serializable
    data class Location(
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