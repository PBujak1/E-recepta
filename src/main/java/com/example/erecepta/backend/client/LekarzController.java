package com.example.erecepta.backend.client;

import com.example.erecepta.backend.dto.LekarzResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class LekarzController {
    private final ApiClient apiClient = new ApiClient();
    private final ObjectMapper mapper;

    public LekarzController() {
        this.mapper = new ObjectMapper();
    }

    public List<LekarzResponse> szukajLekarzy(String fraza) throws Exception {
        String encoded = URLEncoder.encode(fraza, StandardCharsets.UTF_8);
        String response = apiClient.get("/pacjent/lekarze/szukaj?fraza=" + encoded + "&limit=10");
        LekarzResponse[] lekarze = mapper.readValue(response, LekarzResponse[].class);

        return Arrays.stream(lekarze)
                .map( lekarzResponse -> new LekarzResponse(
                        lekarzResponse.getImie(),
                        lekarzResponse.getNazwisko()))
                .toList();
    }

    public List<LekarzResponse> getSpecjalizacja(String fraza) throws Exception {
        String encoded = URLEncoder.encode(fraza, StandardCharsets.UTF_8);
        String response = apiClient.get("/pacjent/lekarze/specjalizacja/szukaj?fraza=" + encoded + "&limit=10");
        LekarzResponse[] lekarze = mapper.readValue(response, LekarzResponse[].class);

        return Arrays.stream(lekarze)
                .map( lekarzResponse -> new LekarzResponse(
                        lekarzResponse.getSpecjalizacja()))
                .toList();

    }
}
