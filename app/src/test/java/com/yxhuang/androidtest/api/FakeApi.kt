package com.yxhuang.androidtest.api

import kotlinx.coroutines.delay

/**
 * Created by yxhuang
 * Date: 2022/7/16
 * Description:
 */
class FakeApi : Api {

    override suspend fun fetch(): String {
        delay(3_000)
        println("FakeApi fetch")
        return "Kotlin"
    }
}