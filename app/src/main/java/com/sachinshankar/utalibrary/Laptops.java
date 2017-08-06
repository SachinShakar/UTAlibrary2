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

public class Laptops extends AppCompatActivity {

    private Button reserve;
    private TextView tv;
    private  RadioButton cen, arc, sne;
    private RadioGroup location;
    private String loc = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptops);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv = (TextView) findViewById(R.id.description);
        tv.setText(Html.fromHtml(getString(R.string.laptop_detail)));

        reserve = (Button) findViewById(R.id.reserve);

        location = (RadioGroup) findViewById(R.id.location);
        location.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.cen) {
                   loc = "1";
                } else if(checkedId == R.id.arc) {
                    loc = "2";
                } else {
                    loc = "3";
                }
            }

        });

        //When reserve button is clicked
        reserve.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                doReserve();
            }
        });
    }

    private void doReserve() {
        //Toast.makeText(this,"Laptop Reserved Succesfully",Toast.LENGTH_SHORT).show();
        File f = new File(
                "/data/data/com.sachinshankar.utalibrary/shared_prefs/login_preference.xml");
        if (! f.exists()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Laptops.this);
            dialog.setCancelable(true);
            dialog.setTitle("Login Message");
            dialog.setMessage("You must login to reserve");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent loginPage = new Intent(Laptops.this, UserLogin.class);
                    startActivity(loginPage);
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();


        }

        else{
            SharedPreferences sharedpreferences = getSharedPreferences(SigninActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            String s = sharedpreferences.getString("NetID", "");
            if(s=="") {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Laptops.this);
                dialog.setCancelable(true);
                dialog.setTitle("Login Message");
                dialog.setMessage("You must login to reserve");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent loginPage = new Intent(Laptops.this, UserLogin.class);
                        startActivity(loginPage);
                    }
                });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
            else{
                //Toast.makeText(this,"Laptop Reserved Succesfully",Toast.LENGTH_SHORT).show();
                //Intent mainPage = new Intent(Laptops.this, MainActivity.class);
                //startActivity(mainPage);
                new ReserveResource(this).execute(s,"2",loc);

            }
        }
        /*
        AlertDialog.Builder dialog = new AlertDialog.Builder(Laptops.this);
        dialog.setCancelable(true);
        dialog.setTitle("Reserve Confirmation");
        dialog.setMessage("Laptop Reserved Succesfully" );
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent mainPage = new Intent(Laptops.this, MainActivity.class);
                startActivity(mainPage);
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();

        */
    }

}
