package com.example.erecepta.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceptaRequest {

    private String idLekarza;
    private String peselPacjenta;
    private String nazwaLeku;
    private String liczbaOpakowan;
    private String odplatnosc;
    private String dawkowanie;

    public ReceptaRequest() {
    }

    public ReceptaRequest(String idLekarza, String peselPacjenta, String nazwaLeku, String liczbaOpakowan, String odplatnosc, String dawkowanie) {
        this.idLekarza = idLekarza;
        this.peselPacjenta = peselPacjenta;
        this.nazwaLeku = nazwaLeku;
        this.liczbaOpakowan = liczbaOpakowan;
        this.odplatnosc = odplatnosc;
        this.dawkowanie = dawkowanie;
    }

    public String getIdLekarza() {
        return idLekarza;
    }

    public void setIdLekarza(String idLekarza) {
        this.idLekarza = idLekarza;
    }

    public String getPeselPacjenta() {
        return peselPacjenta;
    }

    public void setPeselPacjenta(String peselPacjenta) {
        this.peselPacjenta = peselPacjenta;
    }

    public String getNazwaLeku() {
        return nazwaLeku;
    }

    public void setNazwaLeku(String nazwaLeku) {
        this.nazwaLeku = nazwaLeku;
    }

    public String getLiczbaOpakowan() {
        return liczbaOpakowan;
    }

    public void setLiczbaOpakowan(String liczbaOpakowan) {
        this.liczbaOpakowan = liczbaOpakowan;
    }

    public String getOdplatnosc() {
        return odplatnosc;
    }

    public void setOdplatnosc(String odplatnosc) {
        this.odplatnosc = odplatnosc;
    }

    public String getDawkowanie() {
        return dawkowanie;
    }

    public void setDawkowanie(String dawkowanie) {
        this.dawkowanie = dawkowanie;
    }
}
