package com.omaradev.unittesting.shopping_module.presentation.shopping_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.unittesting.shopping_module.common.Resource
import com.omaradev.unittesting.shopping_module.data.local.ShoppingItem
import com.omaradev.unittesting.shopping_module.domain.model.images.ImagesResponse
import com.omaradev.unittesting.shopping_module.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val shoppingItems = repository.getAllShoppingItems()
    val totalPrice = repository.getTotalOfShoppingItems()


    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }


}