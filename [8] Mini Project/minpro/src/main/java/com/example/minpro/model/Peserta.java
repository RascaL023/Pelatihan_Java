package com.example.minpro.model;

public class Peserta {
    private String nama, email, hp;

    public Peserta(String nama, String email, String hp){
        this.nama = nama;
        this.email = email;
        this.hp = hp;
    }
    
    public String getNama(){ return nama; }
    public String getEmail(){ return email; }
    public String getHp(){ return hp; }

    public void setNama(String nama){ this.nama = nama; }
    public void setEmail(String email){ this.email = email; }
    public void setHp(String hp){ this.hp = hp; }
}
