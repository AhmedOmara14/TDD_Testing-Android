package com.omaradev.unittesting.shooping_module.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Shopping")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var name: String,
    var amount: Int,
    var price: Float,
    var imgUrl: String
)