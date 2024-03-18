package com.example.sevenminuitworkout

import android.os.Bundle
import android.widget.Toast
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

        bmiActivityBind?.btnCalculateUnits?.setOnClickListener {
            if (!validateMetricUnit()) {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                bmiActivityBind?.tvBMIValue?.visibility = android.view.View.VISIBLE
                bmiActivityBind?.tvBMIDescription?.visibility = android.view.View.VISIBLE
                bmiActivityBind?.tvBMIDescription?.visibility = android.view.View.VISIBLE

            }

        }
    }

    private fun validateMetricUnit(): Boolean {
        var isValid = true

        if (bmiActivityBind?.etMetricUnitWeight?.text.toString().isEmpty()) {
            isValid = false
        } else if (bmiActivityBind?.etMetricUnitHeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }
}