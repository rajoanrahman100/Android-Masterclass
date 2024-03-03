package com.example.drwaingapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? =
        null // A variable for current color is picked from color pallet.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

        val brushImage = findViewById<ImageButton>(R.id.ib_brush)
        val linearLayoutPaintColor = findViewById<LinearLayout>(R.id.ll_paint_color)

        brushImage.setOnClickListener {
            showBrushSizeChooseDialog()
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

}