package com.omaradev.unittesting.shopping_module.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omaradev.unittesting.shopping_module.common.Resource
import com.omaradev.unittesting.shopping_module.data.local.ShoppingItem
import com.omaradev.unittesting.shopping_module.domain.model.images.ImagesResponse
import com.omaradev.unittesting.shopping_module.domain.repository.Repository

//Fake Repo For Test
class RepositoryTest : Repository {

    private val shoppingItems = mutableListOf<ShoppingItem>()
    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    private fun refreshLiveData() {
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float? {
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun getAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun getTotalOfShoppingItems(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchImage(imageQuery: String): Resource<ImagesResponse?> {
        return if (shouldReturnNetworkError) {
            Resource.error<ImagesResponse?>("Error ,")
        } else {
            Resource.success<ImagesResponse?>(ImagesResponse(listOf(), 0, 0))
        }
    }
}