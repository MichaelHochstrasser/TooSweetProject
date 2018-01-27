package com.example.michaelh.toosweetproject;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einkauf);

        //Load UI
        listReceipts = (ListView) findViewById(R.id.listReceipts);

        //Load CSV
        final Receipts receipts = new Receipts(getApplicationContext());
        InputStream inputStream = getResources().openRawResource(R.raw.receipts);
        receipts.loadCSV(inputStream);

        //Load first Article
        receipts.getReceipts().get(0).getReceiptArticles().get(0).findArticleFromFoodrepo();

        //Show
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,receipts.toArray());
        Parcelable state = listReceipts.onSaveInstanceState();
        listReceipts.setAdapter(arrayAdapter);
        listReceipts.onRestoreInstanceState(state);
        listReceipts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),receipts.getReceipts().get(position).getDate().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
