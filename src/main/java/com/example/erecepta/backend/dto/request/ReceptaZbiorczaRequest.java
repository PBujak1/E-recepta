package com.example.erecepta.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceptaZbiorczaRequest {

    private String peselLekarza;
    private List<LekItemRequest> leki = new ArrayList<>();

    public ReceptaZbiorczaRequest() {
    }

    public ReceptaZbiorczaRequest(String peselLekarza, List<LekItemRequest> leki) {
        this.peselLekarza = peselLekarza;
        this.leki = leki;
    }

    public String getPeselLekarza() {
        return peselLekarza;
    }

    public void setPeselLekarza(String peselLekarza) {
        this.peselLekarza = peselLekarza;
    }

    public List<LekItemRequest> getLeki() {
        return leki;
    }

    public void setLeki(List<LekItemRequest> leki) {
        this.leki = leki;
    }
}
