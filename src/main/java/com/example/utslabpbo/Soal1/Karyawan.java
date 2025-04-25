package com.example.utslabpbo.Soal1;

public class Karyawan {
    private String id;
    private String nama;
    private String posisi;
    private Double gaji;
    private String kelasPerusahaan;
    private String jenisKelamin;

    public Karyawan(String id, String nama, String posisi, Double gaji, String kelasPerusahaan, String jk) {
        setId(id);
        setNama(nama);
        setPosisi(posisi);
        setGaji(gaji);
        setKelasPerusahaan(kelasPerusahaan);
        setJenisKelamin(jk);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public Double getGaji() {
        return gaji;
    }

    public void setGaji(Double gaji) {
        if(gaji >= 0){
            this.gaji = gaji;
        }
    }

    public String getKelasPerusahaan() {
        return kelasPerusahaan;
    }

    public void setKelasPerusahaan(String kelasPerusahaan) {
        this.kelasPerusahaan = kelasPerusahaan;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
