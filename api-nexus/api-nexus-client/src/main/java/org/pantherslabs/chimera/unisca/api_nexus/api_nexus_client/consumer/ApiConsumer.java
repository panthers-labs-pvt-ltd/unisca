package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.response.ResponseWrapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApiConsumer {
    private static HttpClient httpClient;
    private final ObjectMapper mapper;

    public ApiConsumer() {
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.mapper = new ObjectMapper();
    }

    private HttpRequest.Builder createRequestBuilder(String url) throws IOException, InterruptedException {
        String accessToken = getToken();
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Accept", "application/json");
    }

    public <T> T get(String url, TypeReference<T> typeReference) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(url)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), typeReference);
    }

    public String post(String url, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(url)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public <T, F> ResponseWrapper<T, F> post(String url, String jsonBody,
                                             TypeReference<T> successTypeReference,
                                             TypeReference<F> failureTypeReference)
            throws IOException, InterruptedException {

        HttpRequest request = createRequestBuilder(url)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        int statusCode = response.statusCode();


        if (statusCode >= 200 && statusCode < 300) {
            if (responseBody == null || responseBody.trim().isEmpty()) {
                return new ResponseWrapper<>(null, null, statusCode); // ✅ empty successful response
            }
            T success = mapper.readValue(responseBody, successTypeReference);
            return new ResponseWrapper<>(success, null, statusCode);
        } else {
            if (responseBody == null || responseBody.trim().isEmpty()) {
                return new ResponseWrapper<>(null, null, statusCode); // ✅ empty error body
            }
            F failure = mapper.readValue(responseBody, failureTypeReference);
            return new ResponseWrapper<>(null, failure, statusCode);
        }
    }


    public String put(String url, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(url)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String delete(String url) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(url)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String getToken() throws IOException, InterruptedException {
        String KEYCLOAK_TOKEN_URL =
                Optional.ofNullable(System.getenv("KEYCLOAK_TOKEN_URL"))
                        .filter(s -> !s.isEmpty())
                        .orElse(System.getProperty("KEYCLOAK_TOKEN_URL"));

        String API_CLIENT =
                Optional.ofNullable(System.getenv("API_CLIENT"))
                        .filter(s -> !s.isEmpty())
                        .orElse(System.getProperty("API_CLIENT"));

        String API_SECRET =
                Optional.ofNullable(System.getenv("API_SECRET"))
                        .filter(s -> !s.isEmpty())
                        .orElse(System.getProperty("API_SECRET"));

        String API_USERNAME =
                Optional.ofNullable(System.getenv("API_USERNAME"))
                        .filter(s -> !s.isEmpty())
                        .orElse(System.getProperty("API_USERNAME"));

        String API_PASSWORD =
                Optional.ofNullable(System.getenv("API_PASSWORD"))
                        .filter(s -> !s.isEmpty())
                        .orElse(System.getProperty("API_PASSWORD"));

        Map<String, String> formData = Map.of(
                "client_id", API_CLIENT,
                "client_secret", API_SECRET,
                "grant_type", "password",
                "username", API_USERNAME,
                "password", API_PASSWORD
        );

        String requestBody = formData.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(KEYCLOAK_TOKEN_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            return extractAccessToken(responseBody);
        } else {
            throw new RuntimeException("Failed to get access token. HTTP Status: " + response.statusCode() + " Response: " + response.body());
        }
    }

    private static String extractAccessToken(String jsonResponse) {
        // Extract the access token from JSON response using simple parsing
        int startIndex = jsonResponse.indexOf("\"access_token\":\"") + 16;
        int endIndex = jsonResponse.indexOf("\"", startIndex);
        return jsonResponse.substring(startIndex, endIndex);
    }
}