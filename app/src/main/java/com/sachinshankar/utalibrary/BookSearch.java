package com.sachinshankar.utalibrary;

import android.app.AlertDialog;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.view.View;
import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.TextView;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.graphics.Color;
import android.view.Gravity;

public class BookSearch extends AppCompatActivity {
    private Button search;
    private TextView tv;
    private EditText searchText;
    private TextView error;
    private TableLayout t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        tv = (TextView) findViewById(R.id.result);
        t1 = (TableLayout) findViewById(R.id.result_table);
        search = (Button) findViewById(R.id.search_button);

        //When reserve button is clicked
        search.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                doSearch();
            }
        });

    }

    public void doSearch(){
        //clear the previous search results.
       if(t1.getChildCount() > 0)
            t1.removeAllViews();

        searchText = (EditText) findViewById(R.id.search_text);

        //if the user didnt enter any text for search
        if(searchText.getText().toString().trim().length() <= 0){
            AlertDialog.Builder dialog = new AlertDialog.Builder(BookSearch.this);
            dialog.setCancelable(true);
            dialog.setTitle("Error Messege");
            dialog.setMessage("Enter some text in  search box");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();
        }

        else{

            int width = findViewById(R.id.c1).getWidth();
            new SearchActivity(this, t1, tv, width).execute(searchText.getText().toString());
            /*
            TableLayout ll = (TableLayout) findViewById(R.id.result_table);
            TableRow tbrow0 = new TableRow(this);
            TextView tv0 = new TextView(this);
            tv0.setText(" Sl.No ");
            tv0.setTextColor(Color.BLACK);
            tbrow0.addView(tv0);
            TextView tv1 = new TextView(this);
            tv1.setText(" Product ");
            tv1.setTextColor(Color.BLACK);
            tbrow0.addView(tv1);
            TextView tv2 = new TextView(this);
            tv2.setText(" Unit Price ");
            tv2.setTextColor(Color.BLACK);
            tbrow0.addView(tv2);
            TextView tv3 = new TextView(this);
            tv3.setText(" Stock Remaining ");
            tv3.setTextColor(Color.BLACK);
            tbrow0.addView(tv3);
            ll.addView(tbrow0);
            for (int i = 1; i <50; i++) {
                int width = findViewById(R.id.c1).getWidth();
                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText("hello 123456 222sadasdsadsada sadadsd");
                checkBox.setWidth( (int) (width * 0.6)  );

                ImageButton addBtn = new ImageButton(this);
                addBtn.setImageResource(R.drawable.fixtures);

                TextView qty = new TextView(this);
                qty.setText("10");
                qty.setGravity(Gravity.CENTER);


                row.addView(checkBox);
                row.addView(qty);
                row.addView(addBtn);

                ll.addView(row);
            }

            */

        }



    }
}
