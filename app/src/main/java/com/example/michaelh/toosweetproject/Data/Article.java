package com.example.michaelh.toosweetproject.Data;

/**
 * Created by gabri on 27.01.2018.
 */

public class Article {
    private String mName;
    private int mBarCode;
    private float mQuantity; // Amount of this product, (i.e. kg for fruits, int for products)
    private float mSugarPerHundert;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmBarCode() {
        return mBarCode;
    }

    public void setmBarCode(int mBarCode) {
        this.mBarCode = mBarCode;
    }

    public float getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public float getMsugar_per_hundert() {
        return mSugarPerHundert;
    }

    public void setMsugar_per_hundert(int msugar_per_hundert) {
        this.mSugarPerHundert = msugar_per_hundert;
    }

    public float getAbsoluteSugar(){
        return mQuantity * mSugarPerHundert / 100;

    }
}
