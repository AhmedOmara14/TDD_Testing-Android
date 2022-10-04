package com.omaradev.unittesting.shopping_module.domain.model.images

data class ImagesResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)