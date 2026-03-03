package com.example.docbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
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

        // Apply Sunrise Gradient to the header text
        applySunriseGradient(tvMainHeader);

        // Date Selection Logic (UPDATED with Nelson's Formatter)
        btnSelectDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {

                // 1. Get the raw date from the picker
                String rawDate = day + "/" + (month + 1) + "/" + year;

                // 2. Run it through Nelson's fixed formatter
                String prettyDate = DateFormatter.formatBookingDate(rawDate);

                // 3. Set the beautiful text to the UI
                tvSelectedDate.setText(prettyDate);
                tvSelectedDate.setTextColor(Color.parseColor("#1F2937"));

            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        // Time Selection Logic
        btnSelectTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new TimePickerDialog(this, (view, hour, minute) -> {
                String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                tvSelectedTime.setText(selectedTime);
                tvSelectedTime.setTextColor(Color.parseColor("#1F2937"));
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
        });

        // Final Confirmation Logic
        btnConfirmBooking.setOnClickListener(v -> {
            if (tvSelectedDate.getText().toString().equals("Not Selected") ||
                    tvSelectedTime.getText().toString().equals("Not Selected")) {

                Snackbar.make(findViewById(android.R.id.content), "Please select date and time first!", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(Color.parseColor("#D32F2F"))
                        .show();
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Booking Confirmed!", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(Color.parseColor("#4CAF50"))
                        .show();

                Intent intent = new Intent(BookingActivity.this, DashboardActivity.class);
                // Because we set the pretty text to the TextView earlier, it will pass the pretty date automatically!
                intent.putExtra("EXTRA_DATE", tvSelectedDate.getText().toString());
                intent.putExtra("EXTRA_TIME", tvSelectedTime.getText().toString());
                intent.putExtra("EXTRA_DOCTOR", "Alex Chen");

                startActivity(intent);
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