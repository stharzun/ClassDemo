package com.stharzun.demoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "demoApp.db";
    private static final String TABLE_ONE = "userList";
    private static final String TAG = "DBHelper";

    private String createQuery = "create table if not exists " + TABLE_ONE + "(ID integer primary key autoincrement," +
            "name text," +
            "address text," +
            "email text," +
            "contact text," +
            "username text," +
            "password text," +
            "gender text)";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_ONE);
    }

    public void insertUser(String n, String a, String e, String c, String u, String p, String g) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", n);
        cv.put("address", a);
        cv.put("email", e);
        cv.put("contact", c);
        cv.put("username", u);
        cv.put("password", p);
        cv.put("gender", g);
        long inserted = db.insert(TABLE_ONE, null, cv);
        Log.d(TAG, "insertUser() returned: " + inserted);
    }

    public void isUserAvailable(String u, String p) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("select * from %s where username = '%s' and password = '%s'", TABLE_ONE, u, p);
        Cursor c = db.rawQuery(query, null);
    }



}
