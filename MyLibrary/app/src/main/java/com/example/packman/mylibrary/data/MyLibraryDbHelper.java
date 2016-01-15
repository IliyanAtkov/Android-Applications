package com.example.packman.mylibrary.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyLibraryDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "store.db";

    public MyLibraryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVOURITE_BOOK_PARSE_IDS_TABLE =
                "CREATE TABLE " + BooksContract.FavouriteBooksIdsEntry.TABLE_NAME + " (" +
                        BooksContract.FavouriteBooksIdsEntry._ID + " INTEGER PRIMARY KEY," +
                        BooksContract.FavouriteBooksIdsEntry.COLUMN_FAVOURITE_BOOK_PARSE_ID + " TEXT NOT NULL" +
                        ");";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITE_BOOK_PARSE_IDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}