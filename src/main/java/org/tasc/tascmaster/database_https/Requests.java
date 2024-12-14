package org.tasc.tascmaster.database_https;

import org.json.JSONObject;
import org.tasc.tascmaster.Models.UserModel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requests {

    private static final String PROJECT_API_ENDPOINT = "https://ijbzjqufdsmvcemiperl.supabase.co";
    private static final String ANON_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImlqYnpqcXVmZHNtdmNlbWlwZXJsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzM5Njg0NjksImV4cCI6MjA0OTU0NDQ2OX0.lsahe3RrWpqGQAmES91-jgxfyqZ7G98uJVSNcL_-MLY";

    public static HttpRequest buildLoginRequest(String email, String password){

        // Create the JSON payload
        JSONObject payload = new JSONObject();
        payload.put("email", email);
        payload.put("password", password);

        // Build the HTTP request

        return HttpRequest.newBuilder()
                .uri(URI.create(PROJECT_API_ENDPOINT + "/auth/v1/token?grant_type=password"))
                .header("Content-Type", "application/json")
                .header("apikey", ANON_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();
    }

    public static HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpRequest buildFullTableRequest(String table){
        return HttpRequest.newBuilder()
                .uri(URI.create(PROJECT_API_ENDPOINT + "/rest/v1/" + table))
                .header("Authorization", "Bearer " + UserModel.getInstance().getAccessToken())
                .header("apikey", ANON_API_KEY)
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }



}
