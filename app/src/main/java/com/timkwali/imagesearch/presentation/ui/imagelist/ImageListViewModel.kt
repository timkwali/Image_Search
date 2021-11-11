package com.timkwali.imagesearch.presentation.ui.imagelist

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timkwali.imagesearch.common.Resource
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.usecase.getimagedetails.GetImageDetailsUseCase
import com.timkwali.imagesearch.domain.usecase.getimages.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getImageDetailsUseCase: GetImageDetailsUseCase
): ViewModel() {

    private val _imageList = MutableLiveData<Resource<List<ImageItem>>>()
    val imageList: LiveData<Resource<List<ImageItem>>> get() = _imageList

    val imageItem: MutableLiveData<ImageItem> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getImageList(searchQuery: String) {

        _imageList.value = Resource.Loading()
        getImagesUseCase(searchQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                   if(it.data != null) {
                       _imageList.value = Resource.Success(it.data, it.message)
                   } else {
                       _imageList.value = Resource.Error(it.message!!)
                   }
                },
                {
                    _imageList.value = Resource.Error(it.localizedMessage ?: "An unexpected error occurred.")
                }
            )
    }

    fun getImageById(id: Int) {
        imageItem.value = ImageItem(
            6742560, "sfl", "lily, flower, yellow flower",
            "ignartonosbg", 34, 8012,
            "https://pixabay.com/get/g7eb4340e8e4d6d102962a751db046b5a47db56666516cd7951bf4a8db670101f9767c4dcb7f215562b45072221c417c37bab19f4d3974b22448cf976d8e8897c_1280.jpg",
            37, "flower"
        )
    }
}