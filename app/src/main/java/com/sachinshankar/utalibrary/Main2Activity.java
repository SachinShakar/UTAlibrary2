package com.sachinshankar.utalibrary;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private Button resource;
    private Button books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        resource = (Button) findViewById(R.id.resource);
        books = (Button) findViewById(R.id.books);

        resource.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent rsourcePage = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(rsourcePage);
            }
        });

        books.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent rsourcePage = new Intent(Main2Activity.this, BookSearch.class);
                startActivity(rsourcePage);
            }
        });

    }
}
