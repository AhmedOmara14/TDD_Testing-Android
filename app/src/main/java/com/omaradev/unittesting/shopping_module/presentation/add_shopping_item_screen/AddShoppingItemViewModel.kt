package com.omaradev.unittesting.shopping_module.presentation.add_shopping_item_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.unittesting.shopping_module.common.Resource
import com.omaradev.unittesting.shopping_module.data.local.ShoppingItem
import com.omaradev.unittesting.shopping_module.domain.model.images.ImagesResponse
import com.omaradev.unittesting.shopping_module.domain.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddShoppingItemViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private val images_ = MutableLiveData<Resource<ImagesResponse>>()
    val images: LiveData<Resource<ImagesResponse>> = images_

    //Current image that is selected from all images to show it in add shopping
    private val currentImagesUrl_ = MutableLiveData<String>()
    val currentImagesUrl: LiveData<String> = currentImagesUrl_

    private val insertShoppingItemStatus_ = MutableLiveData<Resource<ShoppingItem>>()
    val insertShoppingItemStatus: LiveData<Resource<ShoppingItem>> = insertShoppingItemStatus_


    fun setCurrentImageUrl(url: String) {
        currentImagesUrl_.value = url
    }

    fun insertShoppingItem_DB(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    //For Validate USer Input
    fun insertShoppingItem(name: String, amount: String, price: String) {

    }
}