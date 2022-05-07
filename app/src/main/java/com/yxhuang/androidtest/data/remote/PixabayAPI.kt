package com.yxhuang.androidtest.data.remote

import com.yxhuang.androidtest.BuildConfig
import com.yxhuang.androidtest.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.Query

/**
 * Created by yxhuang
 * Date: 2022/5/3
 * Description:
 */
interface PixabayAPI {

    suspend fun searchForImage(
        @Query("q") searchQuery:String,
        @Query("key") apiKey:String = BuildConfig.API_KEY
    ):Response<ImageResponse>
}