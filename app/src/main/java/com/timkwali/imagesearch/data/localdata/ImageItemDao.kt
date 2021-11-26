package com.timkwali.imagesearch.data.localdata

import androidx.room.*
import com.timkwali.imagesearch.domain.model.ImageItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ImageItemDao {

    @Query("SELECT * FROM imageitem WHERE searchQuery = :searchQuery")
    fun searchImages(searchQuery: String): Observable<List<ImageItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveImage(imageItemList: List<ImageItem>): Completable
}