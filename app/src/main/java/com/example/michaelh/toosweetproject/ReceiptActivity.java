package com.example.michaelh.toosweetproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.michaelh.toosweetproject.Data.ReceiptArticle;
import com.example.michaelh.toosweetproject.Data.Receipts;

import java.io.InputStream;
import java.util.List;

public class ReceiptActivity extends AppCompatActivity {

    ListView listProducts;
    ArrayAdapter arrayAdapter;
    ImageButton btnRefresh;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent myIntent = new Intent(ReceiptActivity.this, MainActivity.class);
                    startActivity(myIntent);

                    return true;
                case R.id.navigation_shoppings:
                    Intent shoppingIntent = new Intent(ReceiptActivity.this, ReceiptActivity.class);
                    startActivity(shoppingIntent);
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_overview:
                    Intent overviewIntent = new Intent(ReceiptActivity.this, OverviewActivity.class);
                    startActivity(overviewIntent);
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profil:
                    Intent profilIntent = new Intent(ReceiptActivity.this, ProfilActivity.class);
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
        setContentView(R.layout.receipt);
        
        btnRefresh = (ImageButton)findViewById(R.id.imgBotRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.notifyDataSetChanged();
            }
        });

        Intent intent = this.getIntent();
        //Bundle bundle = intent.getExtras();
        //Receipts receipts = (Receipts) bundle.getSerializable("receipts");
        Bundle b = getIntent().getExtras();

        Receipts receipts = new Receipts(getApplicationContext());
        InputStream inputStream = getResources().openRawResource(R.raw.receiptsnew);
        receipts.loadCSV(inputStream);

        Integer item = b.getInt("item");

        listProducts = (ListView) findViewById(R.id.listProducts);

        if (item>-1) {
            List<ReceiptArticle> receiptArticle = receipts.getReceipts().get(item).getReceiptArticles();
            arrayAdapter = new ProductAdapter(receiptArticle,getApplicationContext());
            listProducts.setAdapter(arrayAdapter);
        } else {
            List<ReceiptArticle> receiptArticle = receipts.getAllReceiptArticle();
            arrayAdapter = new ProductAdapter(receiptArticle,getApplicationContext());
            listProducts.setAdapter(arrayAdapter);
        }

        Parcelable state = listProducts.onSaveInstanceState();
        listProducts.setAdapter(arrayAdapter);
        listProducts.onRestoreInstanceState(state);

        //Load first Article
        //Articles
        new modifyCar(receipts).execute();
        //receipts.getReceipts().get(0).getReceiptArticles().get(0).findArticleFromFoodrepo();

        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                listProducts.deferNotifyDataSetChanged();
            }

        },0,3000);//Update text every second*/

    }

    class modifyCar extends AsyncTask<Void, Integer, Receipts> {
        private Receipts receipts;

        // a constructor so that you can pass the object and use
        modifyCar(Receipts receipts){
            this.receipts = receipts;
        }

        protected void onPreExecute()
        {
        }

        protected Receipts doInBackground(Void... parms)
        {
            //The rest of the code using newCarAsync
            for (int i = 0; i < receipts.getReceipts().size(); i++) {
                for (int j = 0; j < receipts.getReceipts().get(i).getReceiptArticles().size(); j++) {
                    receipts.getReceipts().get(i).getReceiptArticles().get(j).findArticleFromFoodrepo();
                }
            }
            return receipts;
        }

        protected void onProgressUpdate()
        {
        }

        protected void onPostExecute()
        {
        }
    }
}