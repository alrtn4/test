package com.example.ideapad510.sherkat.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ideapad510.sherkat.view.recycler_view.items;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Table.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Table.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public long insertNote(String note1, String note2, String note3, String note4, String note5) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        // insert row
        values.put(Table.COLUMN_NOTE1, note1);
        values.put(Table.COLUMN_NOTE2, note2);
        values.put(Table.COLUMN_NOTE3, note3);
        values.put(Table.COLUMN_NOTE4, note4);
        values.put(Table.COLUMN_NOTE5, note5);
        long id =db.insert(Table.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Table getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Table.TABLE_NAME,
                new String[]{ Table.COLUMN_NOTE1, Table.COLUMN_NOTE2, Table.COLUMN_NOTE3, Table.COLUMN_NOTE4, Table.COLUMN_NOTE5,
                    Table.COLUMN_ID, Table.COLUMN_TIMESTAMP},
                Table.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Table note = new Table(
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE1)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE2)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE3)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE4)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE5)),
                cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Table.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }


    public List<Table> getAllNotes() {
        List<Table> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Table.TABLE_NAME + " ORDER BY " +
                Table.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Table note = new Table();
                note.setId(cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ID)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Table.COLUMN_TIMESTAMP)));
                note.setNote1(cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE1)));
                note.setNote2(cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE2)));
                note.setNote3(cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE3)));
                note.setNote4(cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE4)));
                note.setNote5(cursor.getString(cursor.getColumnIndex(Table.COLUMN_NOTE5)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Table.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateTable(Table table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Table.COLUMN_NOTE1, table.getNote1());
        values.put(Table.COLUMN_NOTE2, table.getNote2());
        values.put(Table.COLUMN_NOTE3, table.getNote3());
        values.put(Table.COLUMN_NOTE4, table.getNote4());
        values.put(Table.COLUMN_NOTE5, table.getNote5());

        // updating row
        return db.update(Table.TABLE_NAME, values, Table.COLUMN_ID + " = ?",
                new String[]{String.valueOf(table.getId())});
    }

    public void deleteTable(Table table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table.TABLE_NAME, Table.COLUMN_ID + " = ?",
                new String[]{String.valueOf(table.getId())});
        db.close();
    }


    public ArrayList<items> convertNotesToItems(){
        ArrayList<items> items = new ArrayList<>();
        int count = getNotesCount();
        for(int i=1; i<=count; i++){
            items.add(new items(this.getNote(i).getNote1(),this.getNote(i).getNote2(),this.getNote(i).getNote3(),
                    this.getNote(i).getNote4(),this.getNote(i).getNote5()));
        }

        return items;
    }

}