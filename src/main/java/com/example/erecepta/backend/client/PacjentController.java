package com.example.erecepta.backend.client;

import com.example.erecepta.backend.dto.PacjentResponse;
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

        String response = apiClient.get("/login/pacjent/" + pesel + "/wizyty");

        WizytaResponse[] wizyty = mapper.readValue(
                response,
                WizytaResponse[].class
        );

        return Arrays.asList(wizyty);
    }

    public List<PacjentResponse> getLekarzePacjenta(String pesel) throws Exception {
        String response = apiClient.get("/login/pacjent/" + pesel + "/lekarzePacjenta");
        PacjentResponse[] lekarze = mapper.readValue(
                response,
                PacjentResponse[].class
        );

        return Arrays.stream(lekarze)
                .map(lekarz -> new PacjentResponse(
                        lekarz.getImieLekarza() + " " + lekarz.getNazwiskoLekarza()
                ))
                .toList();
    }
}