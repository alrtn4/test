package com.example.ideapad510.sherkat.view.recycler_view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideapad510.sherkat.R;
import com.example.ideapad510.sherkat.model.SimpleRow;
import com.example.ideapad510.sherkat.view.MainActivity;
import com.example.ideapad510.sherkat.view.infoDialogFragment;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.rowHolder> {
    private ArrayList<SimpleRow> itemsList;
    private Context context;

    public MyAdapter(ArrayList<SimpleRow> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;

    }

    @Override
    public rowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        android.view.View view = layoutInflater.inflate(R.layout.inside_recycler_row, parent, false);

        return new rowHolder(view);
    }
    @Override
    public int getItemCount() {
        return itemsList == null? 0: itemsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull rowHolder holder, final int position) {
        final SimpleRow items = itemsList.get(position);

        holder.setText1(items.getName1());
        holder.setText2(items.getName2());
//        holder.setText3(items.getName3());
        holder.setText4(items.getName4());
        holder.setText5(items.getName5());

    }


    public class rowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
        private TextView txt4;
        private TextView txt5;

        public rowHolder(android.view.View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.text1);
            txt2 = itemView.findViewById(R.id.text2);
            txt3 = itemView.findViewById(R.id.text3);
            txt4 = itemView.findViewById(R.id.text4);
            txt5 = itemView.findViewById(R.id.text5);
            txt3.setOnClickListener(this);
        }


        public void onClick(View view){
            if (view.getId() == txt3.getId()){
//                Toast.makeText(view.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();

                infoDialogFragment newFragment = infoDialogFragment.newInstance("INFORMATION");
                newFragment.show(((MainActivity) context).getSupportFragmentManager(), "Title");

                //                MainActivity.startDialoge();
            }
//            else {
//                Toast.makeText(view.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//            }
        }

        public void setText1(String name1) { txt1.setText(name1); }
        public void setText2(String name2) {
            txt2.setText(name2);
        }
//        public void setText3(String name3) {
//            txt3.setText(name3);
//        }
        public void setText4(String name4) {
            txt4.setText(name4);
        }
        public void setText5(String name5) {
            txt5.setText(name5);
        }


/*
        private void startDialoge(View view){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
        }
*/

    }

    private void startDialog(){

    }

}