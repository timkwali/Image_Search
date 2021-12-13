package com.timkwali.imagesearch.data.remote

import com.timkwali.data.remote.dto.ImageSearchDto
import com.timkwali.imagesearch.common.Constants
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchApi {

    @GET("/api/")
    fun searchImages(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = Constants.API_KEY,
        @Query("image_type") imageType: String = Constants.IMAGE_TYPE,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = Constants.PER_PAGE
    ): Observable<Response<ImageSearchDto>>

    @GET("/api/")
    fun searchImageById(
        @Query("id") imageId: String,
        @Query("key") apiKey: String = Constants.API_KEY,
    @Query("image_type") imageType: String = Constants.IMAGE_TYPE
    ): Observable<Response<ImageSearchDto>>
}