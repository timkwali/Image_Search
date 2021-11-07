package com.timkwali.imagesearch.domain.model

import com.timkwali.imagesearch.data.remote.dto.Hit

data class ImageDetails(
    val comments: Int,
    val downloads: Int,
    val largeImageURL: String,
    val likes: Int,
    val tags: String,
    val user: String,
)

fun Hit.toImageDetails(): ImageDetails {
    return ImageDetails(
        comments = comments,
        downloads = downloads,
        largeImageURL = largeImageURL,
        likes = likes,
        tags = tags,
        user = user
    )
}
