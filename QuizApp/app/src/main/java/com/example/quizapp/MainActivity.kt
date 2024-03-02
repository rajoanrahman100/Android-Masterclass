package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.model.Constant

class MainActivity : AppCompatActivity() {

    private var btnStart: Button? = null
    private var etName: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        etName = findViewById(R.id.etName)



        btnStart?.setOnClickListener {
            if (etName?.text.toString().isEmpty()) {
                etName?.error = "Please enter your name"
                etName?.requestFocus()
            } else {
                val intent = Intent(this, QuizQuestionsActivity::class.java)

                //Pass value to next activity through intent.putExtra()
                intent.putExtra(Constant.USER_NAME, etName?.text.toString())

                startActivity(intent)
                finish()
            }
        }
    }

}