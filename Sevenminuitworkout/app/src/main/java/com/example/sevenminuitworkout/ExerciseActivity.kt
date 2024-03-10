package com.example.sevenminuitworkout

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sevenminuitworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var exerciseViewMainBinding: ActivityExerciseBinding? = null

    //Variables for timer which will be initialized later
    private var countDownTimer: CountDownTimer? = null

    //The duration of timer in milliseconds
    private var timeDuration: Long = 11000

    //pauseOffset = timeDuration - timeLeft
    private var pauseOffset: Long = 0

    //How much time i will take for rest
    private var restTime: CountDownTimer? = null

    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseViewMainBinding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(exerciseViewMainBinding?.root)

        ///TODO:Custom action bar
        setSupportActionBar(exerciseViewMainBinding?.toolbarExercise)

        //Enable back button icon

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseViewMainBinding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setRestView()


    }


    private fun startProgressBar() {
        exerciseViewMainBinding?.progressBar?.progress = restProgress

        restTime = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                exerciseViewMainBinding?.progressBar?.progress = 10 - restProgress
                exerciseViewMainBinding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {

                Toast.makeText(
                    applicationContext,
                    "Here now we will start the exercise",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }

    private fun setRestView() {
        if (restTime != null) {
            restTime?.cancel()
            restProgress = 0
        }

        startProgressBar()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (restTime != null) {
            restTime?.cancel()
        }
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }
        exerciseViewMainBinding = null
    }
}