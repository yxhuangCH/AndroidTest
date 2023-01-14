package com.yxhuang.androidtest.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by yxhuang
 * Date: 2023/1/11
 * Description:
 */
class ObjectInstanceConverterFactory: Converter.Factory() {
    val VALUE = Any()

    override fun responseBodyConverter(
            type: Type, annotations: Array<Annotation?>?, retrofit: Retrofit?
    ): Converter<ResponseBody?, Any?>? {
        return if (type !== Any::class.java) {
            null
        } else Converter<ResponseBody?, Any?> { value: ResponseBody? -> VALUE }
    }
}