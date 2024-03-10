package com.example.sevenminuitworkout

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    // TODO(Step 2 - Adding a variables for the 30 seconds Exercise timer.)
    private var exerciseTimer: CountDownTimer? = null // Variable for Exercise Timer and later on we will initialize it.
    private var exerciseProgress = 0 // Variable for the exercise timer progress. As initial value the exercise progress is set to 0. As we are about to start.

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


    // Setting up the 10 seconds timer for rest view and updating it continuously.
    //START
    /**
     * Function is used to set the progress of timer using the progress
     */
    private fun setProgressBar() {
        exerciseViewMainBinding?.progressBar?.progress = restProgress


        /**
         * @param millisInFuture The number of millis in the future from the call
         *   to {#start()} until the countdown is done and {#onFinish()}
         *   is called.
         * @param countDownInterval The interval along the way to receive
         *   {#onTick(long)} callbacks.
         */
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.

        restTimer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                exerciseViewMainBinding?.progressBar?.progress = 10 - restProgress
                exerciseViewMainBinding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                // When the 10 seconds will complete this will be executed.
                // TODO(Step 5 - After completing 10 Seconds of the REST timer start the 30 seconds of Start Exercise View.)
                // START
                setUpExerciseView()
            }
        }.start()
    }

    // TODO(Step 3 - After REST View Setting up the 30 seconds timer for the Exercise view and updating it continuously.)
    // START
    /**
     * Function is used to set the progress of the timer using the progress for Exercise View for 30 Seconds
     */
    private fun setExerciseProgressBar() {
        exerciseViewMainBinding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                exerciseViewMainBinding?.progressBarExercise?.progress = 30 - exerciseProgress
                exerciseViewMainBinding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {

                Toast.makeText(
                    applicationContext,
                    "30 seconds are over, lets go to the rest view",
                    Toast.LENGTH_SHORT
                ).show()


            }
        }.start()
    }

    //END


    // TODO(Step 4 - Setting up the Exercise View with a 30 seconds timer.)
    // START
    /**
     * Function is used to set the progress of the timer using the progress for Exercise View.
     */

    private fun setUpExerciseView() {
        exerciseViewMainBinding?.flProgressBar?.visibility = View.INVISIBLE
        exerciseViewMainBinding?.tvTitle?.text = "Exercise Time"
        exerciseViewMainBinding?.flProgressBarExercise?.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        setExerciseProgressBar()
    }

    //Setting up the Get Ready View with 10 seconds of timer
    //START
    /**
     * Function is used to set the timer for REST.
     */
    private fun setRestView() {

        /**
         * Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        setProgressBar()
    }


    // Destroying the timer when closing the activity or app
    //START
    /**
     * Here in the Destroy function we will reset the rest timer if it is running.
     */

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }


        exerciseViewMainBinding = null
    }
}