
package com.sachinshankar.utalibrary;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by uppinakai on 6/28/17.
 */

public class getdatatask extends AsyncTask<ArrayList<HomeData>,ArrayList<HomeData>, ArrayList<HomeData>> {
    private Context mContext;
    ArrayList<HomeData> mAvailable = new ArrayList<HomeData>();
    ArrayList<TextView> t;

    public getdatatask(Activity context,ArrayList<TextView> t)
    {
        this.t = t;
        this.mContext = context;
    }



    @Override
    protected ArrayList<HomeData> doInBackground(ArrayList<HomeData>... strings) {
        String line = null;
        String output = "";




        try{

            String link = "https://utalibrary.000webhostapp.com/available.php";
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();


            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            JSONObject jsonObject = new JSONObject(sb.toString());


            JSONObject row = jsonObject.getJSONObject("1");
            mAvailable.add(0,new HomeData("Study Rooms",row.getInt("cen"),row.getInt("afa"),row.getInt("sel")));

            row = jsonObject.getJSONObject("2");
            mAvailable.add(1,new HomeData("Computers",row.getInt("cen"),row.getInt("afa"),row.getInt("sel")));

            row = jsonObject.getJSONObject("3");
            mAvailable.add(2,new HomeData("Laptops",row.getInt("cen"),row.getInt("afa"),row.getInt("sel")));

            row = jsonObject.getJSONObject("4");
            mAvailable.add(3,new HomeData("Ipads",row.getInt("cen"),row.getInt("afa"),row.getInt("sel")));

            row = jsonObject.getJSONObject("5");
            mAvailable.add(4,new HomeData("Macbooks",row.getInt("cen"),row.getInt("afa"),row.getInt("sel")));

            row = jsonObject.getJSONObject("6");
            mAvailable.add(5,new HomeData("Chromebooks",row.getInt("cen"),row.getInt("afa"),row.getInt("sel")));

            row = jsonObject.getJSONObject("7");
            mAvailable.add(6,new HomeData("Surfaces",row.getInt("cen"),row.getInt("afa"),row.getInt("sel")));










        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{

        }

        return mAvailable;

    }

    @Override
    protected void onPostExecute(ArrayList<HomeData> s) {
        super.onPostExecute(s);
        // String a =  Integer.toString(((Activity) mContext).findViewById(R.id.txtsrcl).getId());
        //  mContext.

        // this.t.get(0).setText("askda");
        this.t.get(0).setText(String.valueOf(mAvailable.get(0).getmCentral_lib()));
        this.t.get(1).setText(String.valueOf(mAvailable.get(0).getmArchitecture_lib()));
        this.t.get(2).setText(String.valueOf(mAvailable.get(0).getmScience_lib()));


        this.t.get(3).setText(String.valueOf(mAvailable.get(1).getmCentral_lib()));
        this.t.get(4).setText(String.valueOf(mAvailable.get(1).getmArchitecture_lib()));
        this.t.get(5).setText(String.valueOf(mAvailable.get(1).getmScience_lib()));

        this.t.get(6).setText(String.valueOf(mAvailable.get(2).getmCentral_lib()));
        this.t.get(7).setText(String.valueOf(mAvailable.get(2).getmArchitecture_lib()));
        this.t.get(8).setText(String.valueOf(mAvailable.get(2).getmScience_lib()));

        this.t.get(9).setText(String.valueOf(mAvailable.get(3).getmCentral_lib()));
        this.t.get(10).setText(String.valueOf(mAvailable.get(3).getmArchitecture_lib()));
        this.t.get(11).setText(String.valueOf(mAvailable.get(3).getmScience_lib()));

        this.t.get(12).setText(String.valueOf(mAvailable.get(4).getmCentral_lib()));
        this.t.get(13).setText(String.valueOf(mAvailable.get(4).getmArchitecture_lib()));
        this.t.get(14).setText(String.valueOf(mAvailable.get(4).getmScience_lib()));

        this.t.get(15).setText(String.valueOf(mAvailable.get(5).getmCentral_lib()));
        this.t.get(16).setText(String.valueOf(mAvailable.get(5).getmArchitecture_lib()));
        this.t.get(17).setText(String.valueOf(mAvailable.get(5).getmScience_lib()));

        this.t.get(18).setText(String.valueOf(mAvailable.get(6).getmCentral_lib()));
        this.t.get(19).setText(String.valueOf(mAvailable.get(6).getmArchitecture_lib()));
        this.t.get(20).setText(String.valueOf(mAvailable.get(6).getmScience_lib()));




       /* this.t.get(21).setText(String.valueOf(mAvailable.get(7).getmCentral_lib()));
        this.t.get(22).setText(String.valueOf(mAvailable.get(7).getmArchitecture_lib()));
        this.t.get(23).setText(String.valueOf(mAvailable.get(7).getmScience_lib()));

        this.t.get(8).setText(String.valueOf(mAvailable.get(8).getmCentral_lib()));
        this.t.get(8).setText(String.valueOf(mAvailable.get(8).getmArchitecture_lib()));
        this.t.get(8).setText(String.valueOf(mAvailable.get(8).getmScience_lib()));*/
        // ((TextView)(mContext).findViewById(R.id.txtsrcl)).setText(String.valueOf(mAvailable.get(0).getmCentral_lib()));
  /*      ((TextView)(mContext).findViewById(R.id.txtsrasl)).setText(mAvailable.get(0).getmArchitecture_lib());
        ((TextView)(mContext).findViewById(R.id.txtsrsel)).setText(mAvailable.get(0).getmScience_lib());

        ((TextView)((Activity)mContext).findViewById(R.id.txtcompcl)).setText(mAvailable.get(1).getmCentral_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtcompasl)).setText(mAvailable.get(1).getmArchitecture_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtcompsel)).setText(mAvailable.get(1).getmScience_lib());

        ((TextView)((Activity)mContext).findViewById(R.id.txtlappycl)).setText(mAvailable.get(2).getmCentral_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtlappyasl)).setText(mAvailable.get(2).getmArchitecture_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtlappysel)).setText(mAvailable.get(2).getmScience_lib());

        ((TextView)((Activity)mContext).findViewById(R.id.txtipadcl)).setText(mAvailable.get(3).getmCentral_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtipadasl)).setText(mAvailable.get(3).getmArchitecture_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtipadsel)).setText(mAvailable.get(3).getmScience_lib());

        ((TextView)((Activity)mContext).findViewById(R.id.txtmaccl)).setText(mAvailable.get(4).getmCentral_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtmacasl)).setText(mAvailable.get(4).getmArchitecture_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtmacsel)).setText(mAvailable.get(4).getmScience_lib());

        ((TextView)((Activity)mContext).findViewById(R.id.txtchromecl)).setText(mAvailable.get(5).getmCentral_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtchromeasl)).setText(mAvailable.get(5).getmArchitecture_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtchromesel)).setText(mAvailable.get(5).getmScience_lib());

        ((TextView)((Activity)mContext).findViewById(R.id.txtsurfcl)).setText(mAvailable.get(6).getmCentral_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtsurfasl)).setText(mAvailable.get(6).getmArchitecture_lib());
        ((TextView)((Activity)mContext).findViewById(R.id.txtsurfsel)).setText(mAvailable.get(6).getmScience_lib());*/



    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
