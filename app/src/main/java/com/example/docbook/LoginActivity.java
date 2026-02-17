package com.example.docbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docbook.Data.AppDatabase;
import com.example.docbook.Data.User;
import com.example.docbook.Data.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import org.mindrot.jbcrypt.BCrypt;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText emailInput;
    private EditText passwordInput;

    private TextView noAccount;
    private UserViewModel userViewModel;

    public void saveSession(User user) {
        SharedPreferences sharedPref = getSharedPreferences("DocBookPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Save the login state
        editor.putBoolean("isLoggedIn", true);

        // Save user details to display on the profile/home screen later
        editor.putInt("userId", user.id);
        editor.putString("userName", user.name);
        editor.putString("userEmail", user.email);

        editor.apply(); // apply() runs in the background; commit() freezes the UI
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Inside your Activity's onCreate
        btnLogin = findViewById(R.id.btnLogin);
        emailInput = findViewById(R.id.etEmail);
        passwordInput = findViewById(R.id.etPassword);

        noAccount = findViewById(R.id.no_account);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        btnLogin.setOnClickListener(v ->{
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                if(email.isEmpty()){
                    emailInput.setError("Email is required");
                    emailInput.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    passwordInput.setError("Password is required");
                    passwordInput.requestFocus();
                    return;
                }
                if(password.length() < 6) {
                    passwordInput.setError("Password must be at least 6 characters");
                    passwordInput.requestFocus();
                    return;
                }
                userViewModel.login(email).observe(
                        this, user -> {
                            if (user != null) {
                                AppDatabase.databaseWriteExecutor.execute(() -> {
                                    boolean isMatch = BCrypt.checkpw(password, user.password);

                                    runOnUiThread(() -> {
                                        if (isMatch) {
                                            saveSession(user);
                                            startActivity(new Intent(this, MainActivity.class));
                                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            passwordInput.setError("Incorrect password");
                                        }
                                    });
                                });
                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        );

        noAccount.setOnClickListener(v -> {
            startActivity(new Intent(this, SignupActivity.class));
        });
    }
}
