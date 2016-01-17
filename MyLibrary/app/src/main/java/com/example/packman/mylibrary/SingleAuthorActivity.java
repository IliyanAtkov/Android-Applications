package com.example.packman.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.fragments.AuthorBooksFragment;
import com.example.packman.mylibrary.fragments.AuthorInfoFragment;

public class SingleAuthorActivity extends AppCompatActivity {
    ViewPager mViewPager;
    AuthorPageAdapter pageAdapter;
    Toolbar toolbar;
    static TabLayout tabLayout;
    static Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_author);


        pageAdapter = new AuthorPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.single_author_pager);
        mViewPager.setAdapter(pageAdapter);

        toolbar = (Toolbar) findViewById(R.id.tb_single_author);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.single_author_tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        mViewPager.setCurrentItem(tab.getPosition());
                    }
                });

        tabLayout.getTabAt(0).setText("Info");
        tabLayout.getTabAt(1).setText("Books");

        Intent intent = getIntent();
        bundle = new Bundle();
        bundle.putString(GlobalConstants.AUTHOR_TO_PASS_ID, intent.getExtras().getString(GlobalConstants.AUTHOR_TO_PASS_ID));
        bundle.putString(GlobalConstants.AUTHOR_NAME_TO_PASS, intent.getExtras().getString(GlobalConstants.AUTHOR_NAME_TO_PASS));
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        this.overridePendingTransition(R.anim.clockwise_in, R.anim.clockwise_out);
    }

    public static class AuthorPageAdapter extends FragmentPagerAdapter {

        public AuthorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    AuthorInfoFragment authorInfoFragment = new AuthorInfoFragment();
                    authorInfoFragment.setArguments(bundle);
                    return authorInfoFragment;
                case 1:
                    AuthorBooksFragment authorBooksFragment = new AuthorBooksFragment();
                    authorBooksFragment.setArguments(bundle);
                    return authorBooksFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
