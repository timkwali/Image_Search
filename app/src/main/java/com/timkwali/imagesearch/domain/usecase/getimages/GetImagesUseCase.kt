package com.timkwali.imagesearch.domain.usecase.getimages

import com.timkwali.imagesearch.common.networkBoundResource
import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import javax.inject.Inject


class GetImagesUseCase @Inject constructor(
    private val repository: ImageSearchRepository
) {

    operator fun invoke(searchQuery: String) = networkBoundResource(
        query = {
            repository.searchLocalImages(searchQuery)
        },
        fetch = {
            repository.searchImages(searchQuery)
        },
        saveFetchResult = {
            repository.saveLocalImage(it)
        }
    )
}