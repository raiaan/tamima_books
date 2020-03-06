package com.example.tamima_books;

import android.content.Intent;
import android.os.Bundle;

import com.example.tamima_books.Models.BookTitle;
import com.example.tamima_books.Models.Chapter;
import com.example.tamima_books.adapters.ChaptersAdater;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ChaptersActivity extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    BookTitle bookTitle;
    ChapterListFragment chapterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        bookTitle = (BookTitle)intent.getSerializableExtra("book");
        getSupportActionBar().setTitle(bookTitle.getTitle());
        bundle= new Bundle();
        bundle.putSerializable("bookT",bookTitle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        chapterList = new ChapterListFragment();
        chapterList.setArguments(bundle);
        fragmentTransaction.replace(R.id.chapters_list_fragment,chapterList);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
