package com.example.tamima_books.Models;

import android.os.Parcelable;

import java.io.Serializable;

public class BookTitle implements Serializable {
    private String title;
    private String id;
    String cover;

    public BookTitle(String title, String id,String cover) {
        this.title = title;
        this.cover=cover;
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BookTitle(String title) {
        this.title = title;
    }

    public BookTitle() { }

    public String getTitle() {
        return this.title;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }
}
