package com.example.recyclerviewinanutshell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val mUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.tvUserName)
        val emailTextView = itemView.findViewById<TextView>(R.id.tvUserEmail)
        val emailImageView = itemView.findViewById<ImageView>(R.id.ivImage)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.user_item_xml, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val user: User = mUser[position]
        // Set item views based on your views and data model
        val nameText = holder.nameTextView
        nameText.text = user.name
        val emailText = holder.emailTextView
        emailText.text = user.email

    }
}