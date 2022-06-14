package com.app.customgallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.customgallery.models.FolderModel
import com.app.customgallery.repository.GalleryRepository

class MainViewModel(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    var myGallery: LiveData<List<FolderModel>> = liveData {
        emit(galleryRepository.fetchContacts())
    }

}