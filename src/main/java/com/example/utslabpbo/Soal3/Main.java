package com.example.utslabpbo.Soal3;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LotreBoard lotreBoard = new LotreBoard();

        lotreBoard.generateBoard();

        System.out.println("Selamat datang di permainan Lotre Gosok!");

        // Main game loop
        while (!lotreBoard.isGameOver()) {
            System.out.println();
            lotreBoard.displayBoard();

            System.out.print("Masukkan baris (0-3): ");
            int row = scanner.nextInt();
            System.out.print("Masukkan kolom (0-4): ");
            int col = scanner.nextInt();

            // Pastikan tebakan valid
            if (row < 0 || row >= 4 || col < 0 || col >= 5) {
                System.out.println("Tebakan tidak valid.");
                continue;
            }

            boolean isSafe = lotreBoard.guess(row, col);

            if (!isSafe) {
                System.out.println("Kamu terkena bom! Permainan selesai.");
                break;
            }

            if (lotreBoard.isGameOver()) {
                System.out.println("Selamat! Kamu berhasil membuka semua kotak aman.");
                break;
            }
        }

        lotreBoard.displayBoard();
        scanner.close();
    }
}
