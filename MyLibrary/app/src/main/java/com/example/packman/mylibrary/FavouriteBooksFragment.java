package com.example.packman.mylibrary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class FavouriteBooksFragment extends Fragment {
    private ListView listOfBooks;
    public FavouriteBooksFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listOfBooks = (ListView) getView().findViewById(R.id.listView);
        return inflater.inflate(R.layout.fragment_favourite_books, container, false);
    }
}
