package com.example.sevenminuitworkout

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var flStartButton: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flStartButton = findViewById(R.id.flStart)

        flStartButton?.setOnClickListener {

        }

    }
}