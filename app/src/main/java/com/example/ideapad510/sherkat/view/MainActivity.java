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
import com.example.ideapad510.sherkat.model.Table;
import com.example.ideapad510.sherkat.view.recycler_view.MyAdapter;
import com.example.ideapad510.sherkat.view.recycler_view.SimpleDividerItemDecoration;
import com.example.ideapad510.sherkat.view.recycler_view.items;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyAdapter listAdapter;
    public static ArrayList<items> ItemsList = new ArrayList<>();
    private RecyclerView recycler;
    LinearLayoutManager layoutManager;
    private static int screen_Height;
    static TextView txt1;
    static TextView txt2;
    static TextView txt3;
    DatabaseHelper db;
    ArrayList<Table> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler_layout);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(new SimpleDividerItemDecoration(this));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt1 = findViewById(R.id.textView1);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);


        db = new DatabaseHelper(this);
//        db.insertNote("hi", "bye","hello","how","it");
        listAdapter= new MyAdapter(db.convertNotesToItems(),this);
        recycler.setAdapter(listAdapter);


        txt1.setText("111");
        txt2.setText("22");
        txt3.setText("3");

        recycler.setVerticalScrollbarPosition(View.SCROLLBAR_POSITION_LEFT);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void goToEndOfList(View view){
        layoutManager.scrollToPosition(db.getNotesCount() -1);
    }


    private void createNote(String note, String note2, String note3, String note4, String note5) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertNote(note,note2,note3,note4,note5);

        // get the newly inserted note from db
        Table n = db.getNote(id);

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);

            // refreshing the list
            listAdapter.notifyDataSetChanged();

//            toggleEmptyNotes();
        }
    }

/*
    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getNotesCount() > 0) {
            noNotesView.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
        }
    }
*/
    private void updateNote(String note1,String note2,String note3,String note4,String note5 , int position) {
        Table n = notesList.get(position);
        // updating note text
        n.setNote1(note1);
        n.setNote2(note2);
        n.setNote3(note3);
        n.setNote4(note4);
        n.setNote5(note5);

        // updating note in db
        db.updateTable(n);

        // refreshing the list
        notesList.set(position, n);
        listAdapter.notifyItemChanged(position);

//        toggleEmptyNotes();
    }

    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteTable(notesList.get(position));

        // removing the note from the list
        notesList.remove(position);
        listAdapter.notifyItemRemoved(position);

//        toggleEmptyNotes();
    }



}