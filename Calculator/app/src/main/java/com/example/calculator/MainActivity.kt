package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvInputText: TextView? = null
    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInputText = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        //Toast.makeText(this, "Button Clicked", T oast.LENGTH_SHORT).show()
        //tvInputText?.append("1") //Append the text when click any button

        //Now, pass view and retrieve the text
        tvInputText?.append((view as Button).text)
        lastNumeric = true
        lastDot = true
    }

    fun onClear(view: View) {
        tvInputText?.text = ""

    }

    fun onOperator(view: View) {

        tvInputText?.text?.let {

            //Condition: lastNumeric=true and isOperatorAdded=false

            if (lastNumeric && !isOperatorAdded(it.toString())) {

                tvInputText?.append((view as Button).text)
                lastNumeric = false
                lastDot = false

            }
        }


    }

    fun onDecimalPoint(view: View) {
        Log.d("MyTag", "Last Numeric: $lastNumeric, Last Dot: $lastDot")
        if (lastNumeric && lastDot) {
            tvInputText?.append(".")
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInputText?.text.toString()
            var prefix = ""

            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue =
                        tvValue.substring(1) //"-99", it will reduce the - operator through substring
                }

                if (tvValue.contains("-")) {
                    val splitValue =
                        tvValue.split('-') // lets say, "99-1" will be split into "99" and "1" in two different arrays
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    //Since we remove prefix minus, we need to add it back before split value one
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    val result = one.toDouble() - two.toDouble()
                    tvInputText?.text = result.toString()
                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {

        return if (value.startsWith("-")) {
            false
        } else value.contains("+") || value.contains("-") || value.contains("*") || value.contains(
            "/"
        )
    }
}
