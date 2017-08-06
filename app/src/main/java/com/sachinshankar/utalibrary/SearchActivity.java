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
import android.view.View;
import android.widget.Button;


import android.widget.Toast;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.graphics.Color;
import android.view.Gravity;

public class SearchActivity extends AsyncTask<String,String, String> {
    private Context context;
    private TextView tv;
    private TableLayout t1;
    private int width;

    public SearchActivity(Context context, TableLayout t1, TextView tv, int width) {
        this.context = context;
        this.tv = tv;
        this.t1 = t1;
        this.width = width;
    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            String s = (String) arg0[0];

            String link = "https://utalibrary.000webhostapp.com/search-book.php?text=" + s ;
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
                //tv.setText(result);

                TableRow row0 = new TableRow(context);
                TextView tv0 = new TextView(context);
                tv0.setText("No");
                tv0.setTextColor(Color.BLACK);
                row0.addView(tv0);
                TextView tv1 = new TextView(context);
                tv1.setText(" Title ");
                tv1.setTextColor(Color.BLACK);
                tv1.setWidth( (int) (width * 0.3)  );
                row0.addView(tv1);
                TextView tv2 = new TextView(context);
                tv2.setText(" Description ");
                tv2.setTextColor(Color.BLACK);
                tv2.setWidth( (int) (width * 0.45)  );
                row0.addView(tv2);
                t1.addView(row0);

                int n = jsonArray.length();
                int i = 1;
                while (n > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(n - 1);
                    TableRow row = new TableRow(context);
                    TextView tv10 = new TextView(context);
                    tv10.setText(""+i);
                    tv10.setTextColor(Color.BLACK);
                    TableLayout.LayoutParams param1 = new TableLayout.LayoutParams();
                    param1.setMargins(0, 0, 0, 60);
                    row.addView(tv10);
                    TextView tv11 = new TextView(context);
                    tv11.setText(jsonObject.getString("title") );
                    tv11.setTextColor(Color.BLACK);
                    tv11.setWidth( (int) (width * 0.3)  );
                    row.addView(tv11);
                    TextView tv12 = new TextView(context);
                    tv12.setText(jsonObject.getString("description"));
                    tv12.setTextColor(Color.BLACK);
                    tv12.setWidth( (int) (width * 0.45)  );
                    TableRow.LayoutParams param3  = new TableRow.LayoutParams();
                    param3.setMargins(0, 0, 0, 24);
                    row.addView(tv12, param3);
                    Button b = new Button(context);
                    b.setText("SELET");
                    //b.setBackgroundColor(Color.rgb(135,206,250	));
                    TableRow.LayoutParams param2  = new TableRow.LayoutParams();
                    param2.setMargins(0, 40, 0, 0);
                    b.setWidth( (int) (width * 0.15)  );
                    b.setGravity(Gravity.CENTER_VERTICAL);
                    b.setMinHeight(0);
                    b.setMinimumHeight(0);
                    b.setId( Integer.parseInt(jsonObject.getString("isbn")) );
                    b.setOnClickListener(new Button.OnClickListener() {
                        public void onClick(View v) {
                            bookDetails(v.getId() + "");
                        }
                    });
                    row.addView(b, param2);
                    t1.addView(row, param1);

                    n--;
                    i++;
                }

            } else {
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
