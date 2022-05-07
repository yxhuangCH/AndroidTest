package com.yxhuang.androidtest.data.remote.responses

/**
 * Created by yxhuang
 * Date: 2022/5/3
 * Description:
 */
data class ImageResponse(
    val hits:List<ImageResult>,
    val total:Int,
    val totalHits:Int
)