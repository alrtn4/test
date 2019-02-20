package com.example.ideapad510.sherkat.view;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.ideapad510.sherkat.R;
import com.example.ideapad510.sherkat.model.DatabaseHelper;
import com.example.ideapad510.sherkat.view.recycler_view.MyAdapter;
import com.example.ideapad510.sherkat.view.recycler_view.SimpleDividerItemDecoration;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private DatabaseHelper db;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preparingViews();

        //joinning database with recyclerview
        db = new DatabaseHelper(this);
        MyAdapter listAdapter = new MyAdapter(db.convertRowsToSimpleRows(), this);
        recycler.setAdapter(listAdapter);

        //some setting value for example
        setTopTextViews("333","22","1");
//        addRecyclerRow("hi", "bye","hello","how","it");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //the action done by floating button
    public void goToEndOfListOfRecyclerView(View view) {
        layoutManager.scrollToPosition(db.getRowsCount() -1);
    }

    //All views prepare here
    private void preparingViews(){
        setContentView(R.layout.activity_main2);
        recycler = findViewById(R.id.recycler_layout);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        SimpleDividerItemDecoration itemDecoration = new SimpleDividerItemDecoration(this);
        recycler.addItemDecoration(itemDecoration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycler.setVerticalScrollbarPosition(View.SCROLLBAR_POSITION_LEFT);
        txt1 = findViewById(R.id.textView1);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);
    }

    //setting the value of three top text boxes
    private void setTopTextViews(String s1, String s2, String s3){
        txt1.setText(s1);
        txt2.setText(s2);
        txt3.setText(s3);
    }

    private void addRecyclerRow(String txtBox1, String txtBox2, String txtBox3, String txtBox4, String txtBox5){
        db.insertRow(txtBox1, txtBox2, txtBox3, txtBox4, txtBox5);
    }
/*
    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        infoDialogFragment editNameDialogFragment = infoDialogFragment.newInstance("INFORMATION");
        editNameDialogFragment.show(fm, "fragment_info_dialog");
    }
*/

}