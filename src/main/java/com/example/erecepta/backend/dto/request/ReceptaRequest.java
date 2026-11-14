package com.example.erecepta.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceptaRequest {
    @JsonIgnore
    private Integer peselPacjenta;
    private Integer peselLekarza;
    private String nazwaLeku;
    private Integer liczbaOpakowan;
    private Integer odplatnosc;
    private Integer dawkowanie;

    public ReceptaRequest() {
    }

    public ReceptaRequest(Integer peselLekarza, Integer liczbaOpakowan, String nazwaLeku, Integer odplatnosc, Integer dawkowanie) {
        this.peselLekarza = peselLekarza;
        this.liczbaOpakowan = liczbaOpakowan;
        this.nazwaLeku = nazwaLeku;
        this.odplatnosc = odplatnosc;
        this.dawkowanie = dawkowanie;
    }

    public ReceptaRequest(Integer idLekarza, Integer peselPacjenta, String nazwaLeku, Integer liczbaOpakowan, Integer odplatnosc, Integer dawkowanie) {
        this.peselLekarza = idLekarza;
        this.peselPacjenta = peselPacjenta;
        this.nazwaLeku = nazwaLeku;
        this.liczbaOpakowan = liczbaOpakowan;
        this.odplatnosc = odplatnosc;
        this.dawkowanie = dawkowanie;
    }

    public Integer getPeselLekarza() {
        return peselLekarza;
    }

    public void setPeselLekarza(Integer peselLekarza) {
        this.peselLekarza = peselLekarza;
    }

    public Integer getPeselPacjenta() {
        return peselPacjenta;
    }

    public void setPeselPacjenta(Integer peselPacjenta) {
        this.peselPacjenta = peselPacjenta;
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
