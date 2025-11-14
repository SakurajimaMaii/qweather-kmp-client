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

## 支持的平台

|   平台   | Android |  IOS  | Desktop |
| :------: | :-----: | :---: | :-----: |
| 当前状态 |    ✅    |   👷(开发中)   |    ✅    |

## 当前支持的 Api

