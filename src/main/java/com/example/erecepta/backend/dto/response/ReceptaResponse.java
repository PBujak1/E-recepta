package com.example.erecepta.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceptaResponse {

    @JsonProperty("idRecepty")
    @JsonAlias({"id", "IDRecepty", "nrRecepty", "NrRecepty"})
    private String idRecepty;

    @JsonProperty("nazwaLeku")
    @JsonAlias({"nazwa", "NazwaLeku", "lek", "Lek"})
    private String nazwaLeku;

    @JsonProperty("idPacjenta")
    @JsonAlias({"IDPacjenta", "pacjentId", "idPacjent"})
    private String idPacjenta;

    @JsonProperty("peselPacjenta")
    @JsonAlias({"pesel", "PESELPacjenta", "PESEL"})
    private String peselPacjenta;

    @JsonProperty("liczbaOpakowan")
    @JsonAlias({"iloscOpakowan", "LiczbaOpakowan", "opakowania", "Opakowania"})
    private String liczbaOpakowan;

    @JsonProperty("odplatnosc")
    @JsonAlias({"Odplatnosc"})
    private String odplatnosc;

    @JsonProperty("dawkowanie")
    @JsonAlias({"Dawkowanie"})
    private String dawkowanie;

    @JsonProperty("dataWystawienia")
    @JsonAlias({"data", "DataWystawienia"})
    private String dataWystawienia;

    public ReceptaResponse() {
    }

    public ReceptaResponse(String rawText) {
        this.nazwaLeku = rawText;
    }

    public ReceptaResponse(String idRecepty, String nazwaLeku, String idPacjenta, String liczbaOpakowan) {
        this.idRecepty = idRecepty;
        this.nazwaLeku = nazwaLeku;
        this.idPacjenta = idPacjenta;
        this.liczbaOpakowan = liczbaOpakowan;
    }

    public ReceptaResponse(String idRecepty, String nazwaLeku, String idPacjenta, String liczbaOpakowan, String odplatnosc, String dawkowanie, String dataWystawienia) {
        this.idRecepty = idRecepty;
        this.nazwaLeku = nazwaLeku;
        this.idPacjenta = idPacjenta;
        this.liczbaOpakowan = liczbaOpakowan;
        this.odplatnosc = odplatnosc;
        this.dawkowanie = dawkowanie;
        this.dataWystawienia = dataWystawienia;
    }

    public String getIdRecepty() {
        return idRecepty;
    }

    public void setIdRecepty(String idRecepty) {
        this.idRecepty = idRecepty;
    }

    public String getNazwaLeku() {
        return nazwaLeku;
    }

    public void setNazwaLeku(String nazwaLeku) {
        this.nazwaLeku = nazwaLeku;
    }

    public String getIdPacjenta() {
        return idPacjenta;
    }

    public void setIdPacjenta(String idPacjenta) {
        this.idPacjenta = idPacjenta;
    }

    public String getPeselPacjenta() {
        return peselPacjenta;
    }

    public void setPeselPacjenta(String peselPacjenta) {
        this.peselPacjenta = peselPacjenta;
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

    public String getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(String dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public String formatRow() {
        StringBuilder sb = new StringBuilder();
        if (idRecepty != null) sb.append(idRecepty).append(" ");
        if (nazwaLeku != null) sb.append(nazwaLeku).append(" ");
        if (idPacjenta != null) sb.append(idPacjenta).append(" ");
        if (liczbaOpakowan != null) sb.append(liczbaOpakowan).append(" ");
        if (odplatnosc != null) sb.append(odplatnosc).append(" ");
        if (dawkowanie != null) sb.append(dawkowanie).append(" ");
        if (dataWystawienia != null) sb.append(dataWystawienia);
        return sb.toString().trim();
    }
}
