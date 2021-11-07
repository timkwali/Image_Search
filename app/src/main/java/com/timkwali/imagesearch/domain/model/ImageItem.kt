package com.timkwali.imagesearch.domain.model

import com.timkwali.imagesearch.data.remote.dto.Hit

data class ImageItem(
    val id: Int,
    val previewURL: String,
    val tags: String,
    val user: String
)

fun Hit.toImageItem(): ImageItem {
    return ImageItem(
        id = id,
        previewURL = previewURL,
        tags = tags,
        user = user
    )
}
