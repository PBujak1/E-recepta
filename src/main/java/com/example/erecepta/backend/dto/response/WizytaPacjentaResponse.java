package com.example.erecepta.backend.dto.response;

public class WizytaPacjentaResponse {

    private String dataWizyty;

    public WizytaPacjentaResponse() {
    }

    public WizytaPacjentaResponse(String dataWizyty) {
        this.dataWizyty = dataWizyty;
    }

    public String getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(String dataWizyty) {
        this.dataWizyty = dataWizyty;
    }
}