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

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ListView listReceipts;
    ArrayAdapter arrayAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(myIntent);

                    return true;
                case R.id.navigation_shoppings:
                    Intent shoppingIntent = new Intent(MainActivity.this, ShoppingsActivity.class);
                    startActivity(shoppingIntent);
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_overview:
                    Intent overviewIntent = new Intent(MainActivity.this, OverviewActivity.class);
                    startActivity(overviewIntent);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profil:
                    Intent profilIntent = new Intent(MainActivity.this, ProfilActivity.class);
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
        setContentView(R.layout.home);

        //Load UI
        //listReceipts = (ListView) findViewById(R.id.listReceipts);

        //Load CSV
        //final Receipts receipts = new Receipts(getApplicationContext());
        //InputStream inputStream = getResources().openRawResource(R.raw.receiptsnew);
        //receipts.loadCSV(inputStream);

        // Adds Menu listener
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Load first Article
        //receipts.getReceipts().get(0).getReceiptArticles().get(0).findArticleFromFoodrepo();

        //Show
        //arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,receipts.toArray());
        //Parcelable state = listReceipts.onSaveInstanceState();
        //listReceipts.setAdapter(arrayAdapter);
        //listReceipts.onRestoreInstanceState(state);
        // listReceipts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //      Toast.makeText(getApplicationContext(),receipts.getReceipts().get(position).getDate().toString(),Toast.LENGTH_SHORT).show();
        //  }
        //    });
    }
}
