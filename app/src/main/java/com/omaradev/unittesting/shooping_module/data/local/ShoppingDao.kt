package com.omaradev.unittesting.shooping_module.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM Shopping")
    suspend fun getAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(amount*price) FROM Shopping")
    suspend fun getTotalPriceOfItems(): LiveData<Float>
}