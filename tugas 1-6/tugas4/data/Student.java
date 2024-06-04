package modul4.tugas.data;

import modul4.tugas.books.Book;
import modul4.tugas.util.iMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static modul4.tugas.com.main.LibrarySystem.daftarBuku;

public class Student extends User implements iMenu {

    Scanner scanner = new Scanner(System.in);
    private String name;
    private String faculty;
    private String studyProgram;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String nim) {
        super(nim);
    }

    public Student(String nim, String name, String faculty, String studyProgram) {
        super(nim);
        this.name = name;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public void login() {
        if (checkNim(getNim())) {
            System.out.println("Login berhasil sebagai Mahasiswa");
            menu();
        } else {
            System.out.println("NIM Mahasiswa tidak valid atau tidak ditemukan");
        }
    }

    private boolean checkNim(String nim) {
        return nim.length() == 15;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu Mahasiswa");
            System.out.println("1. Buku Terpinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Pinjam Buku atau Logout");
            System.out.print("Pilih antara (1-4): ");
            int pilih = scanner.nextInt();

            switch (pilih) {
                case 1:
                    System.out.println("Buku terpinjam: ");
                    displayBorrowedBooks();
                    break;
                case 2:
                    choiceBook(daftarBuku);
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak ada buku yang terpinjam.");
        } else {
            System.out.println("=================================================================================");
            System.out.println("|| No. || Id Buku       || Nama Buku    || Author      || Category   || Durasi ||");
            System.out.println("=================================================================================");
            int index = 1;
            for (Book book : borrowedBooks) {
                System.out.println("|| " + index + " || " + book.getIdBuku() + " || " + book.getJudul() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getDuration() + " ||");
                index++;
            }
            System.out.println("=================================================================================");
        }
    }
    @Override
    public void choiceBook(Book[] bookList) {
        System.out.println("Daftar Buku Tersedia:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : bookList) {
            if (book != null) {
                System.out.println("|| " + index + " || " + book.getIdBuku() + " || " + book.getJudul() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStok() + " ||");
                index++;
            }
        }

        System.out.println("================================================================");
        System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
        String bookId = scanner.next();
        Book selectedBook = findBookById(bookId);
        scanner.nextLine();
        int durasi;
        if (selectedBook != null) {
            System.out.print("Masukkan durasi pinjaman: ");
            durasi = Integer.parseInt(scanner.nextLine());
            selectedBook.setDuration(durasi);

            if (selectedBook.getStok() > 0) {
                selectedBook.setStok(selectedBook.getStok() - 1);
                borrowedBooks.add(selectedBook);
            } else {
                System.out.println("Buku tidak tersedia.");
            }
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }


    private void returnBook() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku.");
            return;
        }
        System.out.println("Buku yang Anda pinjam:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println((i + 1) + ". " + borrowedBooks.get(i).getJudul());
        }
        System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= borrowedBooks.size()) {
            Book returnedBook = borrowedBooks.remove(choice - 1);
            returnedBook.setStok(returnedBook.getStok() + 1);
            System.out.println("Buku " + returnedBook.getJudul() + " berhasil dikembalikan.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private Book findBookById(String id) {
        for (Book book : daftarBuku) {
            if (book != null && book.getIdBuku().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private void logout() {
        Scanner inp = new Scanner(System.in);

        if (borrowedBooks.isEmpty()) {
            System.out.println("Logout berhasil.");
        } else {
            displayBorrowedBooks();
            System.out.println("Apakah anda yakin untuk meminjam semua buku tersebut? ");
            System.out.print("Input Y (iya) atau T (tidak): ");
            char pilih = inp.next().charAt(0);

            if (pilih == 'y' || pilih == 'Y'){
                System.out.println("Peminjaman buku berhasil di lakukan");
                System.out.println("Logout berhasil.");
            } else if (pilih == 't' || pilih == 'T'){
                System.out.println("Buku telah dikembalikan");
                System.out.println("Logout berhasil.");
            }
        }

    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
