package com.sachinshankar.utalibrary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;


import org.json.*;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;


import android.widget.Toast;

public class ReserveBookActivity extends AsyncTask<String,String, String> {
    private Context context;
    private String isbn;

    public ReserveBookActivity(Context context, String isbn) {
        this.context = context;
        this.isbn = isbn;
    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            String s = (String) arg0[0];
            String c = (String) arg0[1];
            String loc = (String) arg0[2];

            String link = "https://utalibrary.000webhostapp.com/reserve-book.php?username=" + s + "&isbn=" + c + "&location=" + loc;
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            //return sb.toString();
            String output = "";

            JSONArray jsonArray = new JSONArray(sb.toString());
            int n = jsonArray.length();


            while (n > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(n - 1);
                output += jsonObject.getString("id");
                n--;
            }


            return output;

        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {

        //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

        if(result != ""){
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setCancelable(true);
            dialog.setTitle("Reserve Confirmation");
            dialog.setMessage("You have successfully reserved the Book. An email with details will be sent to your email address");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent reserveBook = new Intent(context, ReserveBook.class);
                    reserveBook.putExtra("isbn", isbn);
                    context.startActivity(reserveBook);
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();
        }

        else{
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setCancelable(true);
            dialog.setTitle("Reserve Failed");
            dialog.setMessage("Reserve failed. The requested Book in the selected libary location is not available");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();

        }
    }



}
