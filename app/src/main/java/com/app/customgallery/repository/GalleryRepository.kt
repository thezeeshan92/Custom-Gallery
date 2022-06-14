package com.app.customgallery.repository

import com.app.customgallery.datasource.GalleryDataSource
import com.app.customgallery.models.FolderModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GalleryRepository(private val source: GalleryDataSource, private val myDispatcher: CoroutineDispatcher) {

    suspend fun fetchContacts(): List<FolderModel> {
        return withContext(myDispatcher) {
            source.fetchContacts()
        }
    }
}