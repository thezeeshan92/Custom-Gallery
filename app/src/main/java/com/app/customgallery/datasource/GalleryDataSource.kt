package com.app.customgallery.datasource

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import com.app.customgallery.models.FolderModel
import java.util.*

class GalleryDataSource(private val contentResolver: ContentResolver) {

    fun fetchContacts(): List<FolderModel> {
        val galleryData: MutableList<FolderModel> = mutableListOf()

        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.DATE_MODIFIED,
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

                val folderId = cursor.getInt(folderIdIndex)

                var index: Int = -1

                for (i in galleryData.indices) {
                    if (galleryData[i].folderId == folderId) {
                        index = i
                        break
                    }
                }
                if(index == -1){

                }else{

                }
               /* galleryData.add(
                    val folderId = cursor
                        MyContact(
                            cursor.getString(0),
                            cursor.getString(1).toContactImageUri()
                        )
                ) //add the gallery item*/
                cursor.moveToNext()
            }
            cursor.close()
        }

        return galleryData.toList()
    }

}

fun String.toContactImageUri() = Uri.withAppendedPath(
    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, this.toLong()),
    ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
).toString()