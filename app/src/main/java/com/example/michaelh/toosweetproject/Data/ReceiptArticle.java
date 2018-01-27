package com.example.michaelh.toosweetproject.Data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by gabri on 27.01.2018.
 */

public class ReceiptArticle {
    private double Quantity;
    private String rawArticle_label;
    private double cash;
    private  Article Article;
    Context context;


    public ReceiptArticle(Context context) {
        this.Article = new Article();
        this.context = context;
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

    public void findArticleFromFoodrepo(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
