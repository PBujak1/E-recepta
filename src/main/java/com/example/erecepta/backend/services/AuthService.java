package com.example.erecepta.backend.services;

import com.example.erecepta.backend.dto.LoginRequest;
import com.example.erecepta.backend.dto.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService {

    private final HttpClient client = HttpClient.newHttpClient();

    private final ObjectMapper mapper = new ObjectMapper();

    public LoginResponse login(String nazwisko, String pesel, int mode) throws Exception {

        LoginRequest requestBody = new LoginRequest();
        requestBody.setNazwisko(nazwisko);
        requestBody.setPESEL(pesel);

        String json = mapper.writeValueAsString(requestBody);

        System.out.println("Wysyłany JSON: " + json);

        String endpoint = (mode == 1) ? "/api/login/pacjent" : "/api/login/lekarz";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080" + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("STATUS: " + response.statusCode());
        System.out.println("BODY: " + response.body());

        return mapper.readValue(response.body(), LoginResponse.class);
    }
}

