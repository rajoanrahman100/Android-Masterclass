package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvInputText: TextView? = null

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
    }

    fun onClear(view: View){
        tvInputText?.text = ""
    }
}