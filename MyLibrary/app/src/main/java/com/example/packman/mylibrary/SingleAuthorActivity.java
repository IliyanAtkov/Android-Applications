package com.example.packman.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.adapters.SingleAuthorAdapter;

public class SingleAuthorActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_author);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_single_author);
        setSupportActionBar(toolbar);
        ListView authorsList = (ListView)findViewById(R.id.single_author_list);

        Intent intent = getIntent();
        String authorId = intent.getExtras().getString(GlobalConstants.AUTHOR_TO_PASS_ID);

        SingleAuthorAdapter mAdapter = new SingleAuthorAdapter(this,authorId);
        authorsList.setAdapter(mAdapter);
        mAdapter.loadObjects();
    }

    @Override
    public void onResume(){
        super.onResume();
        this.overridePendingTransition(R.anim.clockwise_in, R.anim.clockwise_out);
    }
}
