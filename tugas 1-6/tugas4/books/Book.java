package modul4.tugas.books;

public class Book {
    private String idBuku;
    private String judul;
    private int stok;
    private String author;
    private String category;

    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Book(String idBuku, String judul, int stok, String category , String author) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.stok = stok;
        this.category = category;
        this.author = author;
    }

    public Book(String idBuku, String judul, int stok, String category , String author, int duration) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.stok = stok;
        this.category = category;
        this.author = author;
        this.duration = duration;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public int getStok() {
        return stok;
    }


    public String getCategory() {
        return category;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getAuthor() {
        return author;
    }
}
