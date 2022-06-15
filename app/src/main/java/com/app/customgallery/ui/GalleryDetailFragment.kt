package com.app.customgallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.app.customgallery.R
import com.app.customgallery.adapter.FolderItemViewAdapter
import com.app.customgallery.databinding.FragmentGalleryDetailBinding
import com.app.customgallery.viewmodel.GallerySharedViewModel


/**
 * Show inside folders images and videos
 */
class GalleryDetailFragment : Fragment() {


    private val model: GallerySharedViewModel by activityViewModels()
    private lateinit var binding: FragmentGalleryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        arguments?.getString("folderName")
            ?.let { actionBar?.title = it }

        model.folderItems.observe(viewLifecycleOwner) {
            binding.rvGalleryItems.layoutManager = GridLayoutManager(requireContext(), 4)
            binding.rvGalleryItems.adapter = FolderItemViewAdapter(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}