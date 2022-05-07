package com.yxhuang.androidtest.respositories

import androidx.lifecycle.LiveData
import com.yxhuang.androidtest.data.local.ShoppingDao
import com.yxhuang.androidtest.data.local.ShoppingItem
import com.yxhuang.androidtest.data.remote.PixabayAPI
import com.yxhuang.androidtest.data.remote.responses.ImageResponse
import com.yxhuang.androidtest.other.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by yxhuang
 * Date: 2022/5/3
 * Description:
 */
class DefaultShoppingRepository @Inject constructor(
        private val shoppingDao: ShoppingDao,
        private val pixabayAPI: PixabayAPI
) : ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
       shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
       return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
       return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured",null)
            } else {
                Resource.error("An unknown error occured",null)
            }

        } catch (e: Exception){
            Resource.error("Couldn't reach the server, Check your internet connection", null)
        }
    }


}