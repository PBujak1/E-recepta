package com.example.erecepta.backend.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api";

    private final HttpClient client = HttpClient.newHttpClient();

    public HttpClient getClient() {
        return client;
    }

    public String getBaseURL() {
        return BASE_URL;
    }

    public String get(String endpoint) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("GET: " + BASE_URL + endpoint);
        System.out.println("STATUS: " + response.statusCode());
        System.out.println("BODY: " + response.body());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException(
                    "Błąd HTTP " + response.statusCode() +
                            ": " + response.body()
            );
        }

        return response.body();
    }
}