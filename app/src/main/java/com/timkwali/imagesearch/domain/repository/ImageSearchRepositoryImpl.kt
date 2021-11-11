package com.timkwali.imagesearch.domain.repository

import com.timkwali.data.remote.dto.ImageSearchDto
import com.timkwali.imagesearch.common.Constants
import com.timkwali.imagesearch.common.HandleErrorResponse
import com.timkwali.imagesearch.data.localdata.ImageItemDao
import com.timkwali.imagesearch.data.remote.ImageSearchApi
import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.model.ResponseModel
import com.timkwali.imagesearch.domain.model.toImageItem
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(
    private val imageSearchApi: ImageSearchApi,
    private val imageItemDao: ImageItemDao
): ImageSearchRepository {

    override fun searchImages(searchQuery: String): Observable<ResponseModel> {
        var imageList: List<ImageItem>? = null
        var message: String? = null
        return imageSearchApi.searchImages(searchQuery = searchQuery)
            .subscribeOn(Schedulers.io())
            .map { response ->
                if(response.code() == Constants.successCode) {
                    imageList = response.body()?.hits?.map { it.toImageItem(searchQuery) }!!
                    if(imageList!!.isEmpty()) {
                        message = Constants.noSearchResults
                    }
                } else {
                    message = HandleErrorResponse.setErrorMessage(response.code())
                }
                ResponseModel(imageList, message)
            }
    }

//    override fun searchImages(searchQuery: String): Observable<Response<ImageSearchDto>> {
//        return imageSearchApi.searchImages(searchQuery = searchQuery)
//    }

    override fun searchImageById(imageId: String): Single<Response<ImageSearchDto>> {
        return imageSearchApi.searchImageById(imageId)
    }

    override fun searchLocalImages(searchQuery: String): Observable<List<ImageItem>> {
        return imageItemDao.searchImages(searchQuery)
    }

    override fun searchLocalImageById(imageId: String): Single<ImageItem> {
        return imageItemDao.searchImageById(imageId)
    }

    override fun saveLocalImage(imageItem: ImageItem) {
        imageItemDao.saveImage(imageItem)
    }
}