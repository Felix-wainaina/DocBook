package com.example.docbook.Data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.mindrot.jbcrypt.BCrypt;

public class UserViewModel extends AndroidViewModel {
    private AppDatabase db;
    private UserDao userDao;
    public UserViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        userDao = db.userDao();
    }

    public LiveData<User> login (String email) {
        return userDao.login(email);
    }

    public void registerUser(String name, String email, String password) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            // 2. Create and save the user
            User newUser = new User();
            newUser.name = name;
            newUser.email = email;
            newUser.password = hashedPassword;
            userDao.registerUser(newUser);
        });
    }

}
