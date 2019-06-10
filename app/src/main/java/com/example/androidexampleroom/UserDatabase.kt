package com.example.androidexampleroom

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable

@Entity(tableName = "user_list")
data class UserEntity(
        @PrimaryKey
        @ColumnInfo(name = "user_id")
        val userId: String,

        @ColumnInfo(name = "user_age")
        val age: Int,

        @ColumnInfo(name = "user_is_cool")
        val isCool: Boolean,

        @ColumnInfo(name = "user_name")
        val username: String
)

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun addUser(userEntity: UserEntity)

    @Query("SELECT * FROM user_list")
    fun observeUsers(): Flowable<List<UserEntity>>
}

@Database(version = 1, entities = [(UserEntity::class)], exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

object UserDatabaseFactory {
    lateinit var userDatabase: UserDao
}