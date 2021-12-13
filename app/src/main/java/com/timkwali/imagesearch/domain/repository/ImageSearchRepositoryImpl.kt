package com.timkwali.imagesearch.domain.repository

import com.timkwali.imagesearch.common.Constants
import com.timkwali.imagesearch.common.HandleErrorResponse
import com.timkwali.imagesearch.data.localdata.ImageDatabase
import com.timkwali.imagesearch.data.localdata.ImageItemDao
import com.timkwali.imagesearch.data.remote.ImageSearchApi
import com.timkwali.imagesearch.data.repository.ImageSearchRepository
import com.timkwali.imagesearch.domain.model.ImageItem
import com.timkwali.imagesearch.domain.model.toImageItem
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(
    private val imageSearchApi: ImageSearchApi,
    private val db: ImageDatabase
): ImageSearchRepository {
    private val imageItemDao = db.imageItemDao

    override fun searchImages(searchQuery: String, page: Int): Observable<List<ImageItem>> {
        return imageSearchApi.searchImages(searchQuery, page = page)
            .map { response ->
                val imageList = response.body()?.hits?.map { it.toImageItem(searchQuery) }
                val code = if(response.code() == 200 && imageList?.isEmpty() == true) 204 else response.code()
                val message = HandleErrorResponse.setErrorMessage(code)

                imageList
            }
    }

    override fun searchLocalImages(searchQuery: String): Observable<List<ImageItem>> {
        return imageItemDao.searchImages(searchQuery)
    }

    override fun saveLocalImage(imageItemList: List<ImageItem>): Completable {
        return imageItemDao.saveImage(imageItemList)
    }
}