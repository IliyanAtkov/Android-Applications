package com.example.packman.mylibrary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.adapters.SingleAuthorAdapter;

public class AuthorInfoFragment extends Fragment {
    private View authorsView;
    private ListView authorsList;
    private String authorId;
    private SingleAuthorAdapter mAdapter;

    public AuthorInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        authorsView = inflater.inflate(R.layout.fragment_author_info, container, false);

        authorsList = (ListView) authorsView.findViewById(R.id.single_author_list);

        authorId = getArguments().getString(GlobalConstants.AUTHOR_TO_PASS_ID);
        mAdapter = new SingleAuthorAdapter(getContext(), authorId);
        authorsList.setAdapter(mAdapter);
        mAdapter.loadObjects();

        return authorsView;
    }
}