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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.net.URLConnection;
import java.util.List;

public class CategoriesFragment extends Fragment {
    List<ParseObject> categories;
    ListView listView;
    ArrayAdapter<String> adapter;

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View categoriesView = inflater.inflate(R.layout.fragment_categories, container, false);
        listView = (ListView) categoriesView.findViewById(R.id.categoriesList);
         new LoadCategories().execute();
        return categoriesView;
    }

    private class LoadCategories extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Categories");
            try {
                categories = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter = (new ArrayAdapter<String>(getActivity(), R.layout.fragment_categories, R.id.categoryTextView));
            // Retrieve object "name" from Parse.com database
            for (ParseObject category : categories) {
                adapter.add((String) category.get("Title"));
            }
            // Binds the Adapter to the ListView
            listView.setAdapter(adapter);
        }
    }
}
