//5 Juni 2021, 10118410, Ridwan Caesarahman Julian, IF-10
package com.tugas10118410.uts_10118410_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AksesDatabase {
    private SQLiteDatabase database;
    private DatabaseOpenHelper openHelper;
    private static volatile AksesDatabase instance;

    private AksesDatabase(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static synchronized AksesDatabase getInstance(Context context){
        if (instance == null){
            instance = new AksesDatabase(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if (database != null){
            this.database.close();
        }
    }

    public void save(Memo memo){
        ContentValues values = new ContentValues();
        values.put("date", memo.getTime());
        values.put("kategori", memo.getKategori());
        values.put("judul", memo.getJudul());
        values.put("isi", memo.getIsi());
        database.insert(DatabaseOpenHelper.TABLE,null,values);

    }

    public void update(Memo memo){
        ContentValues values = new ContentValues();
        values.put("date", memo.getTime());
        values.put("kategori", memo.getKategori());
        values.put("judul", memo.getJudul());
        values.put("isi", memo.getIsi());
        String date = Long.toString(memo.getTime());
        database.update(DatabaseOpenHelper.TABLE,values,"date = ?", new String[]{date});

    }

    public void delete(Memo memo){
        String date = Long.toString(memo.getTime());
        database.delete(DatabaseOpenHelper.TABLE, "date = ?", new String[]{date});
    }

    public List getAllMemos(){
        List memos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * From memo ORDER BY date DESC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            long time = cursor.getLong(0);
            String kategori = cursor.getString(1);
            String judul = cursor.getString(2);
            String isi = cursor.getString(3);
            memos.add(new Memo(time, kategori, judul, isi));
            cursor.moveToNext();
        }

        cursor.close();
        return memos;
    }
}
