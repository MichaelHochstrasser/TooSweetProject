package com.example.michaelh.toosweetproject.Data;

/**
 * Created by gabri on 27.01.2018.
 */

public class Article {
    private String Name;
    private int BarCode;
    private float Quantity; // Amount of this product, (i.e. kg for fruits, int for products)
    private float SugarPerHundert;

    public Article() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBarCode() {
        return BarCode;
    }

    public void setBarCode(int barCode) {
        BarCode = barCode;
    }

    public float getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public float getSugarPerHundert() {
        return SugarPerHundert;
    }

    public void setSugarPerHundert(float sugarPerHundert) {
        SugarPerHundert = sugarPerHundert;
    }

    public float getAbsoluteSugar(){
        return Quantity * SugarPerHundert / 100;

    }
}
