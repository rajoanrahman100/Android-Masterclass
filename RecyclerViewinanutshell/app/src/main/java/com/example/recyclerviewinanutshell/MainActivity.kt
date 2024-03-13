package com.example.recyclerviewinanutshell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewinanutshell.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var userRecyclerView:RecyclerView? = null
    private var users:ArrayList<User>? = null
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        users = Constants.getUserList()
        // Create adapter passing in the sample user data
        val adapter = UserAdapter(users!!)
        // Attach the adapter to the recyclerview to populate items
        //binding!!.userRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
       binding!!.userRecyclerView.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}