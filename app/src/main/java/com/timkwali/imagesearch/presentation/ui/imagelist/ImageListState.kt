package com.timkwali.imagesearch.presentation.ui.imagelist

import com.timkwali.imagesearch.domain.model.ImageItem

data class ImageListState(
    val isLoading: Boolean = false,
    val images: List<ImageItem> = emptyList(),
    val error: String = ""
)
