package com.yxhuang.androidtest.api

import com.google.common.truth.Truth
import com.yxhuang.androidtest.data.remote.PixabayAPI
import com.yxhuang.androidtest.data.remote.responses.ImageResponse
import com.yxhuang.androidtest.utils.ObjectInstanceConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by yxhuang
 * Date: 2023/1/10
 * Description:
 */
class PixabyAPITest {

    @get:Rule
    val server = MockWebServer()

    private lateinit var pixabayAPI: PixabayAPI

    @Before
    fun setup(){
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        pixabayAPI = retrofit.create(PixabayAPI::class.java)
    }

    @Test
    fun testSearchForImage() = runBlocking {
        // 设置返回结果
        enqueueResponse("search.json")

        val result = pixabayAPI.searchForImage("test", "key")
        val imageResponse = result.body() as ImageResponse

        Truth.assertThat(imageResponse.total).isEqualTo(4)
        Truth.assertThat(imageResponse.hits[0].comments).isEqualTo(1)
    }

    private fun enqueueResponse(fileName:String ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        server.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
    }
}