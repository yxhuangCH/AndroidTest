package com.yxhuang.androidtest.api

/**
 * Created by yxhuang
 * Date: 2022/7/16
 * Description:
 */
interface Api {

    suspend fun fetch(): String
}