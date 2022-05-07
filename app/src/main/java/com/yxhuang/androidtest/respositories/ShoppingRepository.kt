package com.yxhuang.androidtest.respositories

import androidx.lifecycle.LiveData
import com.yxhuang.androidtest.data.local.ShoppingItem
import com.yxhuang.androidtest.data.remote.responses.ImageResponse
import com.yxhuang.androidtest.other.Resource

/**
 * Created by yxhuang
 * Date: 2022/5/3
 * Description:
 */
interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems():LiveData<List<ShoppingItem>>

    fun observeTotalPrice():LiveData<Float>

    suspend fun searchForImage(imageQuery:String):Resource<ImageResponse>

}