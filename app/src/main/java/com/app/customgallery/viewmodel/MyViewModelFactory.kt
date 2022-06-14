package com.app.customgallery.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.customgallery.datasource.GalleryDataSource
import com.app.customgallery.repository.GalleryRepository
import kotlinx.coroutines.Dispatchers

class MyViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val source =
                GalleryDataSource(application.contentResolver)
            @Suppress("UNCHECKED_CAST")
            MainViewModel(GalleryRepository(source, Dispatchers.IO)) as T
        } else
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}