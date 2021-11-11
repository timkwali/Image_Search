package com.timkwali.imagesearch.data.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timkwali.imagesearch.domain.model.ImageItem

@Database(
    entities = [ImageItem::class],
    version = 1
)
abstract class ImageDatabase: RoomDatabase() {
    abstract val imageItemDao: ImageItemDao

    companion object {
        const val DATABASE_NAME = "images_db"
    }
}