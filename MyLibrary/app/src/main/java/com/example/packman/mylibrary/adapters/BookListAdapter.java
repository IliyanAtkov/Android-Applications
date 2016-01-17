package com.example.packman.mylibrary.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.data.BooksContract;
import com.example.packman.mylibrary.data.MyLibraryDbHelper;
import com.example.packman.mylibrary.models.Books;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.SingleBookActivity;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;

public class BookListAdapter extends ParseQueryAdapter<Books> implements View.OnClickListener {
    private static String bookToPassID;
    private static String calledFrom;

    public BookListAdapter(final Context context, final String from, final String whereParameter) {
        super(context, new QueryFactory<Books>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery(GlobalConstants.BOOKS_PARSE_TABLE_NAME);
                calledFrom = from;
                if (from == GlobalConstants.CALLED_FROM_CATEGORIES) {
                    query.whereEqualTo("Category", whereParameter);
                } else {
                    query.whereEqualTo("Author", whereParameter);
                }

                return query;
            }
        });
    }

    public BookListAdapter(final Context context, final String from) {
        super(context, new QueryFactory<Books>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery(GlobalConstants.BOOKS_PARSE_TABLE_NAME);
                calledFrom = from;
                if (from == GlobalConstants.CALLED_FROM_BOOKS) {
                    return query;
                } else {
                    SQLiteOpenHelper SQLiteHelper = new MyLibraryDbHelper(context);
                    SQLiteDatabase db = SQLiteHelper.getWritableDatabase();
                    String sql = "SELECT "+BooksContract.FavouriteBooksIdsEntry.COLUMN_FAVOURITE_BOOK_PARSE_ID+" FROM "
                            + BooksContract.FavouriteBooksIdsEntry.TABLE_NAME;

                    Cursor c = db.rawQuery(sql, null);
                    ArrayList<String> list = new ArrayList<String>();

                    if (c.moveToFirst()){
                        do{
                            list.add(c.getString(0));
                        }while(c.moveToNext());
                    }

                    query.whereContainedIn("objectId",list);
                    return query;
                }
            }
        });
    }

    @Override
    public View getItemView(Books bookObject, View v, ViewGroup parent) {
        bookToPassID = bookObject.getObjectID();
        if (v == null) {
            v = View.inflate(getContext(), R.layout.single_book_list_layout, null);
        }

        v.setClickable(true);
        v.setOnClickListener(this);

        super.getItemView(bookObject, v, parent);

        ParseImageView bookImage = (ParseImageView) v.findViewById(R.id.bookImage);
        ParseFile imageFile = bookObject.getImage();
        if (imageFile != null) {
            bookImage.setParseFile(imageFile);
            bookImage.loadInBackground();
        }


        TextView bookTitle = (TextView) v.findViewById(R.id.bookTitle);
        bookTitle.setText(bookObject.getTitle());

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(this.getContext(), SingleBookActivity.class);
        in.putExtra(GlobalConstants.BOOK_TO_PASS_ID, bookToPassID);
        in.putExtra(GlobalConstants.CALLED_FROM, calledFrom);
        this.getContext().startActivity(in);
    }
}
