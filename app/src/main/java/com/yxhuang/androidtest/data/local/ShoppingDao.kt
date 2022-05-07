package com.yxhuang.androidtest.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by yxhuang
 * Date: 2022/5/2
 * Description:
 */
@Dao interface ShoppingDao {

    /**
     * 插入数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    /**
     * 删除数据
     */
    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    /**
     * 查询所以的 item 数据
     */
    @Query("SELECT * FROM shopping_items")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    /**
     * 查询首页商品的价格总和
     */
    @Query("SELECT SUM(price * amount) FROM SHOPPING_ITEMS")
    fun observeTotalPrice(): LiveData<Float>
}