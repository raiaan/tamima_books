package com.example.tamima_books;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.tamima_books.Models.BookTitle;
import com.example.tamima_books.adapters.BookListAdapter;
import com.example.tamima_books.firebase.ChildListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<BookTitle> booksTitles;
    BookListAdapter booksAdapter;
    GridView books;
    Intent intent;

    private AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            intent = new Intent(getBaseContext(),ChaptersActivity.class);
            intent.putExtra("book",(BookTitle)parent.getItemAtPosition(position));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booksTitles = (ArrayList<BookTitle>) (getIntent()).getSerializableExtra("books");
        booksAdapter = new BookListAdapter(getBaseContext(),booksTitles);
        books = (GridView) findViewById(R.id.books_list);
        books.setAdapter(booksAdapter);
        books.setOnItemClickListener(click);
    }
}
