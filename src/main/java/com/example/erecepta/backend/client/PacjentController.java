package com.example.erecepta.backend.client;

import com.example.erecepta.backend.dto.DawkowanieResponse;
import com.example.erecepta.backend.dto.PacjentLekarzResponse;
import com.example.erecepta.backend.dto.WizytaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class PacjentController {

    private final ApiClient apiClient;
    private final ObjectMapper mapper;

    public PacjentController(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.mapper = new ObjectMapper();
    }

    public List<WizytaResponse> getWizytyPacjenta(String pesel) throws Exception {

        String response = apiClient.get("/pacjent/" + pesel + "/wizyty");

        WizytaResponse[] wizyty = mapper.readValue(
                response,
                WizytaResponse[].class
        );

        return Arrays.asList(wizyty);
    }

    public List<PacjentLekarzResponse> getLekarzePacjenta(String pesel) throws Exception {
        String response = apiClient.get("/pacjent/" + pesel + "/lekarzePacjenta");
        PacjentLekarzResponse[] lekarze = mapper.readValue(
                response,
                PacjentLekarzResponse[].class
        );

        return Arrays.stream(lekarze)
                .map(lekarz -> new PacjentLekarzResponse(
                        lekarz.getImieLekarza() + " " + lekarz.getNazwiskoLekarza()
                ))
                .toList();
    }

    public List<DawkowanieResponse> getRecepty(String pesel) throws Exception {
        String response = apiClient.get("/pacjent/" + pesel + "/dawkowanie");
        DawkowanieResponse[] dawkowanie = mapper.readValue(
                response,
                DawkowanieResponse[].class
        );

        return Arrays.stream(dawkowanie)
                .map(dawkowanieResponse -> new DawkowanieResponse(
                        dawkowanieResponse.getNazwaLeku(),
                        dawkowanieResponse.getLiczbaOpakowan(),
                        dawkowanieResponse.getDawkowanie()
                ))
                .toList();
    }
}