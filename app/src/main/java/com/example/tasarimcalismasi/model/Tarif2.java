package com.example.tasarimcalismasi.model;

public class Tarif2 {
    public String tarifAdi;
    public String yemek2Foto;
    public String malzemeler;



    public String tarif;
    public String sure;
    public String tur;

    public String getYemek2Foto() {
        return yemek2Foto;
    }

    public void setYemek2Foto(String yemek2Foto) {
        this.yemek2Foto = yemek2Foto;
    }
    public String getTarifAdi() {
        return tarifAdi;
    }

    public void setTarifAdi(String tarifAdi) {
        this.tarifAdi = tarifAdi;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public Tarif2(String tarifAdi, String yemek2Foto, String malzemeler, String tarif, String sure, String tur) {
        this.tarifAdi = tarifAdi;
        this.yemek2Foto = yemek2Foto;
        this.malzemeler = malzemeler;
        this.tarif = tarif;
        this.sure = sure;
        this.tur = tur;
    }
}
