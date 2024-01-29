package com.example.tasarimcalismasi.model;

public class Kullanici {
    public String kullaniciAdi;
    public String KullaniciSoyadi;
    public String Email;
    public Kullanici(String kullaniciAdi, String kullaniciSoyadi, String Email) {
        this.kullaniciAdi = kullaniciAdi;
        this.KullaniciSoyadi = KullaniciSoyadi;
        this.Email = Email;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getKullaniciSoyadi() {
        return KullaniciSoyadi;
    }

    public void setKullaniciSoyadi(String kullaniciSoyadi) {
        KullaniciSoyadi = kullaniciSoyadi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
