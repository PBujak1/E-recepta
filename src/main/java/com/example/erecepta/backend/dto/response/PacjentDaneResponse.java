package com.example.erecepta.backend.dto.response;

import java.util.List;

public class PacjentDaneResponse {
    String imie;
    String nazwisko;
    Integer pesel;
    String plec;
    Integer wiek;
    String adres;
    private List<WizytyResponse> wizyty;
    private List<String> alergie;

    public PacjentDaneResponse() {
    }

    public PacjentDaneResponse(String imie, String nazwisko, Integer pesel, Integer wiek, String plec, String adres, List<WizytyResponse> wizyty, List<String> alergie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.wiek = wiek;
        this.plec = plec;
        this.adres = adres;
        this.wizyty = wizyty;
        this.alergie = alergie;
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

    public Integer getPesel() {
        return pesel;
    }

    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public Integer getWiek() {
        return wiek;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public List<String> getAlergie() {
        return alergie;
    }

    public void setAlergie(List<String> alergie) {
        this.alergie = alergie;
    }

    public List<WizytyResponse> getWizyty() {
        return wizyty;
    }

    public void setWizyty(List<WizytyResponse> wizyty) {
        this.wizyty = wizyty;
    }
}
