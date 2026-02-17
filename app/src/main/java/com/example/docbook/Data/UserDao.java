package com.example.docbook.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void registerUser(User user);
    @Query("SELECT * FROM User WHERE email = :email LIMIT 1")
    LiveData<User> login(String email);
}
