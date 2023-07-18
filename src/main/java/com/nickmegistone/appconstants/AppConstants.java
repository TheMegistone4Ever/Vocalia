package com.nickmegistone.appconstants;

import java.awt.*;

public class AppConstants {

    public static final int MENU_SLEEP_MILLIS = 1400;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
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

    private AppConstants() {}
}
