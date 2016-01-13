package com.example.packman.mylibrary.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.Models.Books;
import com.example.packman.mylibrary.R;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class BookAdapter extends ParseQueryAdapter<Books> {
    public BookAdapter(Context context){
        super(context, new QueryFactory<Books>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery(GlobalConstants.BOOKS_PARSE_OBJ_NAME);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Books bookObject, View v, ViewGroup parent){
        if (v == null){
            v = View.inflate(getContext(), R.layout.single_book_layout, null);
        }

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
}
