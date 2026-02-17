package com.example.docbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.docbook.Data.UserViewModel;

public class SignupActivity extends AppCompatActivity {

    private Button signupBtn;
    private EditText nameInput;
    private EditText emailInput;
    private EditText passwordInput;

    private EditText cpasswordInput;

    private TextView proceedLogin;

    private UserViewModel userViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupBtn = findViewById(R.id.btnSignup);
        nameInput = findViewById(R.id.etName);
        emailInput = findViewById(R.id.etEmail);
        nameInput = findViewById(R.id.etName);
        passwordInput = findViewById(R.id.etPassword);
        cpasswordInput = findViewById(R.id.etCpassword);

        proceedLogin = findViewById(R.id.proceed_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        signupBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            String cpassword = cpasswordInput.getText().toString();

            // 3. Validation Logic
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length() < 6) {
                passwordInput.setError("Password must be at least 6 characters");
                passwordInput.requestFocus();
                return;
            }
            if (!cpassword.equals(password)) {
                cpasswordInput.setError("Passwords do not match");
                cpasswordInput.requestFocus();
                return;
            }
            userViewModel.registerUser(name, email, password);
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            finish();

        });
        proceedLogin.setOnClickListener(v ->{
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

}
