package com.app.customgallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.customgallery.R
import com.app.customgallery.databinding.LayoutFolderItemViewBinding
import com.app.customgallery.extensions.loadWithThumbnail
import com.app.customgallery.models.FolderItemModel

class FolderItemViewAdapter constructor(
    private val folderItems: List<FolderItemModel>
) : RecyclerView.Adapter<FolderItemViewAdapter.ViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding: LayoutFolderItemViewBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_folder_item_view, parent, false
            )
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        if (folderItems[i].mimeType.contains("image"))
            holder.binding.ivVideo.visibility = View.GONE
        else
            holder.binding.ivVideo.visibility = View.VISIBLE
        holder.binding.folderPic.loadWithThumbnail(folderItems[i].path)
    }


    override fun getItemCount(): Int {
        return folderItems.size
    }

    class ViewHolder(itemView: LayoutFolderItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: LayoutFolderItemViewBinding

        init {
            binding = itemView
        }
    }
}