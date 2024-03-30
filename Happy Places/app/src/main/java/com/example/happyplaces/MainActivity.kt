package com.example.happyplaces

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happyplaces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        //Todo 3: Then replace the setContentView parameter with binding?.root
        setContentView(binding?.root)

        setContentView(R.layout.activity_main)

        binding?.fabAddHappyPlace?.setOnClickListener {
            val intent=Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(intent)

            //Going to AddHappyPlaceActivity
            // Have to to AddHappyPlaceActivity
            //Commenting on codes

        }
    }
}