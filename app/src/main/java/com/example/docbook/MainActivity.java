package com.example.docbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.core.splashscreen.SplashScreen;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private void logout() {
        // 1. Access the same SharedPreferences file
        SharedPreferences sharedPref = getSharedPreferences("DocBookPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // 2. Clear everything (or just the specific keys)
        editor.clear();
        editor.apply();

        // 3. Kick the user back to the Login screen
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        // This flag ensures the user can't hit the 'Back' button to return to the Main screen
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check if logged in
        SharedPreferences sharedPreferences = getSharedPreferences("DocBookPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // User is not logged in, redirect to LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity so user can't go back to it
            return;
        }

        // 3. If logged in, set the actual layout
        setContentView(R.layout.activity_main);

        // Now you can setup your logout button
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> logout());
    }
}