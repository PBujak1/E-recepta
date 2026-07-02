package com.example.erecepta.backend;

public class LoginResponse {
    private String imie;
    private String nazwisko;

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() { return imie;}

    public String getNazwisko() { return nazwisko;}
}
