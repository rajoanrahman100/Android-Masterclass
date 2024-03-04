package com.example.apppermission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //Single Permission
    private val cameraResultLauncher: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it == true) {
            // Permission granted
            Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()

        } else {
            // Permission denied
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            openAppPermissionSettings(this)
        }
    }

    //Multiple Permissions
    private val cameraAndLocationResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value

                if (isGranted) {
                    when (permissionName) {
                        android.Manifest.permission.CAMERA -> {
                            Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT)
                                .show()
                        }

                        android.Manifest.permission.ACCESS_FINE_LOCATION -> {
                            Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT)
                                .show()
                        }

                        android.Manifest.permission.ACCESS_COARSE_LOCATION -> {
                            Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    when (permissionName) {
                        android.Manifest.permission.CAMERA -> {
                            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT)
                                .show()
                        }

                        android.Manifest.permission.ACCESS_FINE_LOCATION -> {
                            Toast.makeText(
                                this,
                                "Location permission denied for fine location",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        android.Manifest.permission.ACCESS_COARSE_LOCATION -> {
                            Toast.makeText(
                                this,
                                "Location permission denied for coarse location",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                        }
                    }
                }

            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionButton = findViewById<Button>(R.id.permission_button)
        permissionButton.setOnClickListener {
            //cameraResultLauncher.launch(android.Manifest.permission.CAMERA)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA
                )
            ) {
                showRationalDialog(
                    "Permission Demo Requires Camera Permission",
                    "Camera can not be used without the permission"
                )
                //cameraResultLauncher.launch(android.Manifest.permission.CAMERA)
            } else {
                //Single Permission
                cameraResultLauncher.launch(android.Manifest.permission.CAMERA)

                //Calling multiple permission
                /*cameraAndLocationResultLauncher.launch(
                    arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                    )
                )*/
            }
        }
    }

    /*
    * Show rational dialog for displaying why the app needs permission
    * Only show if user has denied the permission request previously
    * */
    private fun showRationalDialog(title: String, message: String) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    //If user denied any of the permission, this function will redirect user to App Settings
    private fun openAppPermissionSettings(context: Context) {
        val packageName = context.packageName

        // Build the intent to open the app settings screen
        val intent = Intent()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.fromParts("package", packageName, null)
        } else {
            // For versions below Lollipop, open the app settings through application settings
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:$packageName")
        }

        // Start the activity to open app settings
        context.startActivity(intent)
    }

}