package com.example.tamima_books.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tamima_books.Models.Chapter;
import com.example.tamima_books.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChaptersAdater extends ArrayAdapter <Chapter> {
    ArrayList<Chapter> chapters;
    public ChaptersAdater(@NonNull Context context,ArrayList<Chapter> chapters) {
        super(context, 0,chapters);
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newView = convertView;
        if (convertView == null){
            newView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item,parent,false
            );
        }
        TextView title = (TextView)newView.findViewById(R.id.title);
        title.setText(getItem(position).getTitle());
        return newView;
    }
}
