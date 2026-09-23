package com.example.erecepta.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacjentResponse {

    @JsonProperty("imie")
    private String imieLekarza;

    @JsonProperty("nazwisko")
    private String nazwiskoLekarza;
    private String nazwaLekarza;

    public PacjentResponse() {
    }

    public PacjentResponse(String nazwaLekarza) {
        this.nazwaLekarza = nazwaLekarza;
    }

    public String getImieLekarza() {
        return imieLekarza;
    }

    public void setImieLekarza(String imieLekarza) {
        this.imieLekarza = imieLekarza;
    }

    public String getNazwiskoLekarza() {
        return nazwiskoLekarza;
    }

    public void setNazwiskoLekarza(String nazwiskoLekarza) {
        this.nazwiskoLekarza = nazwiskoLekarza;
    }

    public String getNazwaLekarza() {
        return nazwaLekarza;
    }

    public void setNazwaLekarza(String nazwaLekarza) {
        this.nazwaLekarza = nazwaLekarza;
    }
}
