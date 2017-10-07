package com.example.android.sanrakshan.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sanrakshan.Models.Police;
import com.example.android.sanrakshan.R;

import java.util.ArrayList;

/**
 * Created by HP on 07-Oct-17.
 */

public class PoliceAdapter extends RecyclerView.Adapter<PoliceAdapter.ListItemHolder> {

    Context context;
    ArrayList<Police> policeArrayList;

    public PoliceAdapter(Context context, ArrayList<Police> policeArrayList) {
        this.context = context;
        this.policeArrayList = policeArrayList;
    }

    @Override
    public ListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.list_item_police,parent,false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemHolder holder, int position) {
        Police item = policeArrayList.get(position);
        holder.pname.setText(item.getName());
        holder.address.setText(item.getAddress());
        holder.distance.setText(item.getDistance());
    }

    @Override
    public int getItemCount() {
        return policeArrayList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{
        View mainView;
        TextView pname;
        TextView address;
        TextView distance;
        public ListItemHolder(View itemView) {
            super(itemView);
            mainView = itemView;
            pname = (TextView) itemView.findViewById(R.id.stationName);
            address = (TextView) itemView.findViewById(R.id.address);
            distance = (TextView) itemView.findViewById(R.id.distance);
        }
    }
}
