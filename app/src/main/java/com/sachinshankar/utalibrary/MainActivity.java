package com.sachinshankar.utalibrary;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.RunnableFuture;

import static java.util.Objects.isNull;

public class MainActivity extends AppCompatActivity {
    private ArrayList<TextView> alist = new ArrayList<TextView>();
    public void onRefresh(View view){
        new getdatatask(this,alist).execute();
        Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        // TextView t = (TextView)findViewById(R.id.txtsrcl);

        alist.add(0,(TextView)findViewById(R.id.txtsrcl));
        alist.add(1,(TextView)findViewById(R.id.txtsrasl));
        alist.add(2,(TextView)findViewById(R.id.txtsrsel));

        alist.add(3,(TextView)findViewById(R.id.txtcompcl));
        alist.add(4,(TextView)findViewById(R.id.txtcompasl));
        alist.add(5,(TextView)findViewById(R.id.txtcompsel));

        alist.add(6,(TextView)findViewById(R.id.txtlappycl));
        alist.add(7,(TextView)findViewById(R.id.txtlappyasl));
        alist.add(8,(TextView)findViewById(R.id.txtlappysel));

        alist.add(9,(TextView)findViewById(R.id.txtipadcl));
        alist.add(10,(TextView)findViewById(R.id.txtipadasl));
        alist.add(11,(TextView)findViewById(R.id.txtipadsel));

        alist.add(12,(TextView)findViewById(R.id.txtmaccl));
        alist.add(13,(TextView)findViewById(R.id.txtmacasl));
        alist.add(14,(TextView)findViewById(R.id.txtmacsel));

        alist.add(15,(TextView)findViewById(R.id.txtchromecl));
        alist.add(16,(TextView)findViewById(R.id.txtchromeasl));
        alist.add(17,(TextView)findViewById(R.id.txtchromesel));

        alist.add(18,(TextView)findViewById(R.id.txtsurfcl));
        alist.add(19,(TextView)findViewById(R.id.txtsurfasl));
        alist.add(20,(TextView)findViewById(R.id.txtsurfsel));



        new getdatatask(this,alist).execute();

        startService(new Intent(this, ClosingService.class));

        /*
        TextView laptop = (TextView) findViewById(R.id.txtcatlappy);
        laptop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent (MainActivity.this,Laptops.class));
            }
        });

        */

    }

    /*
    public void onPause(){

        super.onPause();
        SharedPreferences sharedpreferences = getSharedPreferences(SigninActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }
    */



    @Override
    public void onDestroy(){

        SharedPreferences sharedpreferences = getSharedPreferences(SigninActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        super.onDestroy();
    }



    public void viewResources(View view)
    {
        switch(view.getId())
        {
            case R.id.txtcatStudyrooms:
                startActivity(new Intent (MainActivity.this,StudyRooms.class));
                break;
            case R.id.txtcatchromebooks:
                startActivity(new Intent (MainActivity.this,ChromeBooks.class));
                break;
            case R.id.txtcatcomp:
                startActivity(new Intent (MainActivity.this,Computers.class));
                break;
            case R.id.txtcatipads:
                startActivity(new Intent (MainActivity.this,IPads.class));
                break;
            case R.id.txtcatlappy:
                startActivity(new Intent (MainActivity.this,Laptops.class));
                break;
            case R.id.txtcatsurfacetablet:
                startActivity(new Intent (MainActivity.this,SurfaceTablets.class));
                break;
            case R.id.txtcatmacbook:
                startActivity(new Intent (MainActivity.this,MacBooks.class));
                break;

        }

    }

    public void watch(LinearLayout l, TextView t)
    {
        if((t.getText()).equals("0"))
            if(l.getBackground()== null)
            {    l.setBackgroundColor(Color.RED);
                Toast.makeText(this,"Will be notified when available!!", Toast.LENGTH_SHORT).show();}
            else{
                l.setBackgroundDrawable(null);
                Toast.makeText(this,"Watch removed!", Toast.LENGTH_SHORT).show();
            }
        else
        {
            Toast.makeText(this, "Already available!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickImgBtn(View view){
        LinearLayout l ;
        TextView t;
        switch (view.getId()) {
            case R.id.imgsrcl:
                t = (TextView)findViewById(R.id.txtsrcl);
                l = (LinearLayout) findViewById(R.id.linsrcl);
                watch(l,t);
                break;
            case R.id.imgsrasl:
                l = (LinearLayout) findViewById(R.id.linsrasl);
                t = (TextView)findViewById(R.id.txtsrasl);
                watch(l,t);
                break;
            case R.id.imgsrsel:
                l = (LinearLayout) findViewById(R.id.linsrsel);
                t = (TextView)findViewById(R.id.txtsrsel);
                watch(l,t);


            case R.id.imgcompcl:
                t = (TextView)findViewById(R.id.txtcompcl);
                l = (LinearLayout) findViewById(R.id.lincompcl);
                watch(l,t);
                break;
            case R.id.imgcompasl:
                l = (LinearLayout) findViewById(R.id.lincompasl);
                t = (TextView)findViewById(R.id.txtcompasl);
                watch(l,t);
                break;
            case R.id.imgcompsel:
                l = (LinearLayout) findViewById(R.id.lincompsel);
                t = (TextView)findViewById(R.id.txtcompsel);
                watch(l,t);


            case R.id.imglappycl:
                t = (TextView)findViewById(R.id.txtlappycl);
                l = (LinearLayout) findViewById(R.id.linlappycl);
                watch(l,t);
                break;
            case R.id.imglappyasl:
                l = (LinearLayout) findViewById(R.id.linlappyasl);
                t = (TextView)findViewById(R.id.txtlappyasl);
                watch(l,t);
                break;
            case R.id.imglappysel:
                l = (LinearLayout) findViewById(R.id.linlappysel);
                t = (TextView)findViewById(R.id.txtlappysel);
                watch(l,t);


            case R.id.imgipadcl:
                t = (TextView)findViewById(R.id.txtipadcl);
                l = (LinearLayout) findViewById(R.id.linipadcl);
                watch(l,t);
                break;
            case R.id.imgipadasl:
                l = (LinearLayout) findViewById(R.id.linipadasl);
                t = (TextView)findViewById(R.id.txtipadasl);
                watch(l,t);
                break;
            case R.id.imgipadsel:
                l = (LinearLayout) findViewById(R.id.linipadsel);
                t = (TextView)findViewById(R.id.txtipadsel);
                watch(l,t);

            case R.id.imgmaccl:
                t = (TextView)findViewById(R.id.txtmaccl);
                l = (LinearLayout) findViewById(R.id.linmaccl);
                watch(l,t);
                break;
            case R.id.imgmacasl:
                l = (LinearLayout) findViewById(R.id.linmacasl);
                t = (TextView)findViewById(R.id.txtmacasl);
                watch(l,t);
                break;
            case R.id.imgmacsel:
                l = (LinearLayout) findViewById(R.id.linmacsel);
                t = (TextView)findViewById(R.id.txtmacsel);
                watch(l,t);


            case R.id.imgchromecl:
                t = (TextView)findViewById(R.id.txtchromecl);
                l = (LinearLayout) findViewById(R.id.linchromecl);
                watch(l,t);
                break;
            case R.id.imgchromeasl:
                l = (LinearLayout) findViewById(R.id.linchromeasl);
                t = (TextView)findViewById(R.id.txtchromeasl);
                watch(l,t);
                break;
            case R.id.imgchromesel:
                l = (LinearLayout) findViewById(R.id.linchromesel);
                t = (TextView)findViewById(R.id.txtchromesel);
                watch(l,t);

            case R.id.imgsurfcl:
                t = (TextView)findViewById(R.id.txtsurfcl);
                l = (LinearLayout) findViewById(R.id.linsurfcl);
                watch(l,t);
                break;
            case R.id.imgsurfasl:
                l = (LinearLayout) findViewById(R.id.linsurfasl);
                t = (TextView)findViewById(R.id.txtsurfasl);
                watch(l,t);
                break;
            case R.id.imgsurfsel:
                l = (LinearLayout) findViewById(R.id.linsurfsel);
                t = (TextView)findViewById(R.id.txtsurfsel);
                watch(l,t);





        }
    }


}


