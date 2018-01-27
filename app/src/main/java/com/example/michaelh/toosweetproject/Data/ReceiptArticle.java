package com.example.michaelh.toosweetproject.Data;

/**
 * Created by gabri on 27.01.2018.
 */

public class ReceiptArticle {
    private double Quantity;
    private String rawArticle_label;
    private double cash;
    private  Article Article;

    public ReceiptArticle() {
        this.Article = new Article();
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

    public String getRawArticle_label() {
        return rawArticle_label;
    }

    public void setRawArticle_label(String rawArticle_label) {
        this.rawArticle_label = rawArticle_label;
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

    public void findArticleFromMigros(){

    }
}
