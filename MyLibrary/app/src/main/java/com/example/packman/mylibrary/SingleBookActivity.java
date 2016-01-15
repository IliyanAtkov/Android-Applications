package com.example.packman.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.packman.mylibrary.adapters.SingleBookAdapter;
import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.models.Books;

public class SingleBookActivity extends AppCompatActivity {

    public static Books book;
    public static View singleBookView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView booksList = (ListView)findViewById(R.id.single_book_list);

        Intent intent = getIntent();
        String bookID = intent.getExtras().getString(GlobalConstants.BOOK_TO_PASS_ID);
        String calledFrom = intent.getExtras().getString(GlobalConstants.CALLED_FROM);

        SingleBookAdapter mAdapter = new SingleBookAdapter(this,bookID,calledFrom);
        booksList.setAdapter(mAdapter);
        mAdapter.loadObjects();
        }

    @Override
    public void onResume(){
        super.onResume();
        this.overridePendingTransition(R.anim.clockwise_in, R.anim.clockwise_out);
    }

    @Override
    public void onPause(){
        super.onPause();
        this.overridePendingTransition(R.anim.clockwise_out,R.anim.clockwise_in);
    }

    }


