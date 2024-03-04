package com.example.dialogs

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSnackbar = findViewById<Button>(R.id.snackbar_btn)
        val btnAlertDialog = findViewById<Button>(R.id.alert_dialog_btn)
        val btnCustomDialog = findViewById<Button>(R.id.custom_dialog_btn)

        btnSnackbar.setOnClickListener {
            Snackbar.make(it, "Snackbar is clicked", Snackbar.LENGTH_SHORT).show()
        }

        btnAlertDialog.setOnClickListener {
            alertDialogFunction()
        }


    }

    private fun alertDialogFunction() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("Do you want to close?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing Positive Action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            Toast.makeText(this, "clicked yes", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        //performing Negative Action
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(this, "clicked no", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        //performing Neutral Action
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(this, "clicked cancel\n operation cancel", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show() // show the dialog
    }


}