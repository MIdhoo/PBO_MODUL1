    package com.example.modultugas6.models;

    import java.util.ArrayList;

    public class Book {
        private static ArrayList<Book> allBooks = new ArrayList<>();
        private String idBuku;
        private String judul;
        private int stok;
        private String category;
        private String author;
        private int duration;

        // Constructor
        public Book(String idBuku, String judul, int stok, String category, String author, int duration) {
            this.idBuku = idBuku;
            this.judul = judul;
            this.stok = stok;
            this.category = category;
            this.author = author;
            this.duration = duration;
        }

        public Book(String idBuku, String judul, int stok, String category, String author) {
        }

        // Getter and Setter methods
        public String getIdBuku() {
            return idBuku;
        }

        public void setIdBuku(String idBuku) {
            this.idBuku = idBuku;
        }

        public String getJudul() {
            return judul;
        }

        public void setJudul(String judul) {
            this.judul = judul;
        }

        public int getStok() {
            return stok;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
        public static void addBook(Book book) {
            allBooks.add(book);
        }

        public static ArrayList<Book> getAllBooks() {
            return allBooks;
        }
        public static Book getBookById(String id) {
            for (Book book : allBooks) {
                if (book.getIdBuku().equals(id)) {
                    return book;
                }
            }
            return null; // Book not found
        }

        public void borrowBook() {
            if (stok > 0) {
                stok--;
            }
        }
        public void returnBook() {
            stok++;
        }

        // Static method to get all books (dummy implementation for now)
    //    public static ArrayList<Book> getAllBooks() {
    //        ArrayList<Book> books = new ArrayList<>();
    //        books.add(new Book("1", "Book Title 1", 5, "Category 1", "Author 1", 7));
    //        books.add(new Book("2", "Book Title 2", 3, "Category 2", "Author 2", 14));
    //        // Add more books as needed
    //        return books;
    //    }
    }
