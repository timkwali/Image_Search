package com.timkwali.imagesearch.data.repository

import com.timkwali.data.remote.dto.ImageSearchDto
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.model.ResponseModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface ImageSearchRepository {

    //REMOTE DATA
    fun searchImages(searchQuery: String, page: Int): Observable<List<ImageItem>>

    //LOCAL DATA
    fun searchLocalImages(searchQuery: String): Observable<List<ImageItem>>
    fun saveLocalImage(imageItemList: List<ImageItem>): Completable
}