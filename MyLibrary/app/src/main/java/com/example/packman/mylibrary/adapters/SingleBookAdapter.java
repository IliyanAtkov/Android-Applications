package com.example.packman.mylibrary.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.MainActivity;
import com.example.packman.mylibrary.SingleBookActivity;
import com.example.packman.mylibrary.data.BooksContract;
import com.example.packman.mylibrary.data.MyLibraryDbHelper;
import com.example.packman.mylibrary.fragments.FavouriteBooksFragment;
import com.example.packman.mylibrary.models.Books;
import com.example.packman.mylibrary.R;
import com.parse.GetDataCallback;
import com.parse.GetFileCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ProgressCallback;

import java.io.File;


public class SingleBookAdapter extends ParseQueryAdapter<Books> {
    private static Books bookToFavourite;
    private static SQLiteOpenHelper SQLiteHelper;
    private static SQLiteDatabase db;
    private static String calledFrom;
    private static Animation zoomin;
    private static Animation zoomout;
    private static boolean pressed;

    public SingleBookAdapter(Context context, final String bookID, final String from) {
        super(context, new QueryFactory<Books>() {
            public ParseQuery create() {
                calledFrom = from;
                ParseQuery query = new ParseQuery(GlobalConstants.BOOKS_PARSE_TABLE_NAME);
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
    public View getItemView(Books bookObject, View v, final ViewGroup parent) {
        bookToFavourite = bookObject;
        if (v == null) {
            v = View.inflate(getContext(), R.layout.content_single_book, null);
        }
        Button addToFavouriteButton = (Button) v.findViewById(R.id.add_book_to_favourite_button);

        SQLiteHelper = new MyLibraryDbHelper(getContext());
        db = SQLiteHelper.getWritableDatabase();

        if (calledFrom.equals(GlobalConstants.CALLED_FROM_BOOKS)) {
            addToFavouriteButton.setText("Add to Favourite");
            addToFavouriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String query = "SELECT * FROM "
                            + BooksContract.FavouriteBooksIdsEntry.TABLE_NAME
                            + " WHERE "
                            + BooksContract.FavouriteBooksIdsEntry.COLUMN_FAVOURITE_BOOK_PARSE_ID
                            + "='"
                            + bookToFavourite.getObjectID() + "'";

                    Cursor c = db.rawQuery(query, null);

                    if (c.getCount() == 0) {
                        ContentValues values = new ContentValues();
                        values.put(BooksContract.FavouriteBooksIdsEntry.COLUMN_FAVOURITE_BOOK_PARSE_ID, bookToFavourite.getObjectID());
                        db.insert(
                                BooksContract.FavouriteBooksIdsEntry.TABLE_NAME,
                                null,
                                values);
                        Toast.makeText(getContext(), "Book was added to favourites", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Book was already added to favourites", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            addToFavouriteButton.setText("REMOVE");
            addToFavouriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db.delete(BooksContract.FavouriteBooksIdsEntry.TABLE_NAME, BooksContract.FavouriteBooksIdsEntry.COLUMN_FAVOURITE_BOOK_PARSE_ID
                            + "='"
                            + bookToFavourite.getObjectID() + "'", null);

                    Intent in = new Intent(getContext(), MainActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    getContext().startActivity(in);
                }

            });
        }
        Button downloadButton = (Button) v.findViewById(R.id.download_book_button);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        super.getItemView(bookObject, v, parent);

        ParseImageView bookImage = (ParseImageView) v.findViewById(R.id.single_book_image);
        ParseFile imageFile = bookObject.getImage();
        if (imageFile != null) {
            bookImage.setParseFile(imageFile);
            bookImage.loadInBackground();
        }
        zoomin = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        zoomout = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_out);

        pressed = false;
        bookImage.setOnLongClickListener(new OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(!pressed) {
                    v.bringToFront();
                    v.startAnimation(zoomin);
                    pressed = !pressed;
                } else {
                    v.startAnimation(zoomout);
                    pressed = !pressed;
                }
                return true;
            }
        });

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
}