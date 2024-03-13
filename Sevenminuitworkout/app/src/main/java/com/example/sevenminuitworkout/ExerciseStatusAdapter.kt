package com.example.sevenminuitworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminuitworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val exerciseItemList: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ExerciseViewHolder>() {


    inner class ExerciseViewHolder(binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvItem=binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemExerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            )
        )
    }

    override fun getItemCount(): Int {
        return exerciseItemList.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val model: ExerciseModel = exerciseItemList[position]

        holder.tvItem.text = model.getId().toString()

    }
}