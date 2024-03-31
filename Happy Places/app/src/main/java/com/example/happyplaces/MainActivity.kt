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

        //setContentView(R.layout.activity_main)

        binding?.fabAddHappyPlace?.setOnClickListener {
            println("fab clicked")
            val intent=Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)

        }
    }
}