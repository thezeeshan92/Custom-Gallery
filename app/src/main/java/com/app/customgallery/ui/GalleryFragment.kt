package com.app.customgallery.ui

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.customgallery.adapter.FolderViewAdapter
import com.app.customgallery.databinding.FragmentGalleryBinding
import com.app.customgallery.viewmodel.MainViewModel
import com.app.customgallery.viewmodel.MyViewModelFactory
import com.permissionx.guolindev.PermissionX


/**
 * Load all images and video in GalleryFragment
 */
class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkStoragePermission()

        val myViewModelFactory = MyViewModelFactory(requireActivity().application)
        val viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)
        viewModel.myGallery.observe(viewLifecycleOwner) {
            binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvGallery.adapter = FolderViewAdapter(it)
        }
    }

    private fun checkStoragePermission() {
        val permissions = ArrayList<String>()
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        PermissionX.init(this)
            .permissions(permissions)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "Core fundamental are based on storage permission",
                    "OK",
                    "Cancel"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "You need to allow storage permission in settings manually",
                    "OK",
                    "Cancel"
                )
            }
            .request { allGranted, _, _ ->

                if (allGranted) {
                    Toast.makeText(
                        requireContext(),
                        "Storage Permission Granted",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Storage Permission Not Granted",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}