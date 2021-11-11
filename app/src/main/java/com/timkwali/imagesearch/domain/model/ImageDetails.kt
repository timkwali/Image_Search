package com.timkwali.imagesearch.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.timkwali.imagesearch.data.remote.dto.Hit

data class ImageDetails(
    val id: Int,
    val comments: Int,
    val downloads: Int,
    val largeImageURL: String,
    val likes: Int,
    val tags: String,
    val user: String,
)

fun Hit.toImageDetails(): ImageDetails {
    return ImageDetails(
        id = id,
        comments = comments,
        downloads = downloads,
        largeImageURL = largeImageURL,
        likes = likes,
        tags = tags,
        user = user
    )
}
