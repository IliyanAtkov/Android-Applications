package com.example.packman.mylibrary.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.SimpleCursorLoader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class BooksFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView booksList;
    private View booksView;

    public BooksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        booksView = (View)inflater.inflate(R.layout.fragment_books, container, false);
        booksList = (ListView)booksView.findViewById(R.id.booksList);
        //booksList.setAdapter(new BookFeedsAdapter());

        getActivity().getSupportLoaderManager().initLoader(0,null,this);
        return inflater.inflate(R.layout.fragment_books, container, false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public static final class BooksCursorLoader extends SimpleCursorLoader {

        public BooksCursorLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return null;
        }
    }

    /*class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            HttpClient
            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                    // Do normal input or output stream reading
                }
                else {
                    response = "FAILED"; // See documentation for more info on response handling
                }
            }  catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
        }
    }*/
}
