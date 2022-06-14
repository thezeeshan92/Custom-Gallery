package com.app.customgallery

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.guolindev.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    "You need to allow necessary permissions in Settings manually",
                    "OK",
                    "Cancel"
                )
            }
            .request { allGranted, _, _ ->

                if (allGranted) {
                    Toast.makeText(this@MainActivity,"Storage Permission Granted",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity,"Storage Permission Not Granted",Toast.LENGTH_LONG).show()
                }
            }
    }
}