package com.example.tasarimcalismasi.model;

public class Malzeme {

    public String malzemeAdi;
    public boolean isSelected;

    public Malzeme(String malzemeAdi) {
        this.malzemeAdi = malzemeAdi;
    }

    public String getMalzemeAdi() {
        return malzemeAdi;
    }

    public void setMalzemeAdi(String malzemeAdi) {
        this.malzemeAdi = malzemeAdi;
    }
    public boolean isSelected() {

        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
