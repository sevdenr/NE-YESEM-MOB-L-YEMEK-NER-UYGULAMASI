package com.example.tasarimcalismasi.model;

public class Tarif {
    public String tarifAdi;
    public String yemekFoto;
    public Tarif(String tarifAdi, String yemekFoto) {
        this.tarifAdi = tarifAdi;
        this.yemekFoto = yemekFoto;
    }
    public String getTarifAdi() {
        return tarifAdi;
    }
}
