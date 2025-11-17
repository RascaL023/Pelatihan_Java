package com.example.minpro.model;

public class Kelas {
    private String kode, nama, deskripsi;
    private int kapasitas, jumlahPeserta;
    private double harga;
    
    public Kelas(String kode, String nama, String deskripsi, int kapasitas, double harga){
        this.kode = kode;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.kapasitas = kapasitas;
        this.harga = harga;
        this.jumlahPeserta = 0;
    }

    public String getKode(){ return kode; }
    public String getNama(){ return nama; }
    public String getDeskripsi(){ return deskripsi; }
    public int getKapasitas(){ return kapasitas; }
    public int getJumlahPeserta(){ return jumlahPeserta; }
    public double getHarga(){ return harga; }
    
    public void setKode(String kode){ this.kode = kode; }
    public void setNama(String nama){ this.nama = nama; }
    public void setDeskripsi(String deskripsi){ this.deskripsi = deskripsi; }
    public void setKapasitas(int kapasitas){ this.kapasitas = kapasitas; }
    public void setHarga(double harga){ this.harga = harga; }
    public void addPeserta(){ jumlahPeserta++; }
    public void minPeserta(){ --jumlahPeserta; }
}
