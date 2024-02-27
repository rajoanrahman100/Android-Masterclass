package com.example.quizapp.model

import com.example.quizapp.R

object Constant {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val questionData = listOf(
            // Question 1
            Question(
                1, "What country does this flag belong to?",
                R.drawable.ic_flag_of_argentina,
                "Argentina", "Australia",
                "Armenia", "Austria", 1
            ),
            // Question 2
            Question(
                2, "What country does this flag belong to?",
                R.drawable.ic_flag_of_australia,
                "Angola", "Austria",
                "Australia", "Armenia", 3
            ),
            // Question 3
            Question(
                3, "What country does this flag belong to?",
                R.drawable.ic_flag_of_brazil,
                "Belarus", "Belize",
                "Brunei", "Brazil", 4
            ),
            // Question 4
            Question(
                4, "What country does this flag belong to?",
                R.drawable.ic_flag_of_belgium,
                "Bahamas", "Belgium",
                "Barbados", "Belize", 2
            ),
            // Question 5
            Question(
                5, "What country does this flag belong to?",
                R.drawable.ic_flag_of_fiji,
                "Gabon", "France",
                "Fiji", "Finland", 3
            ),
            // Question 6
            Question(
                6, "What country does this flag belong to?",
                R.drawable.ic_flag_of_germany,
                "Germany", "Georgia",
                "Greece", "none of these", 1
            ),
            // Question 7
            Question(
                7, "What country does this flag belong to?",
                R.drawable.ic_flag_of_denmark,
                "Dominica", "Egypt",
                "Denmark", "Ethiopia", 3
            ),
            // Question 8
            Question(
                8, "What country does this flag belong to?",
                R.drawable.ic_flag_of_india,
                "Ireland", "Iran",
                "Hungary", "India", 4
            ),
            // Question 9
            Question(
                9, "What country does this flag belong to?",
                R.drawable.ic_flag_of_new_zealand,
                "Australia", "New Zealand",
                "Tuvalu", "United States of America", 2
            ),
            // Question 10
            Question(
                10, "What country does this flag belong to?",
                R.drawable.ic_flag_of_kuwait,
                "Kuwait", "Jordan",
                "Sudan", "Palestine", 1
            )
        )

        questionsList.addAll(questionData)

        return questionsList
    }

}