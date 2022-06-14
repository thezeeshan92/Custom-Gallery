package com.app.customgallery.datasource

import android.content.ContentResolver
import android.provider.MediaStore
import com.app.customgallery.models.FolderItemModel
import com.app.customgallery.models.FolderModel

class GalleryDataSource(private val contentResolver: ContentResolver) {

    fun fetchContacts(): List<FolderModel> {
        val galleryData: MutableList<FolderModel> = mutableListOf()

        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.DATE_ADDED,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID
        )

        // return date order by date descending
        val orderBy = MediaStore.Files.FileColumns.DATE_ADDED + " DESC"

        // Return only video and image metadata.
        val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

        val queryUri = MediaStore.Files.getContentUri("external")

        val cursor = contentResolver.query(
            queryUri,
            projection,
            selection,
            null,
            orderBy
        )

        val allImages = ArrayList<FolderItemModel>()
        val allVideos = ArrayList<FolderItemModel>()

        cursor?.let {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                // all column index
                val folderIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                val folderNameIndex =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                val mimeTypeIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
                val displayNameIndex =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val addedDateIndex =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
                val pathIndex =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

                val folderId = cursor.getInt(folderIdIndex)
                val mimeType = cursor.getString(mimeTypeIndex)
                val displayName = cursor.getString(displayNameIndex)
                val addedDate = cursor.getString(addedDateIndex)
                val absolutePathOfImage = cursor.getString(pathIndex)
                var folderName = cursor.getString(folderNameIndex)
                if (folderName == null) {
                    folderName = try {
                        val token: Array<String> = absolutePathOfImage.split("/").toTypedArray()
                        token[token.size - 2]
                    } catch (e: Exception) {
                        "Un known"
                    }
                }
                var index: Int = -1

                // get index of folder if already added
                for (i in galleryData.indices) {
                    if (galleryData[i].folderId == folderId) {
                        index = i
                        break
                    }
                }
                val folderItemModel =
                    FolderItemModel(displayName, mimeType, addedDate, absolutePathOfImage)

                if (mimeType.contains("image")) {
                    allImages.add(folderItemModel)
                } else {
                    allVideos.add(folderItemModel)
                }

                if (index == -1) {
                    galleryData.add(FolderModel(folderId, folderName, listOf(folderItemModel)))
                } else {
                    galleryData[index].folderItems.toMutableList().add(folderItemModel)
                }

                cursor.moveToNext()
            }
            cursor.close()
        }
        if (galleryData.size > 0) {
            galleryData.sortBy { it.folderName }
            galleryData.add(0, FolderModel(-2, "All Videos", allVideos))
            galleryData.add(0, FolderModel(-1, "All Images", allImages))
        }
        return galleryData.toList()
    }

}