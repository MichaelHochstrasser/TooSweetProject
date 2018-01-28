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
import android.widget.Button;
import android.widget.ListView;

import com.example.michaelh.toosweetproject.Data.Receipts;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShoppingsActivity extends AppCompatActivity {

    ListView listReceipts;
    ArrayAdapter arrayAdapter;
    Button btnAll;
    private String testVariable = "HalliHallo";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent myIntent = new Intent(ShoppingsActivity.this, MainActivity.class);
                    startActivity(myIntent);

                    return true;
                case R.id.navigation_shoppings:
                    Intent shoppingIntent = new Intent(ShoppingsActivity.this, ShoppingsActivity.class);
                    startActivity(shoppingIntent);
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_overview:
                    Intent overviewIntent = new Intent(ShoppingsActivity.this, OverviewActivity.class);
                    startActivity(overviewIntent);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profil:
                    Intent profilIntent = new Intent(ShoppingsActivity.this, ProfilActivity.class);
                    startActivity(profilIntent);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einkauf);


        //Load UI
        listReceipts = (ListView) findViewById(R.id.listReceipts);
        btnAll = (Button)findViewById(R.id.btnAllShoppings);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ShoppingsActivity.this, ReceiptActivity.class);
                myIntent.putExtra("item", -1);
                startActivity(myIntent);
            }
        });

        //Load CSV
        Receipts receipts = new Receipts(getApplicationContext());
        InputStream inputStream = getResources().openRawResource(R.raw.receiptsnew);
        receipts.loadCSV(inputStream);

        //Sort by Date
        receipts = receipts.sortReceipts_byDate(receipts);
        // Load chart data
        LineChart chart = (LineChart) findViewById(R.id.chart2);
        loadChart(chart,receipts);

        // Adds Menu listener
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_shoppings);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //navigation.setSelectedItemId(R.id.navigation_shoppings);
        //Show
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,receipts.toArray());
        Parcelable state = listReceipts.onSaveInstanceState();
        listReceipts.setAdapter(arrayAdapter);
        listReceipts.onRestoreInstanceState(state);
        listReceipts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),receipts.getReceipts().get(position).getDate().toString(),Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(ShoppingsActivity.this, ReceiptActivity.class);
                myIntent.putExtra("item",position);
                startActivity(myIntent);

            }
        });
    }

    private void loadChart(LineChart chart, Receipts receipts){
        List<Entry> entries = new ArrayList<Entry>();

        //List<Integer> yValues = new ArrayList<Integer>();
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


}
