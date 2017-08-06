package com.sachinshankar.utalibrary;

import android.app.AlertDialog;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;

public class ReserveBook extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private ImageView iv;
    private RadioButton cen, arc, sne;
    private RadioGroup location;
    private String loc = "1";
    private Button reserve;
    private TextView isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_book);
        //tv = new TextView(this);
        //tv.setText(getIntent().getStringExtra("isbn"));
        //ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.c1);
        //constraintLayout.addView(tv);
        iv = (ImageView) findViewById(R.id.imageView);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.description);
        isbn = (TextView) findViewById(R.id.isbn);
        int id = this.getResources().getIdentifier("b" + getIntent().getStringExtra("isbn"), "drawable", this.getPackageName());
        iv.setImageResource(id);
        new FetchBookDetailsActivity(this, tv1, tv2, isbn).execute(getIntent().getStringExtra("isbn"));

        reserve = (Button) findViewById(R.id.reserve);

        location = (RadioGroup) findViewById(R.id.location);
        location.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

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

    private void doReserve(){
        File f = new File(
                "/data/data/com.sachinshankar.utalibrary/shared_prefs/login_preference.xml");

        if (! f.exists()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ReserveBook.this);
            dialog.setCancelable(true);
            dialog.setTitle("Login Message");
            dialog.setMessage("You must login to reserve");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent loginPage = new Intent(ReserveBook.this, UserLogin.class);
                    startActivity(loginPage);
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();


        }

        else
        {
            SharedPreferences sharedpreferences = getSharedPreferences(SigninActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            String s = sharedpreferences.getString("NetID", "");
            if(s=="") {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ReserveBook.this);
                dialog.setCancelable(true);
                dialog.setTitle("Login Message");
                dialog.setMessage("You must login to reserve");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent loginPage = new Intent(ReserveBook.this, UserLogin.class);
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
                //isbn of book
                String ss = isbn.getText().toString();
                //Toast.makeText(this,ss,Toast.LENGTH_SHORT).show();
                new ReserveBookActivity(this, ss).execute(s,ss,loc);

            }

        }

    }


}
