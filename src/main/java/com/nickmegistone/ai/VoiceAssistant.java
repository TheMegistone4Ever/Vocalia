/*
 * Click nbfs:\\nbhost\\SystemFileSystem\\Templates\\Licenses\\license-default.txt to change this license
 * Click nbfs:\\nbhost\\SystemFileSystem\\Templates\\Classes\\Class.java to edit this template
 */
package com.nickmegistone.ai;

import com.nickmegistone.ai.sphinxextextension.LiveSpeechRecognizerExtension;
import edu.cmu.sphinx.api.Configuration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class represents a voice assistant that can perform various tasks based on voice commands.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public final class VoiceAssistant {

    private final LiveSpeechRecognizerExtension lsr;
    private boolean isRecognizing = false;

    /**
     * Constructs a VoiceAssistant object with the specified parameters.
     *
     * @param dictFilename A string representing the filename of the dictionary for speech recognition.
     * @param LMFilename   A string representing the filename of the language model for speech recognition.
     */
    public VoiceAssistant(String dictFilename, String LMFilename) {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath(
                String.format("resource:%s", "/edu/cmu/sphinx/models/en-us/en-us"));
        configuration.setDictionaryPath(
                String.format("file:%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), dictFilename));
        configuration.setLanguageModelPath(
                String.format("file:%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), LMFilename));
        try {
            lsr = new LiveSpeechRecognizerExtension(configuration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the command from the voice recognition result.
     *
     * @return The command extracted from the voice recognition result, converted to lowercase.
     */
    public @NotNull String getCommand() {
        return lsr.getResult().getHypothesis().toLowerCase();
    }

    /**
     * Retrieves the code corresponding to the given voice command.
     *
     * @param voiceCommand The voice command to be checked for code mapping.
     * @return The code associated with the voice command. Returns -1 if no matching code is found.
     */
    public int getCode(@NotNull String voiceCommand) {
        if (voiceCommand.contains("play music")) {
            return 0;
        } else if (voiceCommand.contains("tell me a joke")) {
            return 1;
        } else if (voiceCommand.contains("weather forecast")) {
            return 2;
        } else if (voiceCommand.contains("search for")) {
            return 3;
        } else if (voiceCommand.contains("translate")) {
            return 4;
        } else if (voiceCommand.contains("hey vocalia")) {
            return 5;
        } else if (voiceCommand.contains("bye vocalia")) {
            return 6;
        }
        return -1;
    }

    /**
     * Starts the speech recognition process.
     */
    public void startRecognizing() {
        lsr.startRecognition();
        isRecognizing = true;
        playMP3("greetings.mp3");
    }

    /**
     * Stops the speech recognition process.
     */
    public void stopRecognizing() {
        if (isRecognizing) {
            lsr.stopRecognition();
            isRecognizing = false;
        }
        playMP3("farewell.mp3");
    }

    /**
     * Retrieves the substring after a specified search term in a given input string.
     *
     * @param input      The input string to search within.
     * @param searchTerm The search term to find the substring after.
     * @return           The substring after the search term.
     */
    public @NotNull String getSubstringAfter(@NotNull String input, @NotNull String searchTerm) {
        if (input.length() <= searchTerm.length()) return input;
        return input
                .substring(input.indexOf(searchTerm) + searchTerm.length())
                .trim()
                .replaceAll("\\s", "%20");
    }

    /**
     * Plays an MP3 file.
     *
     * @param filename A string representing the filename of the MP3 file to be played.
     */
    public void playMP3(String filename) {
        try (FileInputStream in = new FileInputStream(String.format("%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), filename))) {
            new AdvancedPlayer(in).play();
        } catch (IOException | JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
}
