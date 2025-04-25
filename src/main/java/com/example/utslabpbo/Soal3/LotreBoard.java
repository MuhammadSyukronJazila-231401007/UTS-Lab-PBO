package com.example.utslabpbo.Soal3;

import java.util.Random;

public class LotreBoard {
    private char[][] board = new char[4][5];
    private boolean[][] revealed = new boolean[4][5];
    private int[][] data = new int[4][5];

    // Method untuk menghasilkan angka acak untuk X (baris)
    int randomX() {
        Random random = new Random();
        return random.nextInt(4); // 0 sampai 3
    }

    // Method untuk menghasilkan angka acak untuk Y (kolom)
    int randomY() {
        Random random = new Random();
        return random.nextInt(5); // 0 sampai 4
    }

    void generateBoard() {
        // Mengisi papan dengan aman (0) dan bom (1)
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = 0; // Semua kotak aman
                revealed[i][j] = false; // Semua kotak belum dibuka
            }
        }

        // Menetapkan 2 bom secara acak
        int jumlahTetapkanBom = 0;
        while (jumlahTetapkanBom < 2) {
            int x = randomX();
            int y = randomY();
//            System.out.println("X : "+x+" Y : "+y);

            // Pastikan tidak ada bom di lokasi yang sudah ada
            if (data[x][y] == 0) {
                data[x][y] = 1;
                jumlahTetapkanBom++;
            }
        }
    }

    void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (revealed[i][j]) {
                    // Tampilkan kotak yang sudah dibuka (O untuk aman, X untuk bom)
                    if (data[i][j] == 1) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    System.out.print("* "); // Kotak yang belum dibuka
                }
            }
            System.out.println();
        }
    }

    boolean guess(int row, int col) {
        // Menandai kotak yang sudah dibuka
        revealed[row][col] = true;

        // Jika kotak berisi bom
        if (data[row][col] == 1) {
            return false;
        }
        return true;
    }

    boolean isGameOver() {
        int countRevealed = 0;
        // Mengecek apakah semua kotak aman sudah dibuka
        for (int i = 0; i < revealed.length; i++) {
            for (int j = 0; j < revealed[i].length; j++) {
                if (revealed[i][j] && data[i][j] == 0) {
                    countRevealed++;
                }
            }
        }
        return countRevealed == 18;
    }
}
