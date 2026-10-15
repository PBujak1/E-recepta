package com.example.erecepta.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LekarzResponse {
    private String imie;
    private String NrPzw;
    private String Email;
    private String NumerTelefonu;
    private String Opis;
    private String Pesel;
    private String nazwisko;
    private String specjalizacja;

    public LekarzResponse(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public LekarzResponse() {
    }

    public LekarzResponse(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public LekarzResponse(String NrPzw, String Email, String NumerTelefonu, String Opis, String Pesel) {
        this.NrPzw = NrPzw;
        this.Email = Email;
        this.NumerTelefonu = NumerTelefonu;
        this.Opis = Opis;
        this.Pesel = Pesel;
    }

    public String getNrPzw() {
        return NrPzw;
    }

    public void setNrPzw(String nrPzw) {
        NrPzw = nrPzw;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNumerTelefonu() {
        return NumerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        NumerTelefonu = numerTelefonu;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public String getPesel() {
        return Pesel;
    }

    public void setPesel(String pesel) {
        Pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Object getNazwa() {
        return imie + " " + nazwisko;
    }

    public String getSpecjalizacja() {return specjalizacja;}

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }
}
