package com.example.recyclerviewinanutshell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewinanutshell.databinding.UserItemXmlBinding

class UserAdapter(private val mUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(val itemBinding: UserItemXmlBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        fun bindingItemView(user: User) {
            itemBinding.tvUserEmail.text = user.email
            itemBinding.tvUserName.text = user.name
        }

        init {
            itemBinding.ivImage.setOnClickListener {
                val position = adapterPosition

                println("Adapter Position $position")
                println("Recycler View  Position ${RecyclerView.NO_POSITION}")

                //if (position != RecyclerView.NO_POSITION) {
                val user = mUser[position]
                println("User Name ${user.name}")

            }

        }
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the custom layout with Binding
        return ViewHolder(
            UserItemXmlBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val user: User = mUser[position]
        // Set item views based on your views and data model
        holder.bindingItemView(user)

    }
}