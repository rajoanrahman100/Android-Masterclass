package com.example.sevenminuitworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sevenminuitworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var exerciseViewMainBinding: ActivityExerciseBinding? = null

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

    }
}