package com.example.packman.mylibrary.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.models.Categories;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CategoriesAdapter extends ParseQueryAdapter<Categories> {
    public CategoriesAdapter(Context context) {
        super(context, new QueryFactory<Categories>() {
            @Override
            public ParseQuery<Categories> create() {
                ParseQuery query = new ParseQuery(GlobalConstants.CATEGORIES_PARSE_TABLE_NAME);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Categories categories, View v, ViewGroup parent){
        CategoriesHolder holder = null;

        if (v == null) {
            v = View.inflate(getContext(), R.layout.row_list_categories, null);

            holder = new CategoriesHolder();
            holder.categoryTitle = (TextView) v.findViewById(R.id.tv_category_row);

            v.setTag(holder);
        } else {
            holder = (CategoriesHolder)v.getTag();
        }

        holder.categoryTitle.setText(categories.getTitle());
        return v;
    }

    private static class CategoriesHolder {
        TextView categoryTitle;
    }
}
