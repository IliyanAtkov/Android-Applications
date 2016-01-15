package com.example.packman.mylibrary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.adapters.BookListAdapter;

public class FavouriteBooksFragment extends Fragment {
    private static View booksView;
    public FavouriteBooksFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        booksView = (View)inflater.inflate(R.layout.fragment_favourite_books, container, false);

        return booksView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ListView booksList = (ListView)booksView.findViewById(R.id.favourite_book_list);
        BookListAdapter mAdapter = new BookListAdapter(getActivity(),GlobalConstants.CALLED_FROM_FAVOURITES);
        booksList.setAdapter(mAdapter);
        mAdapter.loadObjects();

    }
}
