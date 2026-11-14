package com.example.erecepta.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LekItemRequest {

    private String nazwaLeku;
    private Integer liczbaOpakowan;
    private Integer odplatnosc;
    private Integer dawkowanie;

    public LekItemRequest() {
    }

    public LekItemRequest(String nazwaLeku, Integer liczbaOpakowan, Integer odplatnosc, Integer dawkowanie) {
        this.nazwaLeku = nazwaLeku;
        this.liczbaOpakowan = liczbaOpakowan;
        this.odplatnosc = odplatnosc;
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

    public Integer getOdplatnosc() {
        return odplatnosc;
    }

    public void setOdplatnosc(Integer odplatnosc) {
        this.odplatnosc = odplatnosc;
    }

    public Integer getDawkowanie() {
        return dawkowanie;
    }

    public void setDawkowanie(Integer dawkowanie) {
        this.dawkowanie = dawkowanie;
    }
}
