package com.recipeapp.recipeapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by amadzahid on 01/12/2016.
 */
public class RecipeListAdapter extends BaseAdapter {
    List<RecipeModal> dataList;
    Context mContext;

    public RecipeListAdapter(Context pContext, List<RecipeModal> pList){
        mContext = pContext;
        dataList = pList;

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            convertView = vi.inflate(R.layout.recipelist, null);
        }
        ((TextView)convertView.findViewById(R.id.recipeTextSummary)).setText(dataList.get(position).get_name());
        ImageView img = (ImageView)convertView.findViewById(R.id.recipeImageView);

        try
        {
            // get input stream
            InputStream ims = ((Activity)(mContext)).getAssets().open(dataList.get(position).get_name() + ".jpeg");//modal.set]_name( getIntent().getStringExtra("name")+".jpeg"));
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            img.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
        }

        return convertView;
    }
}
