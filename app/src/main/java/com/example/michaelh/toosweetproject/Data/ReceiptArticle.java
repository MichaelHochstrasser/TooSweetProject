package com.example.michaelh.toosweetproject.Data;

import android.content.Context;
import android.util.Log;

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

        String search = this.getRawArticle_label().toLowerCase().replace(" ","+");
        search = "Tomate+arom";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://produkte.migros.ch/sortiment?q=" + search;
        Log.i("Volley","URL: " + url);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("Volley","Response is: "+ response);
                        //Html html = Html.fromHtml(response);
                        Integer indx = response.indexOf("href");
                        if (indx==-1){
                            //Product not found
                            Log.i("Volley","Product not found");
                        } else {
                            response = response.substring(indx);
                            response = response.substring(response.indexOf("href"));
                            Log.i("Volley","Response is: "+ response);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("Volley","That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
