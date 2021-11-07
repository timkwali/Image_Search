package com.timkwali.imagesearch.domain.usecase.getimages

import android.annotation.SuppressLint
import com.timkwali.imagesearch.common.Constants
import com.timkwali.imagesearch.common.HandleErrorResponse
import com.timkwali.imagesearch.common.Resource
import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.model.toImageItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.Observable

class GetImagesUseCase @Inject constructor(
    private val repository: ImageSearchRepository
) {
    private lateinit var observable:  Observable<Resource<List<ImageItem>?>>
    private lateinit var resource: Resource<List<ImageItem>?>

    @SuppressLint("CheckResult")
    operator fun invoke(searchQuery: String) : Observable<Resource<List<ImageItem>?>> {
        observable = Observable.just(Resource.Loading(null))
        repository.searchImages(searchQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    resource = if(it.code() == Constants.successCode) {
                        val imageList = it.body()?.hits?.map { it.toImageItem() }
                        Resource.Success(imageList)
                    } else {
                        val message = HandleErrorResponse.setErrorMessage(it.code())
                        Resource.Error(message, null)
                    }
                    observable = Observable.just(resource)
                },
                {
                    resource = Resource.Error(it.localizedMessage ?: "Something went wrong.", null)
                    observable = Observable.just(resource)
                }
            )
        return observable
    }

}