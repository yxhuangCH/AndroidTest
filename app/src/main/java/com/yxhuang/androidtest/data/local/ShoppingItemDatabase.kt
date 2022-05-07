package com.yxhuang.androidtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by yxhuang
 * Date: 2022/5/2
 * Description:
 */
@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}