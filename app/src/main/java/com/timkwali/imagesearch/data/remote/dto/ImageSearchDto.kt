package com.timkwali.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ImageSearchDto(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)