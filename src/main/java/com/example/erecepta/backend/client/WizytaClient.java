package com.example.erecepta.backend.client;

import com.example.erecepta.backend.dto.WizytaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class WizytaClient {

    private final ApiClient apiClient;
    private final ObjectMapper mapper;

    public WizytaClient(ApiClient apiClient) {
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
}