package com.example.tamima_books;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.tamima_books.Models.BookTitle;
import com.example.tamima_books.Models.Chapter;
import com.example.tamima_books.adapters.ChaptersAdater;
import com.example.tamima_books.firebase.ChildListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ChapterListFragment extends Fragment {
    Intent intent;
    private DatabaseReference book1;
    BookTitle bookTitle;
    Query q;
    ListView chaptersList;
    ChaptersAdater chAdapter;
    ChapterFragment chapterContent;
    Bundle bundle,getBundle;
    ImageView cover;
    ChildListener child;
    AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bundle.putString("title",((Chapter)parent.getItemAtPosition(position)).getTitle());
            bundle.putString("key",child.getChildKey(position));
            bundle.putString("bookKey",bookTitle.getId());
            chapterContent.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.chapters_list_fragment,chapterContent);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chapters_list,container,false);
        bundle = new Bundle();

        getBundle=getArguments();
        bookTitle = (BookTitle)getBundle.getSerializable("bookT");
        book1 = FirebaseDatabase.getInstance().getReference()
                    .child(bookTitle.getId()).child("chapters");
        bundle.putString("cover",bookTitle.getCover());
        chaptersList = (ListView) view.findViewById(R.id.chapters_list);
        cover = (ImageView) view.findViewById(R.id.cover_book);
        Glide.with(getActivity()).load(bookTitle.getCover()).into(cover);
        chAdapter = new ChaptersAdater(getContext(),new ArrayList<Chapter>());
        q= book1.orderByKey();
        child = new ChildListener(chAdapter,
                Chapter.class,
                (ProgressBar)view.findViewById(R.id.loading_chapters));
        q.addChildEventListener(child);
        chapterContent = new ChapterFragment();
        chaptersList.setAdapter(chAdapter);
        chaptersList.setOnItemClickListener(click);
        return  view;
    }
}
