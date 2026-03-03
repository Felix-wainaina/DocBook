package com.example.docbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public static String formatBookingDate(String rawDate) {
        try {
            // Updated to match your DatePicker output (e.g., "15/10/2026")
            SimpleDateFormat inputFormat = new SimpleDateFormat("d/M/yyyy", Locale.getDefault());

            // Nelson's requested pretty format (e.g., "Thursday, 15 Oct 2026")
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, d MMM yyyy", Locale.getDefault());

            Date date = inputFormat.parse(rawDate);
            return (date != null) ? outputFormat.format(date) : rawDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return rawDate; // Fallback to raw string if parsing fails
        }
    }
}