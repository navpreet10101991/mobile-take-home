package com.example.rickandmorty.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static String changeDateStyle(String dateString, SimpleDateFormat incomingFormat, SimpleDateFormat outgoingFormat) {

        try {
            Date date = incomingFormat.parse(dateString);
            if (date != null) {
              return outgoingFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }
}