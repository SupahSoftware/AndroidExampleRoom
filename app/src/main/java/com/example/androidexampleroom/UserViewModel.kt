package com.example.androidexampleroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class UserViewModel(
        private val userDatabase: UserDao = UserDatabaseFactory.userDatabase
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        userDatabase.observeUsers()
                .doOnNext { userEntities ->
                    val users = userEntities.map { User(it.username, it.age, it.isCool, it.userId) }
                    _users.postValue(users)
                }
                .subscribe()
    }

    fun addUser(user: User) {
        val userEntity = UserEntity(user.id, user.age, user.isCool, user.username)
        Completable.defer {
            userDatabase.addUser(userEntity)
            Completable.complete()
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}