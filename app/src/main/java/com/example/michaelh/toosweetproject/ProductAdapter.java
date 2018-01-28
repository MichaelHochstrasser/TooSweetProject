package com.example.michaelh.toosweetproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaelh.toosweetproject.Data.ReceiptArticle;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<ReceiptArticle> implements View.OnClickListener{

    private List<ReceiptArticle> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtAlternativeName;
        TextView txtSugtotAlt;
        TextView txtAmount;
        TextView txtReduction_100g;
        ImageView imgAlternative;
    }

    public ProductAdapter(List<ReceiptArticle> data, Context context) {
        super(context, R.layout.item_product, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        /*int position=(Integer) v.getTag();
        Object object= getItem(position);
        ReceiptArticle receiptArticle=(ReceiptArticle)object;*/
    }

    //private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ReceiptArticle receiptArticle = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_product, parent, false);
            viewHolder.txtAlternativeName = (TextView) convertView.findViewById(R.id.txtProduct1);
            viewHolder.txtSugtotAlt = (TextView) convertView.findViewById(R.id.txtSugtot1);
            viewHolder.txtAmount = (TextView) convertView.findViewById(R.id.txtAmount);
            viewHolder.txtReduction_100g = (TextView) convertView.findViewById(R.id.txtReduction_100g);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;*/

        viewHolder.txtAlternativeName.setText(receiptArticle.getRawArticle_label());
        viewHolder.txtSugtotAlt.setText("");
        viewHolder.txtAmount.setText(String.format( "Value of a: %.2f", Double.toString(receiptArticle.getQuantity())));
        viewHolder.txtReduction_100g.setText(Double.toString(receiptArticle.getAbsoluteSugar()));
        // Return the completed view to render on screen
        return convertView;
    }
}