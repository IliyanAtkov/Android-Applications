package com.example.packman.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.adapters.BookListAdapter;

public class BooksByCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_by_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_books_by_category);
        TextView booksByCategoryTitle = (TextView) findViewById(R.id.tv_books_by_category);
        setSupportActionBar(toolbar);

        ListView booksByCategoryList = (ListView) findViewById(R.id.lv_books_by_categories);
        Intent intent = getIntent();
        String categoryTitle = intent.getExtras().getString(GlobalConstants.CATEGORY_TITLE_TO_PASS);
        booksByCategoryTitle.setText(categoryTitle);
        BookListAdapter mAdapter = new BookListAdapter(this, GlobalConstants.CALLED_FROM_CATEGORIES, categoryTitle);
        booksByCategoryList.setAdapter(mAdapter);
        mAdapter.loadObjects();
    }
}
