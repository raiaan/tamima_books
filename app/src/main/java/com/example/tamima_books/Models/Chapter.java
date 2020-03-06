package com.example.tamima_books.Models;

import java.io.Serializable;

public class Chapter implements Serializable {
    String Title;

    public Chapter( String text, String title) {
        Title = title;
    }

    public Chapter(){}


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
