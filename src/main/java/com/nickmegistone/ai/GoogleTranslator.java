package com.nickmegistone.ai;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleTranslator {

    private final String deployID;

    public GoogleTranslator(String deployID) {
        this.deployID = deployID;
    }

    public @NotNull String translate(String langFrom, String langTo, String text) {
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    String.format("https://script.google.com/macros/s/%s/exec?q=%s&target=%s&source=%s",
                            deployID,
                            URLEncoder.encode(text, StandardCharsets.UTF_8),
                            langTo,
                            langFrom
                    )
            ).openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return URLDecoder.decode(response.toString(), StandardCharsets.UTF_8);
    }
}
