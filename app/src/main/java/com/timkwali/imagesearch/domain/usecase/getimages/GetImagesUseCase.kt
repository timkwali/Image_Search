package com.timkwali.imagesearch.domain.usecase.getimages

import android.annotation.SuppressLint
import android.util.Log
import com.timkwali.imagesearch.common.Constants
import com.timkwali.imagesearch.common.HandleErrorResponse
import com.timkwali.imagesearch.common.Resource
import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.model.ResponseModel
import com.timkwali.imagesearch.domain.model.toImageItem
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.Observable


class GetImagesUseCase @Inject constructor(
    private val repository: ImageSearchRepository
) {

    @SuppressLint("CheckResult")
    operator fun invoke(searchQuery: String): Observable<ResponseModel> {

        var imageList: List<ImageItem>? = null
        var message: String? = null

        return repository.searchImages(searchQuery)
            .subscribeOn(Schedulers.io())
            .map {
                if(it.data != null) {
                    //save data in database
//                    it.data.map {image ->  repository.saveLocalImage(image) }
                    imageList = it.data
                } else {
////                    Check if database has result of search query
//                    var localImages: List<ImageItem>? = emptyList()
//                        repository.searchLocalImages(searchQuery)
//                        ?.observeOn(Schedulers.io())
//                        ?.subscribe(
//                            {
//                                localImages = it
//                            },{
//                                localImages = null
//                            }
//                        )
//                    if(localImages == null) {
//                        message = it.message!!
//                    } else {
//                        //return data from database
//                        imageList = localImages
//                        message = it.message!!
//                    }
                }
                ResponseModel(imageList, message)
            }
    }
}