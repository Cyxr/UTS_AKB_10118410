//5 Juni 2021, 10118410, Ridwan Caesarahman Julian, IF-10
package com.tugas10118410.uts_10118410_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "memos.db";
    public static final String TABLE = "memo";
    public static final int VERSION = 1;

    public DatabaseOpenHelper( Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE memo(date INTEGER PRIMARY KEY, kategori TEXT, judul TEXT, isi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}