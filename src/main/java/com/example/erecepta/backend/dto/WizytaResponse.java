package com.example.erecepta.backend.dto;

public class WizytaResponse {

    private String dataWizyty;

    public WizytaResponse() {
    }

    public WizytaResponse(String dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public String getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(String dataWizyty) {
        this.dataWizyty = dataWizyty;
    }
}