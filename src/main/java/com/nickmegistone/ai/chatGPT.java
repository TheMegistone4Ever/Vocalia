package com.nickmegistone.ai;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class chatGPT {
    private static final String URL_GPT_TURBO = "https://api.openai.com/v1/chat/completions";
    private static final String REQUEST_BODY_GPT_TURBO = """
            {
                "model": "gpt-3.5-turbo",
                "messages": [{"role": "user", "content": "%s"}],
                "temperature": 0.7,
                "max_tokens": 100
            }
            """;

    public static void main(String[] args) throws IOException, InterruptedException {
        String postBody = REQUEST_BODY_GPT_TURBO.formatted("Say 'Hello world' on 5 languages!");
        var request = HttpRequest.newBuilder()
                .uri(URI.create(URL_GPT_TURBO))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer API")
                .POST(HttpRequest.BodyPublishers.ofString(postBody))
                .build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
