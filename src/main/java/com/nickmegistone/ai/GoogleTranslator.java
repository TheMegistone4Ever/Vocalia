package com.nickmegistone.ai;

import static com.nickmegistone.apputils.AppUtils.getUrlContent;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * The GoogleTranslator class represents a translator that can translate text from one language to another using
 * Google Apps Script. It utilizes the Google Apps Script web application deployed with a specific deploy ID.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class GoogleTranslator {

    private final String deployId;

    /**
     * Constructs a GoogleTranslator object with the specified deploy ID.
     *
     * @param deployId The deployment ID for the Google Apps Script web application.
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
        return getUrlContent(
                String.format(
                        "https://script.google.com/macros/s/%s/exec?q=%s&target=%s&source=%s",
                        deployId,
                        URLEncoder.encode(text, StandardCharsets.UTF_8),
                        langTo,
                        langFrom
                )
        );
    }
}
