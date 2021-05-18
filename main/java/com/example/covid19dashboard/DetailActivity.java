package com.example.covid19dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DetailActivity extends AppCompatActivity {
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;
    PieChart pieChart;
    private int positioncountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        positioncountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details Of"+AffectedCountry.countryModelList.get(positioncountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        pieChart=findViewById(R.id.PieChart);
        tvCountry=findViewById(R.id.tvCountry);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvTodayCases=findViewById(R.id.tvTodaycases);
        tvTotalDeaths=findViewById(R.id.tvDeaths);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);



        tvCountry.setText(AffectedCountry.countryModelList.get(positioncountry).getCountry());
        tvCases.setText(AffectedCountry.countryModelList.get(positioncountry).getCases());
        tvRecovered.setText(AffectedCountry.countryModelList.get(positioncountry).getRecovered());
        tvCritical.setText(AffectedCountry.countryModelList.get(positioncountry).getCriticalcases());
        tvActive.setText(AffectedCountry.countryModelList.get(positioncountry).getActivecase());
        tvTodayDeaths.setText(AffectedCountry.countryModelList.get(positioncountry).getTodaydeaths());
        tvTotalDeaths.setText(AffectedCountry.countryModelList.get(positioncountry).getDeaths());
        tvTodayCases.setText(AffectedCountry.countryModelList.get(positioncountry).getTodaycases());

        pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()),
                Color.parseColor("#FFA726")));

        pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvRecovered.getText().toString()),
                Color.parseColor("#66BB6A")));

        pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()),
                Color.parseColor("#EF5350")));

        pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()),
                Color.parseColor("#29B6F6")));

        pieChart.startAnimation();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}