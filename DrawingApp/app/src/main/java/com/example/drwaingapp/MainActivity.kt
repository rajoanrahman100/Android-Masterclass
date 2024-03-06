package com.example.drwaingapp

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.FrameLayout
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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? =
        null // A variable for current color is picked from color pallet.

    private val openGalleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val imageBackGround: ImageView = findViewById(R.id.iv_background_image)
                result.data?.data?.let {

                }
                imageBackGround.setImageURI(result.data?.data)
            }
        }

    /** create an ActivityResultLauncher with MultiplePermissions since we are requesting
     * both read and write
     */
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

                        Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                            Toast.makeText(
                                this,
                                "External Storage permission granted",
                                Toast.LENGTH_SHORT
                            )
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

                        Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                            Toast.makeText(
                                this,
                                "External Storage Permission denied",
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
        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

        val brushImage = findViewById<ImageButton>(R.id.ib_brush)
        val imageButton = findViewById<ImageButton>(R.id.ib_galley)
        val undoBtn = findViewById<ImageButton>(R.id.ib_undo)
        val saveBtn = findViewById<ImageButton>(R.id.ib_save)
        val linearLayoutPaintColor = findViewById<LinearLayout>(R.id.ll_paint_color)
        val linearLayoutActionButton = findViewById<LinearLayout>(R.id.action_button)
        //val frameLayout= findViewById<FrameLayout>(R.id.fl_drawing_View_container)

        brushImage.setOnClickListener {
            showBrushSizeChooseDialog()
        }

        imageButton.setOnClickListener {
            requestStoragePermission()
        }

        undoBtn.setOnClickListener {
            drawingView!!.onClickUndo()
        }

        saveBtn.setOnClickListener {
            if (isReadStorageAllowed()) {
                lifecycleScope.launch {
                    val flDrawingView: FrameLayout = findViewById(R.id.fl_drawing_View_container)
                    val myBitmap = getBitmapFromView(flDrawingView)
                    saveBitmapFile(myBitmap)
                }
            }
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
        // Check if the permission was denied and show rationale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            //call the rationale dialog to tell the user why they need to allow permission request
            showRationalDialog(
                "Kid's Drawing App",
                "Kid's drawing app needs to access your external storage"
            )
        } else {
            // You can directly ask for the permission.
            //if it has not been denied then request for permission
            //  The registered ActivityResultCallback gets the result of this request.
            requestPermission.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
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

    /**
     * Method is called when color is clicked from pallet_normal.
     *
     * @param view ImageButton on which click took place.
     */

    fun paintClicked(view: View) {
        // Toast.makeText(this, "Paint clicked", Toast.LENGTH_SHORT).show()
        if (view != mImageButtonCurrentPaint) {
            // Update the color
            val imageButton = view as ImageButton

            // Here the tag is used for swaping the current color with previous color.
            // The tag stores the selected view
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            // Swap the backgrounds for last active and currently active image button.
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
            //Current view is updated with selected view in the form of ImageButton.
            mImageButtonCurrentPaint = view
        }
    }

    /**  create rationale dialog
     * Shows rationale dialog for displaying why the app needs permission
     * Only shown if the user has denied the permission request previously
     */
    private fun showRationalDialog(title: String, message: String) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    /**
     * We are calling this method to check the permission status
     */
    private fun isReadStorageAllowed(): Boolean {

        //Getting the permission status
        // Here the checkSelfPermission is
        /**
         * Determine whether <em>you</em> have been granted a particular permission.
         *
         * @param permission The name of the permission being checked.
         *
         */

        val result =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        /**
         *
         * @return {@link android.content.pm.PackageManager#PERMISSION_GRANTED} if you have the
         * permission, or {@link android.content.pm.PackageManager#PERMISSION_DENIED} if not.
         *
         */
        //If permission is granted returning true and If permission is not granted returning false
        return result == PackageManager.PERMISSION_GRANTED

    }

    private fun getBitmapFromView(view: View): Bitmap {

        //Define a Bitmap with the same size as the view
        //Create Bitmap: Returns a bitmap width = view width, height = view height
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        //Bitmap a canvas to it
        val canvas = Canvas(returnedBitmap)

        //Get the view background
        val bgDrawable = view.background

        //If there is a background drawable, then draw it on the canvas
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return returnedBitmap
    }

    private suspend fun saveBitmapFile(mBitmap: Bitmap): String {
        var result = ""

        withContext(Dispatchers.IO) {
            try {
                val bytes = ByteArrayOutputStream()
                mBitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes)

                /**
                 * Write a compressed version of the bitmap to the specified outputstream.
                 * If this returns true, the bitmap can be reconstructed by passing a
                 * corresponding inputstream to BitmapFactory.decodeStream(). Note: not
                 * all Formats support all bitmap configs directly, so it is possible that
                 * the returned bitmap from BitmapFactory could be in a different bitdepth,
                 * and/or may have lost per-pixel alpha (e.g. JPEG only supports opaque
                 * pixels).
                 *
                 * @param format   The format of the compressed image
                 * @param quality  Hint to the compressor, 0-100. 0 meaning compress for
                 *                 small size, 100 meaning compress for max quality. Some
                 *                 formats, like PNG which is lossless, will ignore the
                 *                 quality setting
                 * @param stream   The outputstream to write the compressed data.
                 * @return true if successfully compressed to the specified stream.
                 */

                val f = File(
                    externalCacheDir!!.absoluteFile.toString()
                            + File.separator + "KidDrawingApp_" + System.currentTimeMillis() / 1000 + ".png"
                )
                // Here the Environment : Provides access to environment variables.
                // getExternalStorageDirectory : returns the primary shared/external storage directory.
                // absoluteFile : Returns the absolute form of this abstract pathname.
                // File.separator : The system-dependent default name-separator character. This string contains a single character.
                val fo =
                    FileOutputStream(f) // Creates a file output stream to write to the file represented by the specified object.
                fo.write(bytes.toByteArray()) // Writes bytes from the specified byte array to this file output stream.
                fo.close() // Closes this file output stream and releases any system resources associated with this stream. This file output stream may no longer be used for writing bytes.
                result = f.absolutePath // The file absolute path is return as a result.
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "File Saved Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                result = ""
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "Something went wrong while saving the file",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return result
    }

}