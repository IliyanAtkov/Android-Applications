package com.example.packman.mylibrary.data;

import android.provider.BaseColumns;


public class BooksContract {
    public static final class FavouriteBooksIdsEntry implements BaseColumns {
        public static final String TABLE_NAME = "favourite_books_ids";

        public static final String COLUMN_FAVOURITE_BOOK_PARSE_ID = "book_parse_id";
    }
}