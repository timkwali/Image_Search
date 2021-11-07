package com.timkwali.imagesearch.domain.usecase.getimagedetails

import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import javax.inject.Inject

class GetImageDetailsUseCase @Inject constructor(
    private val repository: ImageSearchRepository
) {

    operator fun invoke(imageId: String){
        repository.searchImageById(imageId)
    }
}