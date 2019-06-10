package com.example.androidexampleroom

import android.app.Application
import androidx.room.Room

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        UserDatabaseFactory.userDatabase = Room.databaseBuilder(this, UserDatabase::class.java, "users.db")
                .fallbackToDestructiveMigration()
                .build()
                .userDao()
    }
}