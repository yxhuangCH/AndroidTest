package com.yxhuang.androidtest.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withTimeout
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * Created by yxhuang
 * Date: 2022/7/16
 * Description:
 */
class ApiTest {

    suspend fun loadData1(api: Api) = withTimeout(5_000) {
        api.fetch()
    }


    @Test
    fun loadData1_Success() = runBlockingTest {
        val time = System.currentTimeMillis()
        val actual = loadData1(FakeApi())
        println("loadData1_Success time: ${System.currentTimeMillis() - time}")
        assertThat(actual).isEqualTo("Kotlin")
    }

    @Test
    fun loadData1_Success2() = runBlocking {
        val totalExecutionTime = measureTimeMillis {
            val actual = loadData1(FakeApi())
            assertThat(actual).isEqualTo("Kotlin")
        }
        println("totalExecutionTime : $totalExecutionTime")
    }


    @Test
    fun loadData1_Success3() = runBlockingTest { // 会自动跳过 delay
        val totalExecutionTime = measureTimeMillis {
            val actual = loadData1(FakeApi())
            assertThat(actual).isEqualTo("Kotlin")
        }
        println("totalExecutionTime : $totalExecutionTime")
    }
}