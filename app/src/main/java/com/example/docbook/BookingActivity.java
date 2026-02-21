package com.example.docbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private TextView tvMainHeader, tvSelectedDate, tvSelectedTime;
    private Button btnSelectDate, btnSelectTime, btnConfirmBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize UI components
        tvMainHeader = findViewById(R.id.tvMainHeader);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        tvSelectedTime = findViewById(R.id.tvSelectedTime);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSelectTime = findViewById(R.id.btnSelectTime);
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking);

        // Apply Stitch's Sunrise Gradient to the header text
        applySunriseGradient(tvMainHeader);

        // Date Selection Logic
        btnSelectDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {
                // UPDATE LOGIC: Switches "Not Selected" to chosen date
                String selectedDate = day + "/" + (month + 1) + "/" + year;
                tvSelectedDate.setText(selectedDate);
                tvSelectedDate.setTextColor(Color.parseColor("#1F2937"));
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        // Time Selection Logic
        btnSelectTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(this, (view, hour, minute) -> {
                // UPDATE LOGIC: Switches "Not Selected" to chosen time
                String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                tvSelectedTime.setText(selectedTime);
                tvSelectedTime.setTextColor(Color.parseColor("#1F2937"));
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        // Final Confirmation Logic
        btnConfirmBooking.setOnClickListener(v -> {
            if (tvSelectedDate.getText().toString().equals("Not Selected") ||
                    tvSelectedTime.getText().toString().equals("Not Selected")) {
                Toast.makeText(this, "Please select date and time first!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_LONG).show();
                // Add code here to navigate to your DashboardActivity
            }
        });
    }

    private void applySunriseGradient(TextView tv) {
        tv.post(() -> {
            Shader textShader = new LinearGradient(0, 0, tv.getWidth(), 0,
                    new int[]{Color.parseColor("#FF6B6B"), Color.parseColor("#FB923C")},
                    null, Shader.TileMode.CLAMP);
            tv.getPaint().setShader(textShader);
            tv.invalidate();
        });
    }
}