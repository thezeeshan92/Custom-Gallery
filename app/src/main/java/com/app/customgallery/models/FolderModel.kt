package com.app.customgallery.models

data class FolderModel(
    val folderId: Int,
    val folderName: String,
    val folderItems: MutableList<FolderItemModel>
)