package com.example.tamima_books;

import android.content.Intent;
import android.os.Bundle;

import com.example.tamima_books.Models.BookTitle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

public class Splash extends AppCompatActivity {

    private DatabaseReference book1;
    ArrayList<BookTitle> books ;
    Intent intent;
    TextView text ;
    private ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot ds : dataSnapshot.getChildren()){
                books.add(ds.getValue(BookTitle.class));
            }
            intent.putExtra("books",books);
            startActivity(intent);
            finish();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);

        text = (TextView) findViewById(R.id.text);
        text.startAnimation(AnimationUtils.loadAnimation(this,R.anim.progress));

        intent = new Intent(this,MainActivity.class);
        book1 = FirebaseDatabase.getInstance().getReference().child("books_titles");
        books = new ArrayList<BookTitle>();
        book1.addValueEventListener(listener);
    }

}
