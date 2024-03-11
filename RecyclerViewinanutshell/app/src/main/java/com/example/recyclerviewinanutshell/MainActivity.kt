package com.example.recyclerviewinanutshell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var userRecyclerView:RecyclerView? = null
    private var users:ArrayList<User>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userRecyclerView=findViewById(R.id.userRecyclerView)
        users = Constants.getUserList()
        // Create adapter passing in the sample user data
        val adapter = UserAdapter(users!!)
        // Attach the adapter to the recyclerview to populate items
        userRecyclerView?.adapter = adapter
        // Set layout manager to position the items
        userRecyclerView?.layoutManager = LinearLayoutManager(this)

    }
}