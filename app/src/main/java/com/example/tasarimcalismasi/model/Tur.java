package com.example.tasarimcalismasi.model;

public class Tur {
    public String turAdi;
    public String foto;

    public Tur(String turAdi, String foto) {
        this.turAdi = turAdi;
        this.foto=foto;
    }

    public void setTurAdi(String turAdi) {
        this.turAdi = turAdi;
    }

    public String getTurAdi() {
        return turAdi;
    }
}
