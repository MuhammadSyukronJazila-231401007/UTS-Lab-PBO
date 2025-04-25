package com.example.utslabpbo.Soal2;

public class Kendaraan {
    private String jenis;
    private int lamaParkir;
    private double tarifPerJam;

    public Kendaraan(String jenis) {
        this.jenis = jenis;
        this.tarifPerJam = getTarifPerJam(jenis);
    }

    // Input durasi secara langsung
    public void setDurasi(int jam) {
        this.lamaParkir = jam;
    }

    // Input jam masuk dan keluar
    public void setDurasi(int jamMasuk, int jamKeluar) {
        this.lamaParkir = jamKeluar - jamMasuk;
    }

    private double getTarifPerJam(String jenis) {
        switch (jenis.toLowerCase()) {
            case "motor":
                return 2000;
            case "mobil":
                return 5000;
            case "truk":
                return 9000;
            default:
                return 0;
        }
    }

    public double hitungBiaya() {
        double total = lamaParkir * tarifPerJam;
        if (lamaParkir > 5) {
            total *= 0.9; // diskon 10%
        }
        return total;
    }

    public void tampilkanRingkasan() {
        System.out.println("---- PARKING SUMMARY ----");
        System.out.println("Vehicle Type   : " + jenis);
        System.out.println("Parking Time   : " + lamaParkir + " hour(s)");
        System.out.println("Total Fee      : Rp" + hitungBiaya());
    }
}
