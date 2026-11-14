package com.example.erecepta.backend.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api";
    private final HttpClient client = HttpClient.newHttpClient();

    public String get(String endpoint) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("GET: " + BASE_URL + endpoint);
        System.out.println("STATUS: " + response.statusCode());
        System.out.println("BODY: " + response.body());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException(
                    "Błąd HTTP " + response.statusCode() + ": " + response.body()
            );
        }

        return response.body();
    }

    public String post(String endpoint, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody != null ? jsonBody : ""))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("POST: " + BASE_URL + endpoint);
        System.out.println("STATUS: " + response.statusCode());
        System.out.println("BODY: " + response.body());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException(
                    "Błąd HTTP " + response.statusCode() + ": " + response.body()
            );
        }

        return response.body();
    }

    public String patch(String endpoint, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonBody != null ? jsonBody : ""))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("PATCH: " + BASE_URL + endpoint);
        System.out.println("STATUS: " + response.statusCode());
        System.out.println("BODY: " + response.body());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException(
                    "Błąd HTTP " + response.statusCode() + ": " + response.body()
            );
        }

        return response.body();
    }

    public String put(String endpoint, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody != null ? jsonBody : ""))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("PUT: " + BASE_URL + endpoint);
        System.out.println("STATUS: " + response.statusCode());
        System.out.println("BODY: " + response.body());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException(
                    "Błąd HTTP " + response.statusCode() + ": " + response.body()
            );
        }

        return response.body();
    }

    public HttpClient getClient() {
        return client;
    }

    public String getBaseURL() {
        return BASE_URL;
    }
}
