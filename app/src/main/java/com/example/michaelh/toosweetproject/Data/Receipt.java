package com.example.michaelh.toosweetproject.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by gabri on 27.01.2018.
 */

public class Receipt implements Serializable {
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
    public int getNumberOfReceiptArticles(){
        return this.ReceiptArticles.size();
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


    public void addReceiptArticle(ReceiptArticle receiptArticle){
        for (int i = 0; i < ReceiptArticles.size(); i++) {
            if (ReceiptArticles.get(i).getRawArticle_label().equals(receiptArticle.getRawArticle_label())){
                //Already exist
                Double quantity = ReceiptArticles.get(i).getQuantity();
                Double cash = ReceiptArticles.get(i).getCash();
                ReceiptArticles.get(i).setQuantity(quantity + receiptArticle.getQuantity());
                ReceiptArticles.get(i).setCash(cash + receiptArticle.getCash());
                return;
            }
        }
        this.ReceiptArticles.add(receiptArticle);
    }

    public List<String> toArray(){
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < ReceiptArticles.size(); i++) {
            arr.add(ReceiptArticles.get(i).getRawArticle_label());
        }
        return arr;
    }
    public List<ReceiptArticle> sortReceiptArticles(List<ReceiptArticle> receiptArticles){
        Collections.sort(receiptArticles, new Comparator<ReceiptArticle>(){
            public int compare(ReceiptArticle obj1, ReceiptArticle obj2)
            {
                // TODO Auto-generated method stub
                if (obj1.getAbsoluteSugar() < obj2.getAbsoluteSugar()) {
                    return 1;
                }
                else                {
                    return -1; //TODO implement equal
                }}
        });

        return receiptArticles;
    }
    public Double calcTotalAmountSugar(){
        Double total = 0.0;
        Double max = 0.0;
        for (int i = 0; i < ReceiptArticles.size(); i++) {
            total += ReceiptArticles.get(i).getAbsoluteSugar();
            if (ReceiptArticles.get(i).getAbsoluteSugar()>max){
                max = ReceiptArticles.get(i).getAbsoluteSugar();
            }
        }
        for (int i = 0; i < ReceiptArticles.size(); i++) {
            ReceiptArticles.get(i).setTotalSugarOfReceipt(total);
            ReceiptArticles.get(i).setMaxSugarOfReceipt(max);
        }
        return total;
    }
}
