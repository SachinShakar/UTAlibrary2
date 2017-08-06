package com.sachinshankar.utalibrary;

import android.content.Context;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.TextView;
import android.text.Html;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;



import java.io.File;

import  android.content.SharedPreferences;
import android.content.Context;



import org.json.JSONArray;
import org.json.JSONObject;

public class Computers extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv = (TextView) findViewById(R.id.description);
        tv.setText(Html.fromHtml(getString(R.string.computer_detail)));

    }
}