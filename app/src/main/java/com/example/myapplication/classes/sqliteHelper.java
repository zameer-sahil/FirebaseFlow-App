package com.example.myapplication.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqliteHelper extends SQLiteOpenHelper {
    public static final String DB_Name = "users";
    public static final int version = 1;
    public static final String table ="create table user (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(10), email TEXT(20))" ;
    public sqliteHelper(Context context) {
        super(context, DB_Name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open()
    {
        SQLiteDatabase db1 = this.getReadableDatabase();
        return db1;
    }
    public void insert(String name, String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        db.insert("user", null, values);

    }
}
