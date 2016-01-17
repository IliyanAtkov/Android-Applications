package com.example.packman.mylibrary.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.SingleAuthorActivity;
import com.example.packman.mylibrary.adapters.AuthorsAdapter;
import com.example.packman.mylibrary.models.Authors;

public class AuthorsFragment extends Fragment{
    Context context;
    GridView gridView;

    public AuthorsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View authorsView = inflater.inflate(R.layout.fragment_authors, container, false);

        gridView = (GridView) authorsView.findViewById(R.id.grid_authors);


        final AuthorsAdapter mAdapter = new AuthorsAdapter(context);

        gridView.setAdapter(mAdapter);
        mAdapter.loadObjects();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Authors author = mAdapter.getItem(position);
                Intent intent = new Intent(context, SingleAuthorActivity.class);
                intent.putExtra(GlobalConstants.AUTHOR_TO_PASS_ID, author.getObjectID());
                intent.putExtra(GlobalConstants.AUTHOR_NAME_TO_PASS, author.getName());

                startActivity(intent);
            }
        });

        return authorsView;
    }
}