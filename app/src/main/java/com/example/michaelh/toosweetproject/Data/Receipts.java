package com.example.michaelh.toosweetproject.Data;

import com.example.michaelh.toosweetproject.CSVFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 27.01.2018.
 */

public class Receipts {
    private List<Receipt> Receipts;

    public Receipts() {
        Receipts = new ArrayList();
    }

    public List<Receipt> getReceipts() {
        return Receipts;
    }

    public void setReceipts(List<Receipt> Receipts) {
        this.Receipts = Receipts;
    }

    public Integer getReceipts_position(String TransactionNumber){
        for (int i = 0; i < Receipts.size(); i++) {
            if (Receipts.get(i).getTransactionNumber() == TransactionNumber){
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

        for (int i = 0; i < scoreList.size(); i++) {
            List<String> row = (List<String>) scoreList.get(i);
            //Log.i("CSV", row.get(0).toString());

            //Load Columns
            String strDate = row.get(0);
            String strTime = row.get(1);
            String StoreName = row.get(2);
            String TransactionNumber = row.get(4);
            String article_label = row.get(5);
            String quantity = row.get(6);
            String cash = row.get(8);

            //Create new Receipts, if not exist
            if (getReceipts_position(TransactionNumber) == -1) {
                Receipt newReceipt = new Receipt(strDate, strTime, StoreName, TransactionNumber);
                this.Receipts.add(newReceipt);
            }

            //Add Article
            Article newArticle = new Article();
            Integer pos = getReceipts_position(TransactionNumber);
            //this.Receipts.getReceiptArticles.get(pos).add()


        }
    }
}
