package com.example.packman.mylibrary.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.packman.mylibrary.R;

public class FavouriteBooksFragment extends Fragment {
    private View booksView;
    private ListView listOfBooks;
    public FavouriteBooksFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        booksView = (View)inflater.inflate(R.layout.fragment_books, container, false);
        listOfBooks = (ListView) booksView.findViewById(R.id.booksList);
        return booksView;
    }
}
