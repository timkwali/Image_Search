package com.timkwali.imagesearch.domain.model

data class ResponseModel(
    val data: List<ImageItem>? = null,
    val message: String? = null
)
