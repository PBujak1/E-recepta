package com.example.erecepta.backend.dto;

public class DawkowanieResponse {
    private String nazwaLeku;
    private String liczbaOpakowan;
    private String dawkowanie;

    public DawkowanieResponse() {
    }

    public DawkowanieResponse(String nazwaLeku, String liczbaOpakowan, String dawkowanie) {
        this.nazwaLeku = nazwaLeku;
        this.liczbaOpakowan = liczbaOpakowan;
        this.dawkowanie = dawkowanie;
    }

    public String getNazwaLeku() {
        return nazwaLeku;
    }

    public void setNazwaLeku(String nazwaLeku) {
        this.nazwaLeku = nazwaLeku;
    }

    public String getLiczbaOpakowan() {return liczbaOpakowan;}

    public void setLiczbaOpakowan(String liczbaOpakowan) {
        this.liczbaOpakowan = liczbaOpakowan;
    }

    public String getDawkowanie() {
        return dawkowanie;
    }

    public void setDawkowanie(String dawkowanie) {
        this.dawkowanie = dawkowanie;
    }
}
