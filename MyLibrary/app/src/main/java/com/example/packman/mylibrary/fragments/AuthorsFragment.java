package com.example.packman.mylibrary.Fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.Adapters.AuthorsAdapter;
import com.example.packman.mylibrary.Models.Author;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class AuthorsFragment extends Fragment {
    Context context;
    List<ParseObject> authorsAsParseObjects;
    ListView listView;
    ArrayAdapter<Author> adapter;
    ArrayList<Author> authors;
    ParseQuery<ParseObject> db;

    public AuthorsFragment() {
    }

   // public AuthorsFragment(Context context) {
   //     this(context, new ParseQuery<>(context.getString(R.string.authors_table_name)));
   // }
//
   // public AuthorsFragment(Context context, ParseQuery<ParseObject> db) {
   //     this(context, db, new AuthorsAdapter(context, R.layout.row_list_authors, null));
   // }
//
   // public AuthorsFragment(Context context, ParseQuery<ParseObject> db, ArrayAdapter<Author> adapter) {
   //     this.context = context;
   //     this.db = db;
   //     this.adapter = adapter;
   // }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View authorsView = inflater.inflate(R.layout.fragment_authors, container, false);
        this.db = new ParseQuery<>(this.getString(R.string.authors_table_name));
        this.authors = new ArrayList<>();
        listView = (ListView) authorsView.findViewById(R.id.list_authors);

        new LoadAuthors().execute();
        return authorsView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class LoadAuthors extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                authorsAsParseObjects = db.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for (ParseObject author : authorsAsParseObjects) {
                authors.add(new Author((String)author.get("Name"), (String)author.get("Img_url")));
            }
            adapter = new AuthorsAdapter(context, R.layout.row_list_authors, authors);
            listView.setAdapter(adapter);
        }
    }
}
