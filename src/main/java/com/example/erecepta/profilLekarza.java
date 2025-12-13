package com.example.erecepta;

public class profilLekarza {
    String login;
    String haslo;
    String imie;
    String nazwisko;

    profilLekarza(String login, String haslo){
        this.login = login;
        this.haslo = haslo;
    }

    void setImie(String imie) {
        this.imie = imie;
    }

    void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    String getImie(){
        return imie;
    }

    String getNazwisko(){
        return nazwisko;
    }
}
