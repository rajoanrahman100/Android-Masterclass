package com.example.sevenminuitworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sevenminuitworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private var finishActivityBinding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finishActivityBinding = ActivityFinishBinding.inflate(layoutInflater)
        //Todo 3: Then replace the setContentView parameter with binding?.root
        setContentView(finishActivityBinding?.root)
        finishActivityBinding?.btnFinish?.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finishActivityBinding = null
    }
}