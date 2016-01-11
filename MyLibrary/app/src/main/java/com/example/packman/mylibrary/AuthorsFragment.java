package com.example.packman.mylibrary;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class AuthorsFragment extends Fragment {
    List<ParseObject> authors;
    ListView listView;
    ArrayAdapter<String> adapter;
    
    public AuthorsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View authorsView = inflater.inflate(R.layout.fragment_authors, container, false);
        listView = (ListView) authorsView.findViewById(R.id.authorsList);
        new LoadAuthors().execute();
        return authorsView;
    }

    private class LoadAuthors extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Authors");
            try {
                authors = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter = (new ArrayAdapter<String>(getActivity(), R.layout.fragment_authors, R.id.authorsTextView));
            // Retrieve object "name" from Parse.com database
            for (ParseObject author : authors) {
                adapter.add((String) author.get("Name"));
            }
            // Binds the Adapter to the ListView
            listView.setAdapter(adapter);
        }
    }
}
