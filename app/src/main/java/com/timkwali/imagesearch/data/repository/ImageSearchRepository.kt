package com.timkwali.imagesearch.data.repository

import com.timkwali.data.remote.dto.ImageSearchDto
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.model.ResponseModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface ImageSearchRepository {

    fun searchImages(searchQuery: String): Observable<ResponseModel>

    fun searchImageById(imageId: String): Single<Response<ImageSearchDto>>

    fun searchLocalImages(searchQuery: String): Observable<List<ImageItem>>?

    fun searchLocalImageById(imageId: String): Single<ImageItem>

    fun saveLocalImage(imageItem: ImageItem)
}