package com.example.packman.mylibrary.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.models.Books;
import com.example.packman.mylibrary.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class SingleBookAdapter extends ParseQueryAdapter<Books>{
    private static String bookToPassID;
    public SingleBookAdapter(Context context,final String bookID){
        super(context, new QueryFactory<Books>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery(GlobalConstants.BOOKS_PARSE_OBJ_NAME);
                try {
                    query.get(bookID);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return query;
            }
        });
    }

    @Override
    public View getItemView(Books bookObject, View v, ViewGroup parent){
        //bookToPassID=bookObject.getObjectID();
        if (v == null){
            v = View.inflate(getContext(), R.layout.content_single_book, null);
        }

        //v.setClickable(true);
        //v.setOnClickListener(this);

        super.getItemView(bookObject, v, parent);

        ParseImageView bookImage = (ParseImageView)v.findViewById(R.id.single_book_image);
        ParseFile imageFile = bookObject.getImage();
        if (imageFile != null){
            bookImage.setParseFile(imageFile);
            bookImage.loadInBackground();
        }

        TextView singleBookTitle = (TextView) v.findViewById(R.id.single_book_title);
        singleBookTitle.setText(bookObject.getTitle());

        TextView singleBookAuthor = (TextView) v.findViewById(R.id.single_book_author);
        singleBookAuthor.setText(bookObject.getAuthor());

        TextView singleBookLanguage = (TextView) v.findViewById(R.id.single_book_language);
        singleBookLanguage.setText(bookObject.getLanguage());

        TextView singleBookDescription = (TextView) v.findViewById(R.id.single_book_description);
        singleBookDescription.setText(bookObject.getDescription());

        return v;
    }

    /*@Override
    /*public void onClick(View v) {
        Intent in = new Intent(this.getContext(), SingleBookActivity.class);
        in.putExtra(GlobalConstants.BOOK_TO_PASS_ID,bookToPassID);
        this.getContext().startActivity(in);
    }*/
}