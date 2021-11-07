package com.timkwali.data.remote

import com.timkwali.common.utils.Constants
import com.timkwali.data.remote.dto.ImageSearchDto
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchApi {

    @GET("?key=${Constants.API_KEY}&image_type=photo")
    fun searchImages(
        @Query("q") searchQuery: String
    ): Observable<Response<ImageSearchDto>>

    @GET("?key=${Constants.API_KEY}&image_type=photo")
    fun searchImageById(
        @Query("id") imageId: String
    ): Observable<Response<ImageSearchDto>>
}