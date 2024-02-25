package com.example.agecalculator

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDateTextView: TextView? = null
    private var tvSelectedMinutesTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDateTextView = findViewById<TextView>(R.id.tvSelectedDate)
        tvSelectedMinutesTextView = findViewById<TextView>(R.id.tvSelectedMinutes)

        btnDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        //In order to use date picker, we need couple of things.
        // Year, Month and Day of the month.

        val myCalendar = Calendar.getInstance();
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                print("$selectedYear-$selectedMonth-$selectedDayOfMonth")
                Log.d(
                    "Date Picker Value",
                    "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
                );
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
                tvSelectedDateTextView?.text = selectedDate
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val selectedDateFormat = dateFormat.parse(selectedDate)
                val selectedDateInMinutes = selectedDateFormat.time / 60000
                val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / 60000
                val differentInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvSelectedMinutesTextView?.text = differentInMinutes.toString()

            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate =
            System.currentTimeMillis() - 86400000 //Do not display future dates in date picker from the current date
        dpd.show()
    }
}