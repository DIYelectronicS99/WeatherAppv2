package com.example.weatherappv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    int finalcityname;

    private RecyclerView recyclerView;
    private  ArrayList<Datalist> arrayList;

    TextView city, month,cityView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rclv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        arrayList = new ArrayList<>();


        city = findViewById(R.id.et1);
        month = findViewById(R.id.et2);
        cityView  = findViewById(R.id.cityTV);
        btn = findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                getCitycode();

               // if (cityView.getText().toString() != "")
                 //getWeatherdata(Integer.parseInt(cityView.getText().toString()));


            }
        });

        //getWeatherdata();
    }



    public void getCitycode(){


        String in_city = city.getText().toString();
        String locurlprim = "https://api.meteostat.net/v1/stations/search?q=";
        String locurlpost = "&key=wMzoCfs8";

        String locurl = locurlprim.concat(in_city).concat(locurlpost);
        Log.i("final citycode url:", locurl);
        JsonObjectRequest  request = new JsonObjectRequest(Request.Method.GET, locurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray mainobj = response.getJSONArray("data");
                    JSONObject subobj = mainobj.getJSONObject(0);
                     finalcityname = subobj.getInt("id");
                    cityView.setText(String.valueOf(finalcityname));
                    getWeatherdata(Integer.parseInt(cityView.getText().toString()));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


        //return finalcityname;
    }

    public  void getWeatherdata(int citycode){
        Dataadapter dataadapter;

        final String endmonth = month.getText().toString();
        if (endmonth == "")
            return;
        String cityweatherurlprim = "https://api.meteostat.net/v1/history/monthly?station=".concat(String.valueOf(citycode));
        String cityweatherurl = cityweatherurlprim.concat("&start=2019-01&end=2019-").concat(endmonth).concat("&key=wMzoCfs8");
        Log.i("final weather url:", cityweatherurl);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, cityweatherurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                for(int i=0; i< Integer.parseInt(endmonth) ; i++){

                    try {
                        JSONArray weatherobj = response.getJSONArray("data");
                        JSONObject weathermonthobj = weatherobj.getJSONObject(i);
                        String viewtemp = weathermonthobj.getString("temperature_mean");
                        String viewrain = weathermonthobj.getString("precipitation");
                        String viewmonth = weathermonthobj.getString("month");
                        String viewpressure = weathermonthobj.getString("pressure");


                        arrayList.add(new Datalist(viewmonth, viewtemp, viewrain, viewpressure)) ;
                        Dataadapter dataadapter = new Dataadapter(arrayList, getApplicationContext());
                        recyclerView.setAdapter(dataadapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
