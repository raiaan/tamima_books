package com.example.tamima_books.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tamima_books.Models.BookTitle;
import com.example.tamima_books.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class BookListAdapter extends ArrayAdapter <BookTitle> {
    public BookListAdapter(@NonNull Context context,ArrayList<BookTitle> books) {
        super(context,0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currView = convertView;
        if(convertView==null){
            currView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_cover_item, parent,false);
        }
        TextView title= (TextView) currView.findViewById(R.id.mean_book_title);
        ImageView cover = (ImageView) currView.findViewById(R.id.mean_book_cover);

        title.setTypeface(ResourcesCompat.getFont(getContext(),R.font.arb_fonts_sultani_bold));
        title.setTextSize(18);
        title.setText(getItem(position).getTitle());

        Glide.with(getContext())
                .load(getItem(position).getCover())
                .into(cover);
        return currView;
    }
}
