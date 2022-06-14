package com.app.customgallery.ui

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.customgallery.databinding.FragmentGalleryBinding
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