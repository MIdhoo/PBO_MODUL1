package com.example;

import java.util.Scanner;

public class modul1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("=== Library System ===");

        int option;
        do {
            System.out.println("1. Logind Mahasiswa");
            System.out.println("2. Logind Admin");
            System.out.println("3. Keluar");

            System.out.print("Pilih Opsi Anda (1-3)\t: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Masukkan Nim Anda\t: ");
                    String nim = scan.next();

                    int digitNim = nim.length();

                    if (digitNim != 15){
                        System.out.println("Nim Tidak di Temukan!");
                    } else {
                        System.out.println("Berhasil Logind Sebagai Mahasiswa");
                    }
                    break;
                case 2:
                    System.out.print("Masukkan Username (admin)\t: ");
                    String un = scan.next();

                    System.out.print("Masukkan Password (admin123)\t\t: ");
                    String pass = scan.next();

                    if (un.equals("admin") && pass.equals("admin123")){
                        System.out.println("Berhasil Logind Sebagai Admin");
                    }else {
                        System.out.println("Admin Tidak di Temukan!");
                    }
                    break;
                case 3:
                    System.out.println("Keluar dari Program");
                    break;
                default:
                    System.out.println("Opsi Hanya 1-3");
            }
        } while (option != 3);

        scan.close();
    }
}