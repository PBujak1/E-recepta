package com.example.erecepta.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LekarzResponse {
    private String imie;
    private String nazwisko;
    private String specjalizacja;

    public LekarzResponse() {
    }

    public LekarzResponse(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public LekarzResponse(String specjalizacja) {
        this.specjalizacja = specjalizacja;
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
