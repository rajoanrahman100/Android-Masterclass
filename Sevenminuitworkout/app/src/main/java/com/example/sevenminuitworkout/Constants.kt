package com.example.sevenminuitworkout

object Constants {
    fun getDefaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()

        //Exercises List
        val exercises = listOf<ExerciseModel>(
            ExerciseModel(
                1, "Jumping Jacks", R.drawable.ic_jumping_jacks, false, false
            ),
            ExerciseModel(
                2, "Push Up", R.drawable.ic_push_up, false, false
            ),
            ExerciseModel(
                3, "Squat", R.drawable.ic_squat, false, false
            ),
            ExerciseModel(
                4, "Wall Sit", R.drawable.ic_wall_sit, false, false
            )
        )


        exerciseList.addAll(exercises)

        return exerciseList
    }
}