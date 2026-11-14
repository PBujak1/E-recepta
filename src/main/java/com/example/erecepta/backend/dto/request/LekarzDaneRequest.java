package com.example.erecepta.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LekarzDaneRequest {

    private String pesel;
    private String imie;
    private String nazwisko;
    private String nowyPesel;
    private String adres;
    private String telefon;
    private String email;
    private String wiek;
    private String plec;

    public LekarzDaneRequest() {
    }

    public LekarzDaneRequest(String pesel) {
        this.pesel = pesel;
    }

    public LekarzDaneRequest(String pesel, String imie, String nazwisko, String nowyPesel,
                             String adres, String telefon, String email, String wiek, String plec) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nowyPesel = nowyPesel;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
        this.wiek = wiek;
        this.plec = plec;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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

    public String getNowyPesel() {
        return nowyPesel;
    }

    public void setNowyPesel(String nowyPesel) {
        this.nowyPesel = nowyPesel;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWiek() {
        return wiek;
    }

    public void setWiek(String wiek) {
        this.wiek = wiek;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }
}
