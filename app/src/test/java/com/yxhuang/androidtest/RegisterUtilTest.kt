package com.yxhuang.androidtest

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Created by yxhuang
 * Date: 2022/4/29
 * Description:
 */
class RegisterUtilTest{


    @Test
    fun `empty username return false`(){
        val result = RegisterUtil.validateRegistrationInput(
            "",
            "12345",
            "12345"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password return true`(){
        val result = RegisterUtil.validateRegistrationInput(
            "yxhuang",
            "12345",
            "12345"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists return false`(){
        val result = RegisterUtil.validateRegistrationInput(
            "Tom",
            "12345",
            "12345"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password returns false`(){
        val result = RegisterUtil.validateRegistrationInput(
            "yxhunag",
            "123456",
            "adcdefg"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result = RegisterUtil.validateRegistrationInput(
            "yxhuang",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digit password returns false`(){
        val result  = RegisterUtil.validateRegistrationInput(
            "yxhunag",
                        "abcde1",
            "abcde1"
        )
        assertThat(result).isFalse()
    }
}