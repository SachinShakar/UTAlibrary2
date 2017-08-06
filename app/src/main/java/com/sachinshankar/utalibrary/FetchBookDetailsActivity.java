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

import android.text.Html;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;


import android.widget.Toast;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.graphics.Color;
import android.view.Gravity;

public class FetchBookDetailsActivity extends AsyncTask<String,String, String> {
    private Context context;
    private TextView tv1;
    private TextView tv2;
    private TextView isbn;



    public FetchBookDetailsActivity(Context context, TextView tv1, TextView tv2, TextView isbn) {
        this.context = context;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.isbn = isbn;

    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            String s = (String) arg0[0];

            String link = "https://utalibrary.000webhostapp.com/fetch-book-details.php?text=" + s ;
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


            return sb.toString();
            /*String output = "";

            JSONArray jsonArray = new JSONArray(sb.toString());
            int n = jsonArray.length();


            while (n > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(n - 1);
                output += jsonObject.getString("isbn") + "\n";
                n--;
            }


            return output;
            */

        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {

        try {

            JSONArray jsonArray = new JSONArray(result);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                tv1.setText(Html.fromHtml("<h2>" + jsonObject.getString("title") + "</h2"));
                tv2.setText(Html.fromHtml("<h3>Description</h3> <p>" + jsonObject.getString("description") +
                        "</p> <h3>Author</h3> <p>" + jsonObject.getString("author") + "</p>" +
                        "<h3>Availabilty</h3> <p> <ul> <li>Central Library: " +
                        jsonObject.getString("cen") + "</li>  <li>Architecture Library: " +
                        jsonObject.getString("arc") + "</li> <li>Science & Eng. Library: " +
                        jsonObject.getString("sne") + "</li> </ul> </p>"));


                isbn.setText(jsonObject.getString("isbn"));
            }

            else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setCancelable(true);
                dialog.setTitle("Info");
                dialog.setMessage("No book found");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                final AlertDialog alert = dialog.create();
                alert.show();

            }


        }
        catch (Exception e) {

        }

    }

    //Function to display and reserve details of selected book
    public void bookDetails(String isbn){
        Intent reserveBook = new Intent(context, ReserveBook.class);
        reserveBook.putExtra("isbn", isbn);
        ((Activity)context).startActivity(reserveBook);
    }


}
