package com.example.androidexampleroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val userViewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java) }
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_list.adapter = userAdapter
        user_list.layoutManager = LinearLayoutManager(this)

        userViewModel.users.observe(this, Observer { users ->
            userAdapter.setUsers(users)
        })

        save_user_button.setOnClickListener {
            val username = username_input.text.toString()
            val age = age_input.text.toString().toInt()
            val isCool = is_cool_input.text.toString().toLowerCase() == "true"
            val user = User(username, age, isCool)
            userViewModel.addUser(user)
        }
    }
}