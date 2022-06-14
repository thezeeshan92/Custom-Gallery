package com.app.customgallery.models

data class FolderModel(
    val folderId: Int,
    val folder: String,
    val folderItems: List<FolderItemModel>
)