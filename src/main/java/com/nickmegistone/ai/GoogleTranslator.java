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

/**
 * This class represents a Google Translator that can translate text from one language to another.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class GoogleTranslator {

    private final String deployId;

    /**
     * Constructs a GoogleTranslator object with the specified deploy ID.
     *
     * @param deployId The deploy ID for the Google Apps Script web application.
     */
    public GoogleTranslator(String deployId) {
        this.deployId = deployId;
    }

    /**
     * Translates the given text from the source language to the target language.
     *
     * @param langFrom The source language code.
     * @param langTo   The target language code.
     * @param text     The text to be translated.
     * @return         The translated text.
     */
    public @NotNull String translate(String langFrom, String langTo, String text) {
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    String.format("https://script.google.com/macros/s/%s/exec?q=%s&target=%s&source=%s",
                            deployId,
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
