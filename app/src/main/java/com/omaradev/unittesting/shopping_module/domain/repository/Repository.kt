package com.omaradev.unittesting.shopping_module.domain.repository

import androidx.lifecycle.LiveData
import com.omaradev.unittesting.shopping_module.common.Resource
import com.omaradev.unittesting.shopping_module.data.local.ShoppingItem
import com.omaradev.unittesting.shopping_module.domain.model.images.ImagesResponse
import retrofit2.Response

interface Repository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun getTotalOfShoppingItems(): LiveData<Float>

    suspend fun searchImage(imageQuery: String): Resource<ImagesResponse?>
}