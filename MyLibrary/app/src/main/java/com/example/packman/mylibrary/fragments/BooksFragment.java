package com.example.packman.mylibrary.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.packman.mylibrary.Adapters.BookAdapter;
import com.example.packman.mylibrary.R;

public class BooksFragment extends Fragment{

    public BooksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View booksView = (View)inflater.inflate(R.layout.fragment_books, container, false);
        ListView booksList = (ListView)booksView.findViewById(R.id.booksList);
        BookAdapter mAdapter = new BookAdapter(getActivity());
        booksList.setAdapter(mAdapter);
        mAdapter.loadObjects();

        return booksView;
    }
}
