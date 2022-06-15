package com.app.customgallery.ui

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.app.customgallery.R
import com.app.customgallery.adapter.FolderViewAdapter
import com.app.customgallery.databinding.FragmentGalleryBinding
import com.app.customgallery.viewmodel.GallerySharedViewModel
import com.app.customgallery.viewmodel.MainViewModel
import com.app.customgallery.viewmodel.MyViewModelFactory
import com.permissionx.guolindev.PermissionX


/**
 * Load all images and video in GalleryFragment
 */
class GalleryFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentGalleryBinding
    private val sharedViewModel: GallerySharedViewModel by activityViewModels()

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

        navController = Navigation.findNavController(view)

        checkStoragePermission()

    }

    private fun setUpObservers() {
        val myViewModelFactory = MyViewModelFactory(requireActivity().application)
        val viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)
        viewModel.myGallery.observe(viewLifecycleOwner) { it ->
            val galleryData = it
            binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvGallery.adapter = FolderViewAdapter(galleryData) {

                navController.graph.findStartDestination().id.let { }
                sharedViewModel.folderItems.value = galleryData[it].folderItems

                navController.navigate(
                    R.id.GalleryDetailFragment,
                    bundleOf(
                        "folderName" to galleryData[it].folderName
                    )
                )
            }
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
                    setUpObservers()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Storage Permission Not Granted",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }


    override fun onResume() {
        super.onResume()
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = getString(R.string.app_name)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
    }
}