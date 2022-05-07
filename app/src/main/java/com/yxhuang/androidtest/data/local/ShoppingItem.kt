package com.yxhuang.androidtest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by yxhuang
 * Date: 2022/5/2
 * Description:
 */
@Entity(tableName = "shopping_items")
data class ShoppingItem(
    var name: String,
    var amount: Int,
    var price: Float,
    var imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
