package com.example.packman.mylibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.packman.mylibrary.ImageLoadTask;
import com.example.packman.mylibrary.R;
import com.example.packman.mylibrary.models.Author;

import java.util.ArrayList;

public class AuthorsAdapter extends ArrayAdapter<Author> {
    Context context;
    int layoutResourceId;
    ArrayList<Author> authors = null;

    public AuthorsAdapter(Context context, int layoutResourceId, ArrayList<Author> authors) {
        super(context, layoutResourceId, authors);
        this.context = context;
        this.authors = authors;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AuthorHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new AuthorHolder();
            holder.picture = (ImageView)row.findViewById(R.id.img_author);
            holder.name = (TextView)row.findViewById(R.id.tv_author);

            row.setTag(holder);
        } else {
            holder = (AuthorHolder)row.getTag();
        }

        Author author = authors.get(position);
        holder.name.setText(author.getName());
        new ImageLoadTask(author.getImgUrl(), holder.picture).execute();

        return row;
    }

    private static class AuthorHolder {
        ImageView picture;
        TextView name;
    }
}