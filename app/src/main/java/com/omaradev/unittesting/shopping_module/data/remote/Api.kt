package com.omaradev.unittesting.shopping_module.data.remote

import com.omaradev.unittesting.BuildConfig
import com.omaradev.unittesting.shopping_module.domain.model.images.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/api/")
    suspend fun searchImage(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") image: String
    ):Response<ImagesResponse>
}