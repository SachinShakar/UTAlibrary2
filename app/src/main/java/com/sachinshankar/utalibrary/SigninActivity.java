package com.sachinshankar.utalibrary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;



import android.widget.Toast;
import android.os.Handler;


import org.json.*;


import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

public class SigninActivity extends AsyncTask<String,String, String> {
    private TextView error;
    private Context context;
    public static final String MyPREFERENCES = "login_preference" ;


    //flag 0 means get and 1 means post.(By default it is get.)
    public SigninActivity(Context context,TextView errorFiled) {
        this.context = context;
        this.error = errorFiled;


    }

    @Override
    protected String doInBackground(String... arg0) {
            try{
                String username = (String)arg0[0];
                String password = (String)arg0[1];

                username = username.trim();
                password = password.trim();
                String link="https://utalibrary.000webhostapp.com/login.php";
                String data  = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                //return sb.toString();
                 String output = "";

                JSONArray jsonArray = new JSONArray(sb.toString());
                int n = jsonArray.length();


                while(n>0){
                    JSONObject jsonObject = jsonArray.getJSONObject(n-1);
                    output += jsonObject.getString("id");
                    n--;
                }
                return output;



            } catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

    }

    protected void onPreExecute(){
    }



    @Override
    protected void onPostExecute(String result) {
        if(result.trim().length() <= 0){
            this.error.setText("Login Faile. NetID or password is incorrect");
        }

        else{
            Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show();
            Runnable r = new Runnable() {
                @Override
                public void run(){
                    ((Activity)context).finish();     //<-- put your code in here.
                }
            };

            SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("NetID", result);
            editor.commit();


            Handler h = new Handler();
            h.postDelayed(r, 1000); // <-- the "1000" is the delay time in miliseconds.
            //this.error.setText("Login successful");

        }

    }
}
