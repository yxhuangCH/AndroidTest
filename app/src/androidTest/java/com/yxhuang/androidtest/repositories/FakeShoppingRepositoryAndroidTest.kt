package com.yxhuang.androidtest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yxhuang.androidtest.data.local.ShoppingItem
import com.yxhuang.androidtest.data.remote.responses.ImageResponse
import com.yxhuang.androidtest.other.Resource
import com.yxhuang.androidtest.respositories.ShoppingRepository

/**
 * Created by yxhuang
 * Date: 2022/5/3
 * Description:
 */
class FakeShoppingRepositoryAndroidTest : ShoppingRepository {

    private val mShoppingItems = mutableListOf<ShoppingItem>()

    private val mObservableShoppingItems = MutableLiveData<List<ShoppingItem>>()
    private val mObservableTotalPrice = MutableLiveData<Float>()

    private var mShouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        mShouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        mObservableShoppingItems.postValue(mShoppingItems)
        mObservableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return mShoppingItems.sumByDouble {
            it.price.toDouble()
        }.toFloat()
    }


    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        mShoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        mShoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return mObservableShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return mObservableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if (mShouldReturnNetworkError){
            Resource.error("Error", null)
        } else{
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }

}