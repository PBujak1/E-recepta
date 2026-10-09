package com.example.erecepta.backend.client;

import com.example.erecepta.backend.dto.DawkowanieResponse;
import com.example.erecepta.backend.dto.WizytyResponse;
import com.example.erecepta.backend.dto.PacjentLekarzResponse;
import com.example.erecepta.backend.dto.WizytaPacjentaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class PacjentController {

    private final ApiClient apiClient = new ApiClient();
    private final ObjectMapper mapper;

    public PacjentController() {
        this.mapper = new ObjectMapper();
    }

    public List<WizytaPacjentaResponse> getWizytyPacjenta(String pesel) throws Exception {

        String response = apiClient.get("/pacjent/" + pesel + "/wizyty");

        WizytaPacjentaResponse[] wizyty = mapper.readValue(
                response,
                WizytaPacjentaResponse[].class
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

    public List<WizytyResponse> getHistoriaPacjenta(String pesel) throws Exception {
        String response = apiClient.get("/pacjent/" + pesel + "/historia");
        WizytyResponse[] historiaPacjenta = mapper.readValue(
                response,
                WizytyResponse[].class
        );

        return Arrays.stream(historiaPacjenta)
                .map(historia -> new WizytyResponse(
                        historia.getDataWizyty(),
                        historia.getNazwaLekarza(),
                        historia.getNazwaPacjenta(),
                        historia.getNrRecepty()
                ))
                .toList();
    }

    public List<WizytyResponse> getNadchodzaceWizyty(String pesel) throws Exception {
        String response = apiClient.get("/pacjent/" + pesel + "/nadchodzaceWizyty");
        WizytyResponse[] historiaPacjenta = mapper.readValue(
                response,
                WizytyResponse[].class
        );

        return Arrays.stream(historiaPacjenta)
                .map(historia -> new WizytyResponse(
                        historia.getDataWizyty(),
                        historia.getNazwaLekarza(),
                        historia.getNazwaPacjenta(),
                        historia.getNrRecepty()
                ))
                .toList();
    }
}