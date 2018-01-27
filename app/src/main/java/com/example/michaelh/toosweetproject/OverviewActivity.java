package com.example.michaelh.toosweetproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.michaelh.toosweetproject.Data.Receipts;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {

    ListView listReceipts;
    ArrayAdapter arrayAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent myIntent = new Intent(OverviewActivity.this, MainActivity.class);
                    startActivity(myIntent);

                    return true;
                case R.id.navigation_shoppings:
                    Intent shoppingIntent = new Intent(OverviewActivity.this, ShoppingsActivity.class);
                    startActivity(shoppingIntent);
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_overview:
                    Intent overviewIntent = new Intent(OverviewActivity.this, OverviewActivity.class);
                    startActivity(overviewIntent);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profil:
                    Intent profilIntent = new Intent(OverviewActivity.this, ProfilActivity.class);
                    startActivity(profilIntent);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private void loadChart(LineChart chart){
        List<Entry> entries = new ArrayList<Entry>();

        for(int i=1000; i<=1005; i++){
            // turn your data into Entry objects
            String x_axis_string = new String("Week 1 ");
            entries.add(new Entry(i, i));
        }

        LineDataSet dataSet = new LineDataSet(entries, "weekly sugar consumption [g]"); // add entries to dataset
        //dataSet.setColor(...);
        //dataSet.setValueTextColor(...); // styling, ...


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_sugar);

        // Adds Menu listener
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Load chart data
        LineChart chart = (LineChart) findViewById(R.id.chart);
        loadChart(chart);
    }
}
