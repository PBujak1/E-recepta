package com.example.erecepta.backend.dto;

public class DawkowanieResponse {
    private String nazwaLeku;
    private Integer liczbaOpakowan;
    private Integer dawkowanie;

    public DawkowanieResponse() {
    }

    public DawkowanieResponse(String nazwaLeku, Integer liczbaOpakowan, Integer dawkowanie) {
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

    public Integer getLiczbaOpakowan() {
        return liczbaOpakowan;
    }

    public void setLiczbaOpakowan(Integer liczbaOpakowan) {
        this.liczbaOpakowan = liczbaOpakowan;
    }

    public Integer getDawkowanie() {
        return dawkowanie;
    }

    public void setDawkowanie(Integer dawkowanie) {
        this.dawkowanie = dawkowanie;
    }
}
