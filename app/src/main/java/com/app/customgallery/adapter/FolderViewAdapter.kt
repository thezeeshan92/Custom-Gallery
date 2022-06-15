package com.app.customgallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.customgallery.R
import com.app.customgallery.databinding.LayoutFolderViewBinding
import com.app.customgallery.extensions.loadWithThumbnail
import com.app.customgallery.models.FolderModel

class FolderViewAdapter constructor(
    private val folderModels: List<FolderModel>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<FolderViewAdapter.ViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding: LayoutFolderViewBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_folder_view, parent, false
            )
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.binding.tvFolderName.text = folderModels[i].folderName
        holder.binding.tvFolderSize.text = folderModels[i].folderItems.size.toString()
        holder.binding.folderPic.loadWithThumbnail(folderModels[i].folderItems[0].path)
        holder.itemView.setOnClickListener { onItemClick(i) }
    }


    override fun getItemCount(): Int {
        return folderModels.size
    }

    class ViewHolder(itemView: LayoutFolderViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding: LayoutFolderViewBinding

        init {
            binding = itemView
        }
    }
}