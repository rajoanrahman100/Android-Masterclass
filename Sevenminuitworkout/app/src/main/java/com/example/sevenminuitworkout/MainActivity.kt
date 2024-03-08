package com.example.sevenminuitworkout

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.sevenminuitworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var flStartButton: FrameLayout? = null
    //The binding is name just like the name of the layout with Binding attached
    //Todo 1:We create a variable for it and assign to null
    private var mainActivityViewBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Todo 2: We inflate the late file by calling inflate on the Binding name
        mainActivityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        //Todo 3: Then replace the setContentView parameter with binding?.root
        setContentView(mainActivityViewBinding?.root)

        mainActivityViewBinding?.flStart?.setOnClickListener {

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        //Todo 4: TO avoid memory leak we unassign the binding once the activity is destroyed
        mainActivityViewBinding = null
    }
}