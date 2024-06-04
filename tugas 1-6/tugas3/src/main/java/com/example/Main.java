package com.example;

import java.util.Scanner;
import java.util.ArrayList;

class tugas {
    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Student> userStudent = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login Sebagai Mahasiswa");
            System.out.println("2. Login Sebagai Admin");
            System.out.println("3. keluar");
            System.out.print("Opsi Anda (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    inputNim(scanner);
                    break;
                case "2":
                    Admin admin = new Admin();
                    admin.menuAdmin(scanner);
                    break;
                case "3":
                    System.out.println("Terima kasih. Keluar program.");
                    System.exit(0);
                default:
                    System.out.println("Opsi gagal");
            }
        }
    }

    public static void inputNim(Scanner scanner) {
        System.out.print("Masukkan Nim anda (input 11 untuk kembali): ");
        String nim = scanner.nextLine();
        if (nim.equals("11")) {
            return;
        } else if (checkNim(nim)) {
            Student student = new Student(nim);
            student.menuStudent(scanner); // Panggil menuStudent dari objek student yang sudah dibuat
        } else {
            System.out.println("Nim yang anda masukkan salah. Mohon coba lagi.");
            inputNim(scanner);
        }
    }

    public static boolean checkNim(String nim) {
        for (Student student : userStudent) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }

    public static void initializeData() {
        // Inisialisasi data buku
        bookList.add(new Book("k99c-774k-7364", "judul1", "penulis1", "MTK", 5));
        bookList.add(new Book("e746-83yl-0cdb", "judul2", "penulis2", "IPA", 2));
        bookList.add(new Book("j747-64u5-8474", "judul3", "penulis3", "SBY", 8));

        // Inisialisasi data user
        userStudent.add(new Student("Ican", "202310370311171", "Teknik", "Informatika"));
        userStudent.add(new Student("Adudu", "202310370311203", "Teknik", "Informatika"));
    }

}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int loanDuration;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getStock() { return stock; }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getLoanDuration() { return loanDuration; }
    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration; }
}

class User {
    protected static ArrayList<Book> bookList = new ArrayList<>();

    public void displayBooks() {
        System.out.println("Daftar buku:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Penulis || Katagori || Stok ||");
        int index = 1;
        for (Book book : bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
}
class Student extends User{
    private String name;
    private String nim;
    private String faculty;
    private String program;
    private ArrayList<Book> borrowedBooks;
    private int loanDuration;



    // Konstruktor dengan empat argumen
    public Student(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
        this.borrowedBooks = new ArrayList<>();
        // this.loanDuration = loanDuration;
    }

    // Konstruktor dengan satu argumen
    public Student(String nim) {
        // Temukan detail mahasiswa dari Main.userStudent berdasarkan nim yang diberikan
        for (Student student : tugas.userStudent) {
            if (student.getNim().equals(nim)) {
                this.name = student.getName();
                this.nim = student.getNim();
                this.faculty = student.getFaculty();
                this.program = student.getProgram();
                this.borrowedBooks = student.getBorrowedBooks();
                break;
            }
        }
    }

    // Metode untuk menambah buku yang dipinjam

    // Metode lainnya
    public String getNim() { return nim; }

    public void menuStudent(Scanner scanner) {
        while (true) {
            System.out.println("=== Student Menu ===");
            System.out.println("1. Buku terpinjam");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Logout");
            System.out.print("Choose option (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Buku terpinjam:");
                    displayBorrowedBooks();
                    // Implementasi logika untuk menampilkan buku yang sedang dipinjam
                    break;
                case "2":
                    displayBooks();
                    borrowBook(scanner);
                    // Implementasi logika untuk pinjam buku
                    break;
                case "3":
                    System.out.println("Keluar System...");
                    return;
                default:
                    System.out.println("Opsi gagal.");
            }
        }
    }
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // Metode untuk mengembalikan buku yang dipinjam
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Buku yang dipinjam.");
        } else {
            System.out.println("=================================================================================");
            System.out.println("|| No. || Id Buku || Nama Buku || Penulis || Katagori || Durasi ||");
            System.out.println("=================================================================================");
            int index = 1;
            for (Book book : borrowedBooks) {
                System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + " || " + book.getLoanDuration() + " ||" );
                index++;
            }
            System.out.println("=================================================================================");
        }
    }

    public void displayBooks() {
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Penulis || Kategori || Stok ||");
        int index = 1;
        for (Book book : tugas.bookList) {
            System.out.println("|| " + index + " || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
    public void borrowBook(Scanner scanner) {
        System.out.print("Masukkan nomor buku yang ingin di pinjam: ");
        int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (bookIndex < 0 || bookIndex >= tugas.bookList.size()) {
            System.out.println("ID buku tidak di temukan.");
            return;
        }

        Book selectedBook = tugas.bookList.get(bookIndex);
        System.out.print("Masukkan durasi pinjam (hari): ");
        int loanDuration = Integer.parseInt(scanner.nextLine());

        if (selectedBook.getStock() > 0) {
            // Decrease the stock of the selected book
            selectedBook.setStock(selectedBook.getStock() - 1);
            selectedBook.setLoanDuration(loanDuration); // Set loan duration for the book
            borrowBook(selectedBook);
            System.out.println(" Buku " + selectedBook.getTitle() + " Berhasil dipinjam selama " + loanDuration + " Hari.");
        } else {
            System.out.println("Maaf, stok buku yang anda pilih habis.");
        }
    }

    // Di kelas Book, tambahkan setter untuk loanDuration
    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    // Di kelas Book, tambahkan getter untuk loanDuration
    public int getLoanDuration() {
        return loanDuration;
    }
    public String getName() { return name; }
    public String getFaculty() { return faculty; }
    public String getProgram() { return program; }
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }
}
class Admin extends User{
    // Tambahkan informasi username dan password admin
    private static final String ADMIN = "admin";
    private static final String PASSWORD = "130703";
    public void menuAdmin(Scanner scanner) {
        if (loginAdmin(scanner)) {
            while (true) {
                System.out.println("=== Menu Admin ===");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Lihat Daftar Mahasiswa");
                System.out.println("3. Tambah Buku");
                System.out.println("4. Lihat Daftar Buku");
                System.out.println("5. Keluar");
                System.out.print("Choose option (1-5): ");
                String option = scanner.nextLine();
                switch (option) {
                    case "1":
                        addStudent(scanner);
                        break;
                    case "2":
                        displayStudent();
                        break;
                    case "3":
                        addBook(scanner);
                        break;
                    case "4":
                        displayBookList();
                        break;
                    case "5":
                        System.out.println("Keluar dari akun admin.");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
        }else {
            System.out.println("Login admin gagal. Keluar.");
        }
    }
    // Metode untuk melakukan autentikasi admin
    private boolean loginAdmin(Scanner scanner) {
        System.out.print("Masukkan Username Admin: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password Admin: ");
        String password = scanner.nextLine();

        // Periksa apakah username dan password sesuai dengan yang diharapkan
        return username.equals(ADMIN) && password.equals(PASSWORD);
    }

    public void addStudent(Scanner scanner) {
        System.out.println("Masukkan data mahasiswa:");
        System.out.print("Masukkan nama: ");
        String name = scanner.nextLine();
        String nim;
        do {
            System.out.print("Masukkan Nim: ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("Nim harus 15 digit.");
            }
        } while (nim.length() != 15);
        System.out.print("Masukkan fakultas: ");
        String faculty = scanner.nextLine();
        System.out.print("Masukkan prodi: ");
        String program = scanner.nextLine();
        tugas.userStudent.add(new Student(name, nim, faculty, program));
        System.out.println("Berhasil di daftar.");
    }

    public void displayStudent() {
        System.out.println("Daftar siswa terdaftar:");
        for (Student student : tugas.userStudent) {
            System.out.println("Nama: " + student.getName());
            System.out.println("Fakultas: " + student.getFaculty());
            System.out.println("NIM: " + student.getNim());
            System.out.println("Prodi: " + student.getProgram());
            System.out.println();
        }
    }
    public void addBook(Scanner scanner) {
        System.out.println("Pilih kategori buku:");
        System.out.println("1. Buku Matematika");
        System.out.println("2. Buku Ilmu Pengetahuan Alam");
        System.out.println("3. Buku Seni Budaya");
        System.out.println("4. Keluar");
        System.out.print("Masukkan pilihan anda (1-4): ");
        String categoryChoice = scanner.nextLine();

        String category;
        switch (categoryChoice) {
            case "1":
                category = "Buku Matematika";
                break;
            case "2":
                category = "Buku Ilmu Pengetahuan Alam";
                break;
            case "3":
                category = "Buku Seni Budaya";
                break;
            case "4":
                System.out.println("Keluar System...");
                return;
            default:
                System.out.println("Pilihan gagal. Defaul ke Buku Matematika.");
                category = "Buku Matematika";
                break;
        }
        System.out.println("Masukkan rincian buku:");
        System.out.print("Masukkan ID Buku: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Judul Buku: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan Penulis Buku: ");
        String author = scanner.nextLine();
        System.out.print("Masukkan Stok Buku: ");
        int stock = Integer.parseInt(scanner.nextLine());

        tugas.bookList.add(new Book(id, title, author, category, stock));
        System.out.println("Buku berhasil di tambah di perpustakaan.");
    }
    public void displayBookList() {
        System.out.println("Daftar buku:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Penulis || kategori || Stok ||");
        int index = 1;
        for (Book book : tugas.bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
}
