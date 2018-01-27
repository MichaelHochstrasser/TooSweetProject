package com.example.michaelh.toosweetproject.Data;

import android.content.Context;

import com.example.michaelh.toosweetproject.CSVFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 27.01.2018.
 */

public class Receipts {
    private List<Receipt> Receipts;
    private Context context;
    public Receipts(Context context) {
        Receipts = new ArrayList();
        this.context = context;
    }

    public List<Receipt> getReceipts() {
        return Receipts;
    }

    public void setReceipts(List<Receipt> Receipts) {
        this.Receipts = Receipts;
    }

    public Integer getReceipts_position(String TransactionNumber){
        for (int i = 0; i < Receipts.size(); i++) {
            if (Receipts.get(i).getTransactionNumber().equals(TransactionNumber)){
                return i;
            }
        }
        return -1;
    }

    public void loadCSV(InputStream inputStream) {
        // LOAD CSV
        List<String> names = new ArrayList();
        //InputStream inputStream = getResources().openRawResource(R.raw.receipts);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.read();

        for (int i = 1; i < scoreList.size(); i++) {
            List<String> row = (List<String>) scoreList.get(i);
            //Log.i("CSV", row.get(0).toString());

            //Load Columns
            String strDate = row.get(0);
            String strTime = row.get(1);
            String StoreName = row.get(2);
            String TransactionNumber = row.get(4);
            String article_label = row.get(5);
            Double quantity = Double.parseDouble(row.get(6));
            Double cash = Double.parseDouble(row.get(8));

            //Create new Receipts, if not exist
            if (getReceipts_position(TransactionNumber) == -1) {
                Receipt newReceipt = new Receipt(strDate, strTime, StoreName, TransactionNumber);
                this.Receipts.add(newReceipt);
            }

            //Create new ReceiptArticle
            ReceiptArticle receipt_article = new ReceiptArticle(context);
            receipt_article.setRawArticle_label(article_label);
            receipt_article.setQuantity(quantity);
            receipt_article.setCash(cash);

            //receipt_article.findArticleFromFoodrepo();

            //Add ReceiptArticle
            Integer pos = getReceipts_position(TransactionNumber);
            this.Receipts.get(pos).getReceiptArticles().add(receipt_article);
            //this.Receipts.getReceiptArticles.get(pos).add()

        }
    }

    public List<String> toArray(){
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < Receipts.size(); i++) {
            arr.add("Einkauf vom " + Receipts.get(i).getDate() + " in " + Receipts.get(i).getStoreName());
        }
        return arr;
    }
}
