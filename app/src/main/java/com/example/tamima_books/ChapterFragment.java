package com.example.tamima_books;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChapterFragment extends Fragment {
    Bundle bundle;
    TextView title;
    TextView content;
    ScrollView scrollView;
    DatabaseReference contentRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chapter_fragment,container,false);
        bundle = getArguments();
        contentRef = FirebaseDatabase.getInstance().getReference()
                .child(bundle.getString("bookKey"))
                .child("chapters")
                .child(bundle.getString("key"))
                .child("text");

        scrollView= (ScrollView) view.findViewById(R.id.chapter_content);
        title = (TextView) view.findViewById(R.id.chapter_title);
        content=(TextView) view.findViewById(R.id.chapter_text);
        contentRef.addValueEventListener(listener);
        title.setText(bundle.getString("title","الفصل"));

        Glide.with(getActivity())
                .load(bundle.getString("cover"))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            resource.setAlpha(60);
                            scrollView.setBackground(resource);
                        }
                    }
                });
        return view;
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            content.setText(dataSnapshot.getValue(String.class));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
