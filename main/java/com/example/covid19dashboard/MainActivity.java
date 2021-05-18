package com.example.covid19dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView tvcases,tvrecovered,tvcritical,tvactive,tvtodaycases,tvtotaldeaths,tvtodaydeaths,tvaffectedcountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.track);
        button.setBackgroundColor(Color.GREEN);

        tvcases=findViewById(R.id.tvcases);
        tvrecovered=findViewById(R.id.tvrecovered);
        tvcritical=findViewById(R.id.tvcritical);
        tvactive=findViewById(R.id.tvactive);
        tvtodaycases=findViewById(R.id.tvtodaycases);
        tvtotaldeaths=findViewById(R.id.tvtotaldeaths);
        tvtodaydeaths=findViewById(R.id.tvtodaydeaths);
        tvaffectedcountries=findViewById(R.id.tvaffectedcountries);

        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollstats);
        pieChart=findViewById(R.id.piechart);

        fetchdata();

    }

    private void fetchdata() {
        String url="https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response.toString());

                    tvcases.setText(jsonObject.getString("cases"));
                    tvrecovered.setText(jsonObject.getString("recovered"));
                    tvcritical.setText(jsonObject.getString("critical"));
                    tvactive.setText(jsonObject.getString("active"));
                    tvtodaycases.setText(jsonObject.getString("todayCases"));
                    tvtotaldeaths.setText(jsonObject.getString("deaths"));
                    tvtodaydeaths.setText(jsonObject.getString("todayDeaths"));
                    tvaffectedcountries.setText(jsonObject.getString("affectedCountries"));



                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvcases.getText().toString()),
                            Color.parseColor("#FFA726")));

                    pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvrecovered.getText().toString()),
                            Color.parseColor("#66BB6A")));

                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvtotaldeaths.getText().toString()),
                            Color.parseColor("#EF5350")));

                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvactive.getText().toString()),
                            Color.parseColor("#29B6F6")));

                    pieChart.startAnimation();



                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                    scrollView.setVisibility(View.VISIBLE);



                } catch (JSONException e) {
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    public void goTrackCountries(View view) {
        startActivity(new Intent(getApplicationContext(),AffectedCountry.class));
    }
}