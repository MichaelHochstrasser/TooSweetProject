package com.example.michaelh.toosweetproject.Data;

import java.io.Serializable;

/**
 * Created by gabri on 27.01.2018.
 */

public class Article implements Serializable {
    private String Name;
    private int BarCode;
    private double Quantity; // Amount of this product, (i.e. kg for fruits, int for products)
    private double SugarPerHundert;

    public Article(String name, int barCode, double quantity, double sugarPerHundert) {
        Name = name;
        BarCode = barCode;
        Quantity = quantity;
        SugarPerHundert = sugarPerHundert;
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

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public double getSugarPerHundert() {
        return SugarPerHundert;
    }

    public void setSugarPerHundert(float sugarPerHundert) {
        SugarPerHundert = sugarPerHundert;
    }

    public double getAbsoluteSugar(){
        return Quantity * SugarPerHundert / 100;

    }
}
