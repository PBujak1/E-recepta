package com.example.erecepta.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LekarzRequest {

    private String pesel;
    private String idLekarza;

    public LekarzRequest() {
    }

    public LekarzRequest(String pesel) {
        this.pesel = pesel;
    }

    public LekarzRequest(String pesel, String idLekarza) {
        this.pesel = pesel;
        this.idLekarza = idLekarza;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdLekarza() {
        return idLekarza;
    }

    public void setIdLekarza(String idLekarza) {
        this.idLekarza = idLekarza;
    }
}
