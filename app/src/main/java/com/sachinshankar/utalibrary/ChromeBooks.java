package com.sachinshankar.utalibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChromeBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}

