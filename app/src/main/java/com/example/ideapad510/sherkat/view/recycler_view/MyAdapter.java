package com.example.ideapad510.sherkat.view.recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ideapad510.sherkat.R;

import java.util.ArrayList;

/**
 * Created by Ideapad 510 on 1/13/2019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemHolder> {
    // List to store all the contact details
    private ArrayList<items> itemsList;
    private Context mContext;
    // Counstructor for the Class
    public MyAdapter(ArrayList<items> itemsList, Context context) {
        this.itemsList = itemsList;
        this.mContext = context;
    }
    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        android.view.View view = layoutInflater.inflate(R.layout.inner_layout, parent, false);

//        view.getLayoutParams().height = MainActivity.getScreen_Height() / 8;
//        view.getLayoutParams().height = (int) 95;

        return new ItemHolder(view);
    }
    @Override
    public int getItemCount() {
        return itemsList == null? 0: itemsList.size();
    }
    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        final items items = itemsList.get(position);

        // Set the data to the views here
        holder.setText1(items.getName1());
        holder.setText2(items.getName2());
        holder.setText3(items.getName3());
        holder.setText4(items.getName4());
        holder.setText5(items.getName5());
// You can set click listners to indvidual items in the viewholder here
        // make sure you pass down the listner or make the Data members of the viewHolder public

    }
    // This is your ViewHolder class that helps to populate data to the view
    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
        private TextView txt4;
        private TextView txt5;

        public ItemHolder(android.view.View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.text1);
            txt2 = itemView.findViewById(R.id.text2);
            txt3 = itemView.findViewById(R.id.text3);
            txt4 = itemView.findViewById(R.id.text4);
            txt5 = itemView.findViewById(R.id.text5);
        }

        public void setText1(String name1) {
            txt1.setText(name1);
        }
        public void setText2(String name2) {
            txt2.setText(name2);
        }
        public void setText3(String name3) {
            txt3.setText(name3);
        }
        public void setText4(String name4) {
            txt4.setText(name4);
        }
        public void setText5(String name5) {
            txt5.setText(name5);
        }

    }

}