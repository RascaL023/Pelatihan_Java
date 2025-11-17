package com.example.minpro.model;

public class DetailKelas {
    private Kelas kelas;
    private Peserta peserta;

    public DetailKelas(Kelas kelas, Peserta peserta) {
        this.kelas = kelas;
        this.peserta = peserta;
        kelas.addPeserta();
    }

    public Peserta getPeserta(){ return peserta; }
    public Kelas getKelas(){ return kelas; }
}
