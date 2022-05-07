package com.yxhuang.androidtest

import android.content.Context

/**
 * Created by yxhuang
 * Date: 2022/5/2
 * Description:
 */
class ResourceComparser {

    fun isEqual(context: Context,resId:Int, string: String):Boolean{
        return context.getString(resId) == string
    }
}