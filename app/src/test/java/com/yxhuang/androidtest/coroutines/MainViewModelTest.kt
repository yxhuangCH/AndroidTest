package com.yxhuang.androidtest.coroutines

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.common.truth.Truth.assertThat
import com.yxhuang.androidtest.utils.MainCoroutineRule
import com.yxhuang.androidtest.utils.getOrAwaitValueTest
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import kotlin.system.measureTimeMillis

/**
 * Created by yxhuang
 * Date: 2022/7/8
 * Description:
 *  来源： https://medium.com/swlh/unit-testing-with-kotlin-coroutines-the-android-way-19289838d257
 *  使用 kotlinx-coroutines-test:1.4.2
 *  参考 https://github.com/Kotlin/kotlinx.coroutines/tree/1.4.2/kotlinx-coroutines-test
 *
 */
class MainViewModel(
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private var isSessionExpired = false

    suspend fun checkSessionExpiry(): Boolean {
        delay(5_000)
        isSessionExpired = true
        return isSessionExpired
    }

    private var userData: Any? = null
    fun getUserData(): Any? = userData

    suspend fun saveSessionData() {
        viewModelScope.launch(dispatcher) { // viewModelScope 需要， mainCoroutineRule
            userData = "some_user_data"
        }
    }

    private var _userLogin = MutableLiveData<Boolean>()
    val userLogin :MutableLiveData<Boolean> = _userLogin
    suspend fun login(){
        viewModelScope.launch(Dispatchers.IO){
             delay(3_000)
            _userLogin.postValue(true)
        }
    }

}

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule() // 解决  viewModelScope.launch

    private val testCoroutineDispatcher = mainCoroutineRule.testDispatcher

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
     // 解决 Method getMainLooper in android.os.Looper not mocked. See http://g.co/androidstudio/not-mocked for details.

    @Before
    fun setup(){
        println("setup")
    }

    @After
    fun teardown(){
        println("teardown")
    }

    // runBlocking 不会跳过 delay 的时间
    @Test
    fun testCheckSessionExpired() = runBlocking {
        val mainViewModel = MainViewModel(testCoroutineDispatcher)

        val totalExecutionTime = measureTimeMillis {
            val isSessionExpired = mainViewModel.checkSessionExpiry()
            assertThat(isSessionExpired).isTrue()
        }
        println("totalExecutionTime 1: $totalExecutionTime")
    }

    //  runBlockingTest 会跳过 delay,
    @Test
    fun testCheckSessionExpired2() = runBlockingTest {
        val mainViewModel = MainViewModel(testCoroutineDispatcher)
        val totalExecutionTime = measureTimeMillis {
            val isSessionExpired = mainViewModel.checkSessionExpiry()
            assertThat(isSessionExpired).isTrue()
        }
        println("totalExecutionTime 2: $totalExecutionTime")
    }


    @Test
    fun testSaveSessionData() = runBlockingTest {
        val mainViewModel = MainViewModel(testCoroutineDispatcher)

        mainViewModel.saveSessionData()

        val userData = mainViewModel.getUserData()

        assertThat(userData).isEqualTo("some_user_data")
    }

    // runBlocking 不会跳过 delay 的时间
    @Test
    fun testCheckSessionExpired3() = runBlocking{
        val mainViewModel = MainViewModel(testCoroutineDispatcher)
        val isSessionExpired = mainViewModel.checkSessionExpiry()
        assertThat(isSessionExpired).isTrue()
    }

    @Test
    fun testLogin() = mainCoroutineRule.runBlockingTest {

        val mainViewModel = MainViewModel(testCoroutineDispatcher)

        println("testLogin : ${Thread.currentThread().name}")

        mainViewModel.login()

        val value = mainViewModel.userLogin.getOrAwaitValueTest {
            advanceTimeBy(3_000)
            // or
//            advanceUntilIdle()
        }

        assertThat(value).isTrue()
    }


}
