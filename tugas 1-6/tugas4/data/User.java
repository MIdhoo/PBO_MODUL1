package modul4.tugas.data;

import modul4.tugas.books.Book;

public abstract class User {

    private String nim;

    public User(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void displayBooks(Book[] bookList){

    }
    public void addStudent(){

    }
    public void choiceBook(Book[] bookList){

    }

}
