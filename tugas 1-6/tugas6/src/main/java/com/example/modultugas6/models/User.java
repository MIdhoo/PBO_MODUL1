package com.example.modultugas6.models;

import java.util.ArrayList;

public abstract class User {
    private String nim;

    public User(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public abstract void displayBooks(ArrayList<Book> bookList);

    public abstract void addStudent();

    public abstract void choiceBook(ArrayList<Book> bookList);
}

