package com.example.rickandmorty.utilities;

import java.text.SimpleDateFormat;
import java.util.Locale;

public interface Keys {

    SimpleDateFormat NETWORKING_INCOMING_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.CANADA);
    SimpleDateFormat DISPLAYING_FORMAT = new SimpleDateFormat("MMM dd, yyyy", Locale.CANADA);
}