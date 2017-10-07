package com.example.android.sanrakshan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.sanrakshan.R;

import java.util.ArrayList;

/**
 * Created by HP on 07-Oct-17.
 */

public class TipsAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> tips;

    public TipsAdapter (Context context, ArrayList<String> tips) {
        this.context = context;
        this.tips = tips;
    }

    @Override
    public int getCount() {
        return tips.size();
    }

    @Override
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null){
            view = li.inflate(R.layout.list_item_tips,parent,false);
        }
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(tips.get(pos));
        return view;
    }

}

