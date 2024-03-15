package com.example.sevenminuitworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sevenminuitworkout.databinding.ActivityBmiBinding

class BMIActivity : AppCompatActivity() {

    private var bmiActivityBind: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bmiActivityBind = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(bmiActivityBind?.root)
        setSupportActionBar(bmiActivityBind?.toolbarBmiActivity)
        ///TODO:Custom action bar

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE YOUR BMI"
        }

        bmiActivityBind?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}