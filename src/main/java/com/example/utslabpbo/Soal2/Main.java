package com.example.utslabpbo.Soal2;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

        System.out.println("======= Welcome to ParkingChan =======");

        boolean lanjut = true;
        while (lanjut) {
            System.out.print("\nEnter vehicle type (Motor/Mobil/Truk) : ");
            String jenis = input.nextLine();

            Kendaraan kendaraan = new Kendaraan(jenis);

            System.out.print("Enter Duration (Manual/Time): ");
            String metode = input.nextLine();

            if (metode.equalsIgnoreCase("Manual")) {
                System.out.print("Enter Duration (in hour): ");
                int jam = input.nextInt();
                kendaraan.setDurasi(jam);
            } else {
                System.out.print("Enter entry time : ");
                int masuk = input.nextInt();
                System.out.print("Enter exit time  : ");
                int keluar = input.nextInt();
                kendaraan.setDurasi(masuk, keluar);
            }
            input.nextLine(); // flush newline

            System.out.println("");
            kendaraan.tampilkanRingkasan();
            daftarKendaraan.add(kendaraan);

            System.out.print("\nAdd another vehicle? (y/n): ");
            String again = input.nextLine();
            if (!again.equalsIgnoreCase("y")) {
                lanjut = false;
            }
        }

        // Ringkasan akhir
        int totalKendaraan = daftarKendaraan.size();
        double totalBiaya = 0;
        for (Kendaraan k : daftarKendaraan) {
            totalBiaya += k.hitungBiaya();
        }

        System.out.println("\n======= FINAL REPORT =======");
        System.out.println("Total Vehicle Final      : " + totalKendaraan);
        System.out.println("Total Parking Fees Final : Rp" + totalBiaya);
        System.out.println("Thank You.....");
    }
}
