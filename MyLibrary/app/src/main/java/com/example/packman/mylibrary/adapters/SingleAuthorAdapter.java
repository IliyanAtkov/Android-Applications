package com.example.packman.mylibrary.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.packman.mylibrary.Globals.GlobalConstants;
import com.example.packman.mylibrary.ImageLoadTask;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.models.Authors;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class SingleAuthorAdapter extends ParseQueryAdapter<Authors>{
    public SingleAuthorAdapter(Context context,final String authorId){
        super(context, new QueryFactory<Authors>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery(GlobalConstants.AUTHORS_PARSE_TABLE_NAME);
                try {
                    query.get(authorId);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return query;
            }
        });
    }

    @Override
    public View getItemView(Authors authors, View v, ViewGroup parent) {
        AuthorHolder holder = null;

        if (v == null) {
            v = View.inflate(getContext(), R.layout.content_single_author, null);

            holder = new AuthorHolder();

            holder.authorName = (TextView) v.findViewById(R.id.tv_single_author_name);
            holder.authorNationality = (TextView) v.findViewById(R.id.tv_single_author_nationality);
            holder.authorBiography = (TextView) v.findViewById(R.id.tv_single_author_biography);
            holder.authorBorn = (TextView) v.findViewById(R.id.tv_single_author_born);
            holder.authorDied = (TextView) v.findViewById(R.id.tv_single_author_died);
            holder.authorImage = (ImageView) v.findViewById(R.id.img_single_author);

            v.setTag(holder);
        } else {
            holder = (AuthorHolder)v.getTag();
        }

        super.getItemView(authors, v, parent);

        holder.authorName.setText(authors.getName());
        holder.authorNationality.setText(authors.getNationality());
        holder.authorBiography.setText(authors.getBiography());
        holder.authorBorn.setText(authors.getBorn().toString());
        holder.authorDied.setText(authors.getDied().toString());
        new ImageLoadTask(authors.getImgUrl(), holder.authorImage).execute();

        return v;
    }

    private static class AuthorHolder {
        ImageView authorImage;
        TextView authorName;
        TextView authorNationality;
        TextView authorBiography;
        TextView authorBorn;
        TextView authorDied;
    }
}
