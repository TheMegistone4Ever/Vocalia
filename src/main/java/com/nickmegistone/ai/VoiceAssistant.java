/*
 * Click nbfs:\\nbhost\\SystemFileSystem\\Templates\\Licenses\\license-default.txt to change this license
 * Click nbfs:\\nbhost\\SystemFileSystem\\Templates\\Classes\\Class.java to edit this template
 */
package com.nickmegistone.ai;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
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
public class VoiceAssistant {
    private final LiveSpeechRecognizer lsr;
    public boolean isRecognizing;

    /**
     * Constructs a VoiceAssistant object with the specified parameters.
     *
     * @param dictFilename   A string representing the filename of the dictionary for speech recognition.
     * @param LMFilename     A string representing the filename of the language model for speech recognition.
     * @throws IOException  If an I/O error occurs while reading the files.
     */
    public VoiceAssistant(String dictFilename, String LMFilename) throws IOException {
        isRecognizing = false;
        Configuration config = new Configuration();
        config.setAcousticModelPath(
                String.format("resource:%s", "/edu/cmu/sphinx/models/en-us/en-us"));
        config.setDictionaryPath(
                String.format("file:%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), dictFilename));
        config.setLanguageModelPath(
                String.format("file:%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), LMFilename));
        lsr = new LiveSpeechRecognizer(config);
    }

    public String getCommand() {
        return lsr.getResult().getHypothesis().toLowerCase();
    }

    /**
     * Starts the speech recognition process and performs tasks based on the recognized voice commands.
     */
    /*public void startSpeechRecognizing() {
        // lsr.startRecognition(true);
        SpeechResult speechResult;
        while ((speechResult = lsr.getResult()) != null) {
            String voiceCommand = speechResult.getHypothesis().toLowerCase();
            if (voiceCommand.contains("play music")) {
                cmdExec("start chrome https://music.youtube.com/watch?list=RDAMVMljUtuoFt-8c");
            } else if (voiceCommand.contains("tell me a joke")) {

            } else if (voiceCommand.contains("weather forecast")) {
                cmdExec("start chrome https://www.gismeteo.ua/");
            } else if (voiceCommand.contains("search for")) {
                cmdExec("start chrome https://www.google.com/search?q=" + getSubstringAfter(voiceCommand, "search for"));
            } else if (voiceCommand.contains("translate")) {
                cmdExec("start chrome https://www.deepl.com/en/translator#en/uk/" + getSubstringAfter(voiceCommand, "translate"));
            } else if (voiceCommand.contains("hey vocalia")) {
                playMP3("greetings.mp3");
                // TODO: Add pointer to searchbar and voice-typing...
            } else if (voiceCommand.contains("bye vocalia")) {
                stopRecognizing();
                System.exit(0);
            } else {
                System.err.printf("Command not found: %s...%n", voiceCommand);
            }
        }
    }*/

    public int getCode(String voiceCommand) {
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
        isRecognizing = true;
        lsr.startRecognition(true);
        playMP3("greetings.mp3");
    }

    /**
     * Stops the speech recognition process.
     */
    public void stopRecognizing() {
        isRecognizing = false;
        lsr.stopRecognition();
        playMP3("farewell.mp3");
    }

    /**
     * Executes a command in the command prompt.
     *
     * @param command       A string representing the command to be executed.
     */
    public void cmdExec(String command) {
        try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", command});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the substring after a specified search term in a given input string.
     *
     * @param input         The input string to search within.
     * @param searchTerm    The search term to find the substring after.
     * @return              The substring after the search term.
     */
    public @NotNull String getSubstringAfter(@NotNull String input, String searchTerm) {
        if (input.length() <= searchTerm.length()) return input;
        return input
                .substring(input.indexOf(searchTerm) + searchTerm.length())
                .trim()
                .replaceAll("\\s", "%20");
    }

    /**
     * Plays an MP3 file.
     *
     * @param filename   A string representing the filename of the MP3 file to be played.
     */
    public void playMP3(String filename) {
        try (FileInputStream in = new FileInputStream(String.format("%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), filename))) {
            new AdvancedPlayer(in).play();
        } catch (IOException | JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
}
