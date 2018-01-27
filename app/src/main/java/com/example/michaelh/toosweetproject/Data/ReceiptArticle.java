package com.example.michaelh.toosweetproject.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;

/**
 * Created by gabri on 27.01.2018.
 */

public class ReceiptArticle implements Serializable {
    private double Quantity;
    private String rawArticle_label;
    private double cash;
    private  Article article;
    Context context;


    public ReceiptArticle(Context context) {
        this.article = null;
        this.context = context;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public com.example.michaelh.toosweetproject.Data.Article getArticle() {
        return article;
    }

    public void setArticle(com.example.michaelh.toosweetproject.Data.Article article) {
        article = article;
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
        if (article==null){
            return 0.0;
        }
        return Quantity * article.getAbsoluteSugar();
    }

    private double parseSugar(String response){
        int sugar_index = response.indexOf("davon Zucker");
        String staticElement = "davon Zucker\n" +
                "                    </td>\n" +
                "                                    <td>\n" +
                "                        ";


        sugar_index = sugar_index + staticElement.length();
        String sugarSubstring = response.substring(sugar_index);

        int gramm_index = sugarSubstring.indexOf('g');
        String sugarString = sugarSubstring.substring(0, gramm_index);
        Log.i("Volley","Sugar index  is: "+ sugar_index);
        Log.i("Volley","Sugar index  is: "+ response.indexOf("davon Zucker"));
        Log.i("Volley","Sugar: "+ sugarString);
        double sugar = Double.parseDouble(sugarString);
        return sugar;
    }
    public void findArticleFromFoodrepo(){

        String search = this.getRawArticle_label().toLowerCase().replace(" ","-");
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url ="https://produkte.migros.ch/sortiment?q=" + search;
        String url = "https://produkte.migros.ch/m-budget-tomaten";
        Log.i("Volley","URL: " + url);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("Volley","Response is: "+ response);
                        // TODO: Switch to dom-parser

                        //Html html = Html.fromHtml(response);
                        Integer indx = response.indexOf("href");
                        if (indx==-1){
                            //Product not found
                            Log.i("Volley","Product not found");
                        } else {
                            //response = response.substring(indx);
                            //response = response.substring(response.indexOf("href"));
                            //Log.i("Volley","Response is: "+ response);
                            Double sugar = parseSugar(response);
                            int barcode = -1;
                            Article article = new Article(getRawArticle_label(),barcode,getQuantity(), sugar); //Fix per 100
                            setArticle(article);
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
