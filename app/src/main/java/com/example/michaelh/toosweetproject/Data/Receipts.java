package com.example.michaelh.toosweetproject.Data;

import android.content.Context;

import com.example.michaelh.toosweetproject.CSVFile;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by gabri on 27.01.2018.
 */

public class Receipts implements Serializable {
    private List<Receipt> Receipts;
    private Receipt receiptAll;
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
    public int getNumberOfReceipts(){
        return Receipts.size();
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
            Double cash = 0.0; // Double.parseDouble(row.get(8));

            //Using data directly from csv
            //TODO: Remove once loaded from migros
            Double sugar_per_hunder = Double.parseDouble(row.get(9));
            Double quanity_per_product = Double.parseDouble(row.get(10));

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

            //Loading article data to article  TODO: Remove once loaded from migros
            Article Article1 = new Article(article_label, -1, quanity_per_product,sugar_per_hunder);
            receipt_article.setArticle(Article1);

            //Add ReceiptArticle
            Integer pos = getReceipts_position(TransactionNumber);
            this.Receipts.get(pos).addReceiptArticle(receipt_article);
            //this.Receipts.getReceiptArticles.get(pos).add()

        }

        loadReceiptAll();
    }

    public List<String> toArray(){
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < Receipts.size(); i++) {
            arr.add("Einkauf vom " + Receipts.get(i).getDate() + " in " + Receipts.get(i).getStoreName());
        }
        return arr;
    }

    public Receipt getReceiptAll() {
        return receiptAll;
    }

    public void setReceiptAll(Receipt receiptAll) {
        this.receiptAll = receiptAll;
    }

    public void loadReceiptAll() {
        receiptAll = new Receipt("", "-1", "Total", "-1");

        for (int i = 0; i < Receipts.size(); i++) {
            for (int j = 0; j < Receipts.get(i).getReceiptArticles().size(); j++) {
                receiptAll.addReceiptArticle(Receipts.get(i).getReceiptArticles().get(j));
            }
        }

        this.receiptAll.calcTotalAmountSugar();
    }

    public Receipts sortReceipts_byDate(Receipts receipts){
        Collections.sort(this.getReceipts(), new Comparator<Receipt>(){
            public int compare(Receipt obj1, Receipt obj2)
            {
                // TODO Auto-generated method stub
                if (obj1.getDay().after(obj2.getDay())) {
                    return 1;
                }
                else                {
                    return -1; //TODO implement equal
                }}
        });

        return receipts;
    }


    // List<ReceiptArticle> receiptArticles = new ArrayList<>();
        /*Double totalSugar = 0.0;
        Double max = 0.0;
        for (int i = 0; i < Receipts.size(); i++) {
            for (int j = 0; j < Receipts.get(i).getReceiptArticles().size(); j++) {
                receiptAll.addReceiptArticle(Receipts.get(i).getReceiptArticles().get(j));
                totalSugar += Receipts.get(i).getReceiptArticles().get(j).getAbsoluteSugar();
                if (Receipts.get(i).getReceiptArticles().get(j).getAbsoluteSugar()>max){
                    max = Receipts.get(i).getReceiptArticles().get(j).getAbsoluteSugar();
                }
            }
        }

        for (int i = 0; i < Receipts.size(); i++) {
            for (int j = 0; j < Receipts.get(i).getReceiptArticles().size(); j++) {
                Receipts.get(i).getReceiptArticles().get(j).setTotalSugarOfReceipt(totalSugar);
                Receipts.get(i).getReceiptArticles().get(j).setMaxSugarOfReceipt(max);
            }
        }
        return receiptArticles;*/
}
