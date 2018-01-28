package com.example.michaelh.toosweetproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.michaelh.toosweetproject.Data.ReceiptArticle;
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

    private void loadChart(LineChart chart, Receipts receipts){
        List<Entry> entries = new ArrayList<Entry>();

        //List<Integer> yValues = new ArrayList<Integer>();
        receipts.sortReceipts_byDate();
        /*for (int i = 0; i < receipts.getReceipts().size(); i++) {
            yValues.add((int) Math.round(receipts.getReceipts().get(i).calcTotalAmountSugar()));
        }*/

        for (int i = 0; i < receipts.getReceipts().size(); i++) {
            // turn your data into Entry objects
            String x_axis_string = new String("Week 1 ");
            entries.add(new Entry(i, (int) Math.round(receipts.getReceipts().get(i).calcTotalAmountSugar())));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Sugar consumption over time"); // add entries to dataset
        //dataSet.setColor(...);
        //dataSet.setValueTextColor(...); // styling, ...


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }


    private void loadDummyTopList(ListView listProducts){
        List<ReceiptArticle> receiptArticles = new ArrayList<>();

        arrayAdapter = new ProductAdapter(receiptArticles,getApplicationContext());
        listProducts.setAdapter(arrayAdapter);

        Parcelable state = listProducts.onSaveInstanceState();
        listProducts.setAdapter(arrayAdapter);
        listProducts.onRestoreInstanceState(state);

    }

    private void loadTopSugarListView(Receipts receipts, ListView listProducts){
            List<ReceiptArticle> receiptArticle = receipts.getReceiptAll().getTopSugarProducts(5);
            arrayAdapter = new ProductAdapter(receiptArticle,getApplicationContext());
            listProducts.setAdapter(arrayAdapter);

        Parcelable state = listProducts.onSaveInstanceState();
        listProducts.setAdapter(arrayAdapter);
        listProducts.onRestoreInstanceState(state);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_sugar);

        // Adds Menu listener
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_overview);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ListView listProducts = (ListView) findViewById(R.id.listMostProduct);

        //Load CSV
        final Receipts receipts = new Receipts(getApplicationContext());
        InputStream inputStream = getResources().openRawResource(R.raw.receiptsnew);
        receipts.loadCSV(inputStream);

        // Load chart data
        LineChart chart = (LineChart) findViewById(R.id.chart);
        loadChart(chart,receipts);

        loadTopSugarListView(receipts, listProducts);


    }
}
