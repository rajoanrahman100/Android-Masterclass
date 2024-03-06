package com.example.drwaingapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? =
        null // A variable for current color is picked from color pallet.

    private val openGalleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
             result->
            if(result.resultCode == RESULT_OK && result.data != null){
                val imageBackGround: ImageView = findViewById(R.id.iv_background_image)
                result.data?.data?.let {

                }
                imageBackGround.setImageURI(result.data?.data)
            }
        }

    private val requestPermission: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value

                if (isGranted) {
                    when (permissionName) {
                        Manifest.permission.READ_EXTERNAL_STORAGE -> {
                            Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.INTERNAL_CONTENT_URI //Image path of my device
                            )

                            openGalleryLauncher.launch(intent)

                        }


                    }
                } else {
                    when (permissionName) {
                        Manifest.permission.READ_EXTERNAL_STORAGE -> {
                            Toast.makeText(this, "Storage Permission denied", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

        val brushImage = findViewById<ImageButton>(R.id.ib_brush)
        val imageButton = findViewById<ImageButton>(R.id.ib_galley)
        val undoBtn = findViewById<ImageButton>(R.id.ib_undo)
        val redoBtn = findViewById<ImageButton>(R.id.ib_redo)
        val linearLayoutPaintColor = findViewById<LinearLayout>(R.id.ll_paint_color)
        val linearLayoutActionButton = findViewById<LinearLayout>(R.id.action_button)

        brushImage.setOnClickListener {
            showBrushSizeChooseDialog()
        }

        imageButton.setOnClickListener {
            requestStoragePermission()
        }

        undoBtn.setOnClickListener {
            drawingView!!.onClickUndo()
        }

        redoBtn.setOnClickListener {
            drawingView!!.onClickRedo()
        }

        //Set the image button pressed on OnCreate
        mImageButtonCurrentPaint = linearLayoutPaintColor[0] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.pallet_selected
            )
        )


    }




    /*
    * This method is used to request for storage permission
    * */
    private fun requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            showRationalDialog(
                "Kid's Drawing App",
                "Kid's drawing app needs to access your external storage"
            )
        } else {

            requestPermission.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                )
            )
        }
    }

    /**
     * Method is used to launch the dialog to select different brush sizes.
     */

    private fun showBrushSizeChooseDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush Size:")
        val smallButton = brushDialog.findViewById<ImageButton>(R.id.ib_small_brush)
        smallButton.setOnClickListener {
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }

        val mediumButton = brushDialog.findViewById<ImageButton>(R.id.ib_medium_brush)
        mediumButton.setOnClickListener {
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }

        val largeButton = brushDialog.findViewById<ImageButton>(R.id.ib_large_brush)
        largeButton.setOnClickListener {
            drawingView?.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()
    }

    fun paintClicked(view: View) {
        // Toast.makeText(this, "Paint clicked", Toast.LENGTH_SHORT).show()
        if (view != mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.pallet_selected
                )
            )

            mImageButtonCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.pallet_normal
                )
            )

            mImageButtonCurrentPaint = view
        }
    }


    private fun showRationalDialog(title: String, message: String) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

}