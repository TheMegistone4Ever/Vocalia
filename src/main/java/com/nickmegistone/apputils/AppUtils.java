package com.nickmegistone.apputils;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public final class AppUtils {

    public static final int MENU_SLEEP_MILLIS = 1400;
    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 720;
    public static final int THUMB_SIZE = 80;
    public static final int INTERNET_TIMEOUT = 1500;
    public static final Color SEARCH_ENABLED_COLOR = new Color(0, 102, 102);
    public static final Color SEARCH_DISABLED_COLOR = new java.awt.Color(255, 102, 0);
    public static final Color SCROLLBAR_COLOR = new Color(130, 130, 130);
    public static final Color MAIN_BACKGROUND_COLOR = new Color(51, 51, 51);
    public static final Color LIGHT_BACKGROUND_COLOR = new Color(102, 102, 102);
    public static final Color DARK_BACKGROUND_COLOR = new Color(24, 24, 24);
    public static final Color AVATAR_BORDER_TEXT_COLOR = new Color(224, 224, 224);
    public static final String SYNTHESIZER_IS_SPEAKING = "Synthesizer is speaking...";
    public static final String SEND = "Send...";
    public static final String SEARCH_WHEN_CLICKED = "";
    public static final String NO_INTERNET_CONNECTION_SEARCH = String.format("No internet connection, retrying after %,.1f seconds...", INTERNET_TIMEOUT / 1000.0);

    /**
     * Retrieves the content of a URL address.
     *
     * @param urlAddress The URL address from which to retrieve the content.
     * @return The content of the URL as a string.
     */
    public static @NotNull String getUrlContent(String urlAddress) {
        StringBuilder response = new StringBuilder();
        try {
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new URI(
                                    urlAddress
                            ).toURL().openConnection().getInputStream()
                    )
            )) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line).append("\n");
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return URLDecoder.decode(response.toString(), StandardCharsets.UTF_8);
    }
}
