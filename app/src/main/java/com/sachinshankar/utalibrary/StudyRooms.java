package com.sachinshankar.utalibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StudyRooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_rooms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}