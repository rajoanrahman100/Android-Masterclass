package com.example.sevenminuitworkout

import android.annotation.SuppressLint
import android.icu.math.BigDecimal
import android.os.Bundle
import android.view.View
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
            if (validateMetricUnit()) {
                val heightValue: Float =
                    bmiActivityBind?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue: Float =
                    bmiActivityBind?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)

                // Display BMI result
                displayBMIResult(bmi)
            } else {

                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()

            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayBMIResult(bmi: Float) {

        var bmiLabel: String
        var bmiDescription: String

        if (bmi.toString() <= "15.00") {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.toString() > "15.00" && bmi.toString() <= "16.00") {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.toString() > "16.00" && bmi.toString() <= "18.5") {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.toString() > "18.5" && bmi.toString() <= "25") {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.toString() > "25" && bmi.toString() <= "30") {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your better! Workout more!"
        } else if (bmi.toString() > "30" && bmi.toString() <= "35") {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.toString() > "35" && bmi.toString() <= "40") {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        bmiActivityBind?.llDiplayBMIResult?.visibility = View.VISIBLE
        bmiActivityBind?.tvBMIValue?.text =
            BigDecimal(bmi.toDouble()).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString()
        bmiActivityBind?.tvBMIType?.text = bmiLabel
        bmiActivityBind?.tvBMIDescription?.text = bmiDescription
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