package com.app.customgallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.customgallery.databinding.FragmentGalleryDetailBinding

/**
 * Show inside folders images and videos
 */
class GalleryDetailFragment : Fragment() {

    private lateinit var binding: FragmentGalleryDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}