package org.example;

import java.util.Scanner;

class LibrarySystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;

        do {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    loginAsStudent();
                    break;
                case 2:
                    loginAsAdmin();
                    break;
                case 3:
                    System.out.println("adios");
                    break;
                default:
                    System.out.println("Invalid option! Please choose between 1 to 3.");
            }
        } while (option != 3);

        scanner.close();
    }

    static void loginAsStudent() {
        System.out.print("Enter your NIM: ");
        String nim = scanner.nextLine();

        if (nim.equals("202210370311203")) {
            System.out.println("Successful Login as Student Library System");
        } else {
            System.out.println("User Not Found");
        }
    }

    static void loginAsAdmin() {
        System.out.print("Enter your username (admin): ");
        String username = scanner.nextLine();
        System.out.print("Enter your password (admin): ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Successful Login as Admin");
        } else {
            System.out.println("Admin User Not Found!!");
        }
    }
}
