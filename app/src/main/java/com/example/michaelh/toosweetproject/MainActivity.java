package com.example.michaelh.toosweetproject;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.michaelh.toosweetproject.Data.Receipts;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ListView listReceipts;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einkauf);

        //Load UI
        listReceipts = (ListView) findViewById(R.id.listReceipts);

        //Load CSV
        Receipts receipts = new Receipts();
        InputStream inputStream = getResources().openRawResource(R.raw.receipts);
        receipts.loadCSV(inputStream);

        //Show
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,receipts.toArray());
        Parcelable state = listReceipts.onSaveInstanceState();
        listReceipts.setAdapter(arrayAdapter);
        listReceipts.onRestoreInstanceState(state);
    }
}
