package com.yxhuang.androidtest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by yxhuang
 * Date: 2022/5/2
 * Description:
 */
class ResourceComparserTest{

    private lateinit var resourceComparser: ResourceComparser

    @Before
    fun setup(){
        resourceComparser = ResourceComparser()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparser.isEqual(context, R.string.app_name, "AndroidTest")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDiffAsGivenString_returnsFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparser.isEqual(context, R.string.app_name, "test")
        assertThat(result).isFalse()
    }
}