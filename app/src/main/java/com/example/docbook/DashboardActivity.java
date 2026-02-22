package com.example.docbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvDoctorName, tvDate, tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // 1. Initialize UI elements
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvDate = findViewById(R.id.tvAppointmentDate);
        tvTime = findViewById(R.id.tvAppointmentTime);

        // 2. Extract data from Intent
        // NOTE: Replace these placeholder strings with keys from Felix/Nelson
        String doctorName = getIntent().getStringExtra("DOCTOR_NAME_KEY");
        String date = getIntent().getStringExtra("DATE_KEY");
        String time = getIntent().getStringExtra("TIME_KEY");

        // 3. Bind data to UI
        if (doctorName != null) tvDoctorName.setText("Dr. " + doctorName);
        if (date != null) tvDate.setText("Date: " + date);
        if (time != null) tvTime.setText("Time: " + time);
    }
}
