# QWeather-KMP

基于 [QWeather](https://www.qweather.com/) Web Api 和 [Ktor](https://ktor.io/) 开发的 SDK ，
面向 [Kotlin Multiplatform](https://www.jetbrains.com/zh-cn/kotlin-multiplatform/) 。

## 快速使用

### 创建客户端

#### 基于 HostApi 和 Jwt 认证：

```kotlin
val jwt = Configuration(
    Plan.HostApi("abc1234xyz.def.qweatherapi.com"),
    QWeather.Authentication.Jwt("key-id", "project-id", "private-key")
)
val qw = QWeather.getInstance(jwt)
```

#### 基于 Api-Key

```kotlin
val key = Configuration(
    Plan.HostApi("abc1234xyz.def.qweatherapi.com"),
    ApiKey("api-key")
)
val qw = QWeather.getInstance(key)
```

### 发送请求

```kotlin
qw.air().daily(LocationID(locationId), Lang.EN)
    .onSuccess { println(it.daily.joinToString()) }
    .onFailure { println(it) }
```

> [!NOTE]
> 更多请求请参考[示例](https://github.com/SakurajimaMaii/qweather-kmp/tree/main/qweather/src/jvmTest/kotlin/com/qwsdk/vastgui/example)部分

## 支持的平台

|   平台   | Android |  IOS  | Desktop |
| :------: | :-----: | :---: | :-----: |
| 当前状态 |    ✅    |   👷(开发中)   |    ✅    |

## 当前支持的 Api

- GeoAPI

    和风天气 GeoAPI 提供全球地理位位置、全球城市搜索服务，支持经纬度坐标反查、多语言、模糊搜索等功能。

    - [X] 城市搜索
    - [X] 热门城市查询
    - [X] POI 搜索
    - [X] POI 范围搜索

- 天气预报

    天气 API 提供全球 20 多万个城市的实时天气和预报数据，并支持基于数值模式的天气预报，分辨率达 3–5 公里，覆盖全球坐标点。

    - [X] 实时天气
    - [X] 每日天气预报
    - [X] 逐小时天气预报
    - [X] 格点实时天气
    - [X] 格点每日天气预报
    - [X] 格点逐小时天气预报

- 分钟预报

    分钟级降水 API （临近预报）支持中国 1 公里精度的分钟级降雨预报数据，为每一分钟的降雨进行精准预测。

    - [X] 分钟级降水

- 预警

    和风极端天气预警 API 提供了全球官方发布的极端天气预警服务，覆盖中国及全球国家或地区。

    - [X] 实时天气预警

- 天气指数

    天气生活指数包括洗车指数、穿衣指数、感冒指数、过敏指数、紫外线指数、钓鱼指数等数据。天气指数支持中国 3000+ 个市县区和海外 15 万个城市天气预报。

    - [X] 天气指数预报

- 空气质量

    全球空气质量 API，适配当地空气质量标准，可以轻松的获取指定位置的空气质量、污染物和健康建议。目前已经覆盖 100 多个国家或地区数据，包括实时和预报数据，分辨率为 1x1 公里。

    - [X] 实时空气质量
    - [X] 空气质量小时预报
    - [X] 空气质量每日预报
    - [X] 监测站数据
 
- 时光机

    时光机可以获取最近 10 天的历史天气和空气质量数据。

    - [X] 天气时光机
    - [X] 空气质量时光机

- 热带气旋（台风）

    热带气旋（台风）API提供全球主要海洋流域的台风信息，包括台风实时位置、等级、气压、风速，还可查询台风路径和台风预报信息。

    - [X] 台风预报
    - [X] 台风实况和路径
    - [X] 台风列表

- 海洋数据

    海洋数据 API 提供全球主要港口和城市的潮汐数据。

    - [X] 潮汐

- 太阳辐射

    太阳辐射 API 支持获取全球辐射数据，包括 DNI、DHI、GHI 以及相关联的气象数据，最高 15 分钟间隔， 1x1 公里分辨率。

    - [X] 太阳辐射预报

- 天文

    天文API提供了全球任意地点未来60天的日出日落、太阳高度角、月升月落和月相数据。

    - [X] 日出日落
    - [X] 月升月落和月相
    - [X] 太阳高度角

- 控制台API

    帐号所有者可以为指定凭据开启控制台 API 权限，以便轻松的在本地访问控制台数据，了解当前财务和请求量统计。

    - [X] 财务汇总
    - [X] 请求量统计