package com.example.docbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatBookingDate(String rawDate) {
        // Example: Converts "12-05" to "Friday, 12th May"
        // This logic assumes 2026 based on the current project year
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, d MMM", Locale.getDefault());
            Date date = inputFormat.parse(rawDate);
            return (date != null) ? outputFormat.format(date) : rawDate;
        } catch (ParseException e) {
            return rawDate; // Fallback to raw string if parsing fails
        }
    }
}
