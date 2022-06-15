package com.app.customgallery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.customgallery.models.FolderItemModel

class GallerySharedViewModel : ViewModel() {
    val folderItems = MutableLiveData<MutableList<FolderItemModel>>()
}