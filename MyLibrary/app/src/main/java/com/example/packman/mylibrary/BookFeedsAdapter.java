package com.example.packman.mylibrary;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BookFeedsAdapter extends CursorAdapter {

    public BookFeedsAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View singleBookView = LayoutInflater.from(context).inflate(R.layout.single_book_layout,parent,false);
        BookViewHolder bookHolder = new BookViewHolder();
        bookHolder.bookImage = (ImageView)singleBookView.findViewById(R.id.bookImage);
        bookHolder.bookTitle = (TextView)singleBookView.findViewById(R.id.bookTitle);
        singleBookView.setTag(bookHolder);

        return singleBookView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        BookViewHolder bookHolder =(BookViewHolder) view.getTag();
    }

    public class BookViewHolder{
        ImageView bookImage;
        TextView bookTitle;
    }
}
