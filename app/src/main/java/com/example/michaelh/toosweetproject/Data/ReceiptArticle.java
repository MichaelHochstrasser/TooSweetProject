package com.example.michaelh.toosweetproject.Data;

/**
 * Created by gabri on 27.01.2018.
 */

public class ReceiptArticle {
    private String RawName;
    private double Quantity;
    private String article_label;
    private double cash;
    private  Article Article;

    public ReceiptArticle() {
        this.Article = new Article();
    }

    public String getRawName() {
        return RawName;
    }

    public void setRawName(String rawName) {
        RawName = rawName;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public com.example.michaelh.toosweetproject.Data.Article getArticle() {
        return Article;
    }

    public void setArticle(com.example.michaelh.toosweetproject.Data.Article article) {
        Article = article;
    }

    public String getArticle_label() {
        return article_label;
    }

    public void setArticle_label(String article_label) {
        this.article_label = article_label;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getAbsoluteSugar(){
        return Quantity * Article.getAbsoluteSugar();
    }
}
