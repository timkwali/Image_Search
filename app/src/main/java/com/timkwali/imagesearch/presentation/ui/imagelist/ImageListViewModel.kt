package com.timkwali.imagesearch.presentation.ui.imagelist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timkwali.imagesearch.common.Constants
import com.timkwali.imagesearch.common.Resource
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.usecase.getimages.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
): ViewModel() {

    private val _imageList = MutableLiveData<Resource<List<ImageItem>>>()
    val imageList: LiveData<Resource<List<ImageItem>>> get() = _imageList
    var currentSearchQuery = "fruits"
    var currentPage = 1

    init {
        getImageList(currentSearchQuery)
    }

    @SuppressLint("CheckResult")
    fun getImageList(searchQuery: String) {
        currentSearchQuery = searchQuery
        getImagesUseCase(searchQuery, page = currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _imageList.value = it
                },
                {
                    val message = it.localizedMessage ?: "An unexpected error occurred."
                    _imageList.value = Resource.Error(message, null)
                }
            )
    }
}