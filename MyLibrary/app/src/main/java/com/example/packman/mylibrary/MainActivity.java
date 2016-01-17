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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.packman.mylibrary.Services.LocationService;
import com.example.packman.mylibrary.Services.ScreenOrientationService;
import com.example.packman.mylibrary.animations.ZoomOutPageTransformer;
import com.example.packman.mylibrary.fragments.AuthorsFragment;
import com.example.packman.mylibrary.fragments.BooksFragment;
import com.example.packman.mylibrary.fragments.CategoriesFragment;
import com.example.packman.mylibrary.fragments.FavouriteBooksFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    MyLibraryPageAdapter pageAdapter;
    Toolbar toolbar;
    static TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, ScreenOrientationService.class));
        startService(new Intent(this, LocationService.class));

        pageAdapter = new MyLibraryPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pageAdapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        mViewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        // TODO Auto-generated method stub
                    }
                });
        tabLayout.getTabAt(0).setText("Favourite Books");
        tabLayout.getTabAt(1).setText("Authors");
        tabLayout.getTabAt(2).setText("Categories");
        tabLayout.getTabAt(3).setText("Books");

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyLibraryPageAdapter extends FragmentPagerAdapter {

        public MyLibraryPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new FavouriteBooksFragment();
                case 1:
                    return new AuthorsFragment();
                case 2:
                    return new CategoriesFragment();
                case 3:
                    return new BooksFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}


