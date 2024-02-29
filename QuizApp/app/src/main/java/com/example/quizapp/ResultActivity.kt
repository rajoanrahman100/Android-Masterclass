package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.model.Constant

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Todo: connect to each view in the layout through its id
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)

        val userName = intent.getStringExtra(Constant.USER_NAME)
        tvName.text = userName

        val totalQuestions = intent.getIntExtra(Constant.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constant.CORRECT_ANSWERS, 0)

        tvScore.text = "Your Score is $correctAnswers out of $totalQuestions."

        btnFinish.setOnClickListener {
            //
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }
    }
}