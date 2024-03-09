package com.example.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    //Variables for timer which will be initialized later
    private var countDownTimer: CountDownTimer? = null

    //The duration of timer in milliseconds
    private var timeDuration: Long = 60000

    //pauseOffset = timeDuration - timeLeft
    private var pauseOffset: Long = 0

    private var timerText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById<TextView>(R.id.tvTimer)
        val startButton = findViewById<TextView>(R.id.btnStart)
        val pauseButton = findViewById<TextView>(R.id.btnPause)
        val resetButton = findViewById<TextView>(R.id.btnReset)
        timerText?.text = (timeDuration / 1000).toString()

        startButton.setOnClickListener {
            startTimer(pauseOffset)
        }

        pauseButton.setOnClickListener {
            pauseTimer()
        }

        resetButton.setOnClickListener {
            resetTimer()
        }

    }

    private fun startTimer(pauseOffsetL: Long) {
        countDownTimer = object : CountDownTimer(timeDuration - pauseOffsetL, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                println("MillisUntilFinished $millisUntilFinished")
                println("Time Duration $timeDuration")
                pauseOffset = timeDuration - millisUntilFinished
                println("Pause Offset $pauseOffset")
                timerText?.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "Timer Finished", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }
    }

    private fun resetTimer() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
            timerText?.text = (timeDuration / 1000).toString()
            countDownTimer = null
            pauseOffset = 0
        }
    }
}