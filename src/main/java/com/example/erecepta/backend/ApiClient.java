package com.example.erecepta.backend;

import java.net.http.HttpClient;

public class ApiClient {
    private static final String BaseURL = "http://localhost:8080/api";
    private final HttpClient client = HttpClient.newHttpClient();

    public HttpClient getClient() { return client; }

    public String getBaseURL() { return BaseURL; }
}
