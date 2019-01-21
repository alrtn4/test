package com.example.ideapad510.sherkat.model;

public class Table {
    public static final String TABLE_NAME = "notes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_NOTE1 = "note1";
    public static final String COLUMN_NOTE2 = "note2";
    public static final String COLUMN_NOTE3 = "note3";
    public static final String COLUMN_NOTE4 = "note4";
    public static final String COLUMN_NOTE5 = "note5";

    private int id;
    private String timestamp;
    private String note1;
    private String note2;
    private String note3;
    private String note4;
    private String note5;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ( "
                    + COLUMN_NOTE1 + " TEXT,"
                    + COLUMN_NOTE2 + " TEXT,"
                    + COLUMN_NOTE3 + " TEXT,"
                    + COLUMN_NOTE4 + " TEXT,"
                    + COLUMN_NOTE5 + " TEXT,"
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP "
                    + ")";

    public Table() {
    }

    public Table(String note1, String note2, String note3, String note4, String note5, int id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
        this.note4 = note4;
        this.note5 = note5;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public void setNote2(String note2) {this.note2 = note2;}

    public String getNote2() { return note2;}

    public void setNote3(String note3) {this.note3 = note3;}

    public String getNote3() { return note3;}

    public void setNote4(String note4) {this.note4 = note4;}

    public String getNote4() {return note4;}

    public void setNote5(String note5) {
        this.note5 = note5;
    }

    public String getNote5() {return note5;}


}