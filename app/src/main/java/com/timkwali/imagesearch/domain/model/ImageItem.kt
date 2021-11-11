package com.timkwali.imagesearch.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.timkwali.imagesearch.data.remote.dto.Hit

@Entity
data class ImageItem(
    @PrimaryKey
    val id: Int,
    val previewURL: String,
    val tags: String,
    val user: String,
    val comments: Int,
    val downloads: Int,
    val largeImageURL: String,
    val likes: Int,
    val searchQuery: String
)

fun Hit.toImageItem(searchQuery: String): ImageItem {
    return ImageItem(
        id = id,
        previewURL = previewURL,
        tags = tags,
        user = user,
        comments = comments,
        downloads = downloads,
        largeImageURL = largeImageURL,
        likes = likes,
        searchQuery = searchQuery
    )
}
