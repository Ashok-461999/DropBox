package com.cloudeagle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DropboxTeamInfo {

    private static final String ACCESS_TOKEN = "";
    public static void main(String[] args) {
        DropboxTeamInfo dropboxClient = new DropboxTeamInfo();
        dropboxClient.fetchTeamInfo();
    }

    public void fetchTeamInfo() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TEAM_INFO_URL))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body:\n" + responseBody);

            if (statusCode != 200) {
               System.err.println(" Failed to fetch team info. Status: " + statusCode);
            }

        } catch (IOException e) {
            System.err.println(" I/O error while calling Dropbox API: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Request was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); 
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
