package com.omaradev.unittesting.shopping_module.data

import androidx.lifecycle.LiveData
import com.omaradev.unittesting.shopping_module.common.Resource
import com.omaradev.unittesting.shopping_module.data.local.ShoppingDao
import com.omaradev.unittesting.shopping_module.data.local.ShoppingItem
import com.omaradev.unittesting.shopping_module.data.remote.Api
import com.omaradev.unittesting.shopping_module.domain.model.images.ImagesResponse
import com.omaradev.unittesting.shopping_module.domain.repository.Repository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl
@Inject constructor(private val shoppingDao: ShoppingDao, private val api: Api) :
    Repository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun getAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.getAllShoppingItems()
    }

    override fun getTotalOfShoppingItems(): LiveData<Float> {
        return shoppingDao.getTotalPriceOfItems()
    }

    override suspend fun searchImage(imageQuery: String): Resource<ImagesResponse> {
        return try {
            Resource.Loading<ImagesResponse>()
            val images = api.searchImage(image = imageQuery)
            return Resource.Success<ImagesResponse>(images.body())
        } catch (e: HttpException) {
            Resource.Error<ImagesResponse>(e.localizedMessage ?: "an Error Occurred")
        } catch (e: IOException) {
            Resource.Error<ImagesResponse>("No Internet Connection, Check your Internet")
        }
    }

}