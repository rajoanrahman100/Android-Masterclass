package com.example.sevenminuitworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminuitworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val exerciseItemList: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ExerciseViewHolder>() {


    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    inner class ExerciseViewHolder(binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }

    /**
     * Inflates the item view which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemExerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            )
        )
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return exerciseItemList.size
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val model: ExerciseModel = exerciseItemList[position]

        holder.tvItem.text = model.getId().toString()

        when {
            model.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_thin_color_accent_border
                )

                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }

            model.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_accent_background
                )

                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }

            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_gray_background
                )

                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }

    }
}
