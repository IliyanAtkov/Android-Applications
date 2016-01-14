package com.example.packman.mylibrary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.models.Books;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.SingleBookActivity;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class BookListAdapter extends ParseQueryAdapter<Books> implements View.OnClickListener {
    private static String bookToPassID;
    public BookListAdapter(Context context){
        super(context, new QueryFactory<Books>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery(GlobalConstants.BOOKS_PARSE_OBJ_NAME);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Books bookObject, View v, ViewGroup parent){
        bookToPassID=bookObject.getObjectID();
        if (v == null){
            v = View.inflate(getContext(), R.layout.single_book_list_layout, null);
        }

        v.setClickable(true);
        v.setOnClickListener(this);

        super.getItemView(bookObject, v, parent);

        ParseImageView bookImage = (ParseImageView)v.findViewById(R.id.bookImage);
        ParseFile imageFile = bookObject.getImage();
        if (imageFile != null){
            bookImage.setParseFile(imageFile);
            bookImage.loadInBackground();
        }


        TextView bookTitle = (TextView)v.findViewById(R.id.bookTitle);
        bookTitle.setText(bookObject.getTitle());

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(this.getContext(), SingleBookActivity.class);
        in.putExtra(GlobalConstants.BOOK_TO_PASS_ID, bookToPassID);
        this.getContext().startActivity(in);
    }
}
