package com.example.michaelh.toosweetproject.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 27.01.2018.
 */

public class Receipt {
    // TODO: Should we change to private and use get / set instaed
    private String Date;
    private String Time;
    private String StoreName;
    private String TransactionNumber;
    List<ReceiptArticle> ReceiptArticles = new ArrayList();

    public Receipt(String date, String time, String storeName, String transactionNumber) {
        Date = date;
        Time = time;
        StoreName = storeName;
        TransactionNumber = transactionNumber;
        this.ReceiptArticles = new ArrayList();
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getTransactionNumber() {
        return TransactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        TransactionNumber = transactionNumber;
    }

    public List<ReceiptArticle> getReceiptArticles() {
        return ReceiptArticles;
    }

    public void setReceiptArticles(List<ReceiptArticle> receiptArticles) {
        ReceiptArticles = receiptArticles;
    }

    public float getAbsoluteSugarofReceipt(){
        float absoluteTotalSugar = 0;

        for (int i=0; i<ReceiptArticles.size(); i++) {
            absoluteTotalSugar += ReceiptArticles.get(i).getAbsoluteSugar();
        }
        return absoluteTotalSugar;



    }
}
