package com.timkwali.imagesearch.domain.repository

import com.timkwali.data.remote.dto.ImageSearchDto
import com.timkwali.imagesearch.data.remote.ImageSearchApi
import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(
    private val imageSearchApi: ImageSearchApi
): ImageSearchRepository {

    override fun searchImages(searchQuery: String): Observable<Response<ImageSearchDto>> {
        return imageSearchApi.searchImages(searchQuery)
    }

    override fun searchImageById(imageId: String): Observable<Response<ImageSearchDto>> {
        return imageSearchApi.searchImageById(imageId)
    }
}