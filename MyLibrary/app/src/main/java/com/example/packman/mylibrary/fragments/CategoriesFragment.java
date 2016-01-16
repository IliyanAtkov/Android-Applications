package com.example.packman.mylibrary.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.packman.mylibrary.BooksByCategoryActivity;
import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.adapters.CategoriesAdapter;
import com.example.packman.mylibrary.models.Categories;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class CategoriesFragment extends Fragment {
    Context context;
    ListView listView;

    public CategoriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View categoriesView = inflater.inflate(R.layout.fragment_categories, container, false);
        listView = (ListView) categoriesView.findViewById(R.id.lv_categories);

        final CategoriesAdapter mAdapter = new CategoriesAdapter(context);
        listView.setAdapter(mAdapter);

        mAdapter.loadObjects();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categories category = mAdapter.getItem(position);
                Intent intent = new Intent(context, BooksByCategoryActivity.class);
                intent.putExtra(GlobalConstants.CATEGORY_TITLE_TO_PASS, category.getTitle());

                startActivity(intent);
            }
        });

        return categoriesView;
    }
}
