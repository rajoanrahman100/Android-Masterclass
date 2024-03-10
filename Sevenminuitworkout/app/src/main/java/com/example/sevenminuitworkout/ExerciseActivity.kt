package com.example.sevenminuitworkout

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sevenminuitworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null

    //How much time i will take for rest
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    // TODO(Step 2 - Adding a variables for the 30 seconds Exercise timer.)
    private var exerciseTimer: CountDownTimer? =
        null // Variable for Exercise Timer and later on we will initialize it.
    private var exerciseProgress =
        0 // Variable for the exercise timer progress. As initial value the exercise progress is set to 0. As we are about to start.

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var exercisePosition = -1

    private var mediaPlayer:MediaPlayer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ///TODO:Custom action bar
        setSupportActionBar(binding?.toolbarExercise)

        //Enable back button icon

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        exerciseList =
            Constants.getDefaultExerciseList() //load the exercise list from the constants class.

        println("Exercise List : " + exerciseList?.size)

        setRestView()


    }


    // Setting up the 10 seconds timer for rest view and updating it continuously.
    //START
    /**
     * Function is used to set the progress of timer using the progress
     */
    private fun setProgressBar() {
        binding?.progressBar?.progress = restProgress


        /**
         * @param millisInFuture The number of millis in the future from the call
         *   to {#start()} until the countdown is done and {#+()}
         *   is called.
         * @param countDownInterval The interval along the way to receive
         *   {#onTick(long)} callbacks.
         */
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.

        restTimer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                // When the 10 seconds will complete this will be executed.
                // TODO(Step 5 - After completing 10 Seconds of the REST timer start the 30 seconds of Start Exercise View.)
                // START
//                exercisePosition++
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
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {

                if (exercisePosition < exerciseList?.size!! - 1) {
                    setRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations!! We have done our today's exercise. Take a break now",
                        Toast.LENGTH_SHORT
                    ).show()
                }


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
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE


        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding?.ivImage?.setImageResource(exerciseList!![exercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![exercisePosition].getName()

        setExerciseProgressBar()
    }

    //Setting up the Get Ready View with 10 seconds of timer
    //START
    /**
     * Function is used to set the timer for REST.
     */
    private fun setRestView() {

        try {
            val soundUri=Uri.parse("android.resource://com.example.sevenminuitworkout/"+R.raw.press_start)
            mediaPlayer=MediaPlayer.create(applicationContext,soundUri)
            mediaPlayer?.isLooping=false
            mediaPlayer?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }

        binding?.flProgressBar?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        exercisePosition++
        binding?.tvUpcomingExerciseName?.text = exerciseList!![exercisePosition].getName()


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

        if(mediaPlayer!=null){
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }


        binding = null
    }
}