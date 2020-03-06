package com.example.tamima_books.firebase;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChildListener implements ChildEventListener {
    ArrayAdapter adapter;
    Class type;
    View loading;
    ArrayList<String> keysList;

    public ChildListener(ArrayAdapter adapter, Class type) {
        this.adapter = adapter;
        this.type = type;
        this.keysList = new ArrayList<String>();
    }

    public ChildListener(ArrayAdapter adapter, Class type, View loading ) {
        this(adapter,type);
        this.loading = loading;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        if(loading!=null&&adapter.getCount()==1){
            loading.setVisibility(View.GONE);
        }
        adapter.add(dataSnapshot.getValue(type));
        keysList.add(dataSnapshot.getKey());
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        adapter.remove(dataSnapshot.getValue(type));
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
    public  String getChildKey(int key){
        return this.keysList.get(key);
    }
}
