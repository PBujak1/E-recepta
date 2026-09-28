package com.example.erecepta.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoriaPacjentaResponse {
    private String dataWizyty;
    private String nazwaLekarza;
    private String nazwaPacjenta;
    private String nrRecepty;

    public HistoriaPacjentaResponse() {
    }

    public HistoriaPacjentaResponse(
            String dataWizyty,
            String nazwaLekarza,
            String nazwaPacjenta,
            String nrRecepty
    ) {
        this.dataWizyty = dataWizyty;
        this.nazwaLekarza = nazwaLekarza;
        this.nazwaPacjenta = nazwaPacjenta;
        this.nrRecepty = nrRecepty;
    }

    public String getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(String dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public String getNazwaLekarza() {
        return nazwaLekarza;
    }

    public void setNazwaLekarza(String nazwaLekarza) {
        this.nazwaLekarza = nazwaLekarza;
    }

    public String getNazwaPacjenta() {
        return nazwaPacjenta;
    }

    public void setNazwaPacjenta(String nazwaPacjenta) {
        this.nazwaPacjenta = nazwaPacjenta;
    }

    public String getNrRecepty() {
        return nrRecepty;
    }

    public void setNrRecepty(String nrRecepty) {
        this.nrRecepty = nrRecepty;
    }
}
