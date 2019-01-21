package com.example.ideapad510.sherkat.view;

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
    private MyAdapter listAdapter;
    private RecyclerView recycler;
    LinearLayoutManager layoutManager;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preparingViews();
        TextView txt1 = findViewById(R.id.textView1);
        TextView txt2 = findViewById(R.id.textView2);
        TextView txt3 = findViewById(R.id.textView3);

        //joinning database with recyclerview
        db = new DatabaseHelper(this);
        listAdapter = new MyAdapter(db.convertRowsToSimpleRows(), this);
        recycler.setAdapter(listAdapter);

        //some setting value for example
        txt1.setText("111");
        txt2.setText("22");
        txt3.setText("3");
//        db.insertRow("hi", "bye","hello","how","it");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void goToEndOfListOfRecyclerView(View view) {
        layoutManager.scrollToPosition(db.getRowsCount() - 1);
    }


    public void preparingViews(){
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler_layout);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        SimpleDividerItemDecoration itemDecoration = new SimpleDividerItemDecoration(this);
        recycler.addItemDecoration(itemDecoration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycler.setVerticalScrollbarPosition(View.SCROLLBAR_POSITION_LEFT);
    }

}