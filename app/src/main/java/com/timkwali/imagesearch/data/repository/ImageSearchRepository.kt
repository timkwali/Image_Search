package com.timkwali.imagesearch.data.repository

import com.timkwali.data.remote.dto.ImageSearchDto
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface ImageSearchRepository {

    fun searchImages(searchQuery: String): Observable<Response<ImageSearchDto>>

    fun searchImageById(imageId: String): Observable<Response<ImageSearchDto>>

}