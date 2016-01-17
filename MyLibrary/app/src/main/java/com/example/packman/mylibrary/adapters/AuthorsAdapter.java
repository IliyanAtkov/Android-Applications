package com.example.packman.mylibrary.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.ImageLoadTask;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.models.Authors;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

public class AuthorsAdapter extends ParseQueryAdapter<Authors> {
    public AuthorsAdapter(Context context) {
        super(context, new QueryFactory<Authors>() {
            @Override
            public ParseQuery<Authors> create() {
                ParseQuery query = new ParseQuery(GlobalConstants.AUTHORS_PARSE_TABLE_NAME);
                return query;
            }
        });

    }

    @Override
    public View getItemView(Authors authors, View v, ViewGroup parent) {
        AuthorHolder holder = null;

        if (v == null) {
            v = View.inflate(getContext(), R.layout.row_list_authors, null);

            holder = new AuthorHolder();
            holder.authorImage = (ImageView) v.findViewById(R.id.img_author);
            holder.authorName = (TextView) v.findViewById(R.id.tv_author);

            v.setTag(holder);
        } else {
            holder = (AuthorHolder) v.getTag();
        }

        new ImageLoadTask(authors.getImgUrl(), holder.authorImage).execute();

        holder.authorName.setText(authors.getName());

        return v;
    }

    private static class AuthorHolder {
        ImageView authorImage;
        TextView authorName;
    }
}