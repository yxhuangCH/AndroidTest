package com.yxhuang.androidtest

/**
 * Created by yxhuang
 * Date: 2022/4/29
 * Description:
 */
object RegisterUtil {

    /**
     * 已经存在 用户
     */
    private val existingUsers = listOf("Tom", "Micheal")

    /**
     * 严重注册信息
     *  写单元测试的情况：
     *  1. username / password is empty;
     *  2. username is already exit
     *  3. confirmed password 与 password 不一致
     *  4. password 里面小于 2 位数字
     */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String
    ): Boolean {
       if (username.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()){
           return false
       }
        if (username in existingUsers){
            return false
        }
        if (password != confirmedPassword){
            return false
        }
        if (password.count{ it.isDigit()} < 5){
            return false
        }
        return true
    }
}