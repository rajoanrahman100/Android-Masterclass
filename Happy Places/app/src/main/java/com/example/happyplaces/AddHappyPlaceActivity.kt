package com.example.happyplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happyplaces.databinding.ActivityAddHappyPlaceBinding
import com.example.happyplaces.databinding.ActivityMainBinding

class AddHappyPlaceActivity : AppCompatActivity() {

    private var binding: ActivityAddHappyPlaceBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddHappyPlaceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.toolbarAddPlace?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.toolbarAddPlace?.title="Add Happy Place"



    }
}