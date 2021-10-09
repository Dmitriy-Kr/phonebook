package phonebook.services.networkservices;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import phonebook.pojo.transactions.ErrorResponse;
import phonebook.pojo.transactions.StatusResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class NetworkService {
    private HttpClient client;
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T getRequest(String url, Map<String, String> headers, Class<T> serverResponse) /*throws NetworkException*/ {

        ErrorResponse errorResponse;
        client = HttpClient.newBuilder().build();
        HttpResponse<String> response = null;

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .headers(headers.entrySet().stream()
                            .map(header -> new String[]{header.getKey(), header.getValue()})
                            .flatMap(Arrays::stream)
                            .toArray(String[]::new))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            StatusResponse statusResponse = objectMapper.readValue(response.body(),StatusResponse.class);

            if ("ok".equals(statusResponse.getStatus())) {
                return objectMapper.readValue(response.body(), serverResponse);
            } else {
                errorResponse = objectMapper.readValue(response.body(), ErrorResponse.class);
                throw new RuntimeException(errorResponse.getError());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }



    public <T> T postRequest(String url, Map<String, String> headers, Object requestObject, Class<T> serverResponse) {

        client = HttpClient.newBuilder().build();
        HttpResponse<String> response = null;
        try {

            String requestBody = objectMapper.writeValueAsString(requestObject);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .uri(URI.create(url))
                    .headers(headers.entrySet().stream()
                            .map(header -> new String[]{header.getKey(), header.getValue()})
                            .flatMap(Arrays::stream)
                            .toArray(String[]::new))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            StatusResponse statusResponse = objectMapper.readValue(response.body(),StatusResponse.class);

            if ("ok".equals(statusResponse.getStatus())) {
                return objectMapper.readValue(response.body(), serverResponse);
            } else {
                ErrorResponse errorResponse = objectMapper.readValue(response.body(), ErrorResponse.class);
                throw new RuntimeException(errorResponse.getError());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
