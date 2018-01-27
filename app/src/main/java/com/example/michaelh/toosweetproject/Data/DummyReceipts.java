package com.example.michaelh.toosweetproject.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by gabri on 27.01.2018.
 */

public class DummyReceipts  extends Receipts{

    public DummyReceipts() {

    }

    private Calendar mStringToCalender(String dateString){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        //cal.setTime(sdf.parse("Mon Mar 14 16:02:37 GMT 2011"));// all done

        return cal;
    }
    private void loadDummyData(){

        //    Calendar mDate =
        //       Calendar mTime =
        //String mStoreName =
        //String mTransactionNumber =
        //List<ReceiptArticle> mReceiptArticles =

        //Receipt receipt_1 = new Receipt()
        //List<Receipt> receipts =
        //setmReceipts();


    }
}
