/*
 * Click nbfs:\\nbhost\\SystemFileSystem\\Templates\\Licenses\\license-default.txt to change this license
 * Click nbfs:\\nbhost\\SystemFileSystem\\Templates\\Classes\\Class.java to edit this template
 */
package com.nickmegistone.ai;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
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
    private final MCNPLNN MCModel;

    /**
     * Constructs a VoiceAssistant object with the specified parameters.
     *
     * @param MCFilename     A string representing the filename of the Markov chain model.
     * @param nGram          An integer representing the n-gram size for the Markov chain model.
     * @param dictFilename   A string representing the filename of the dictionary for speech recognition.
     * @param LMFilename     A string representing the filename of the language model for speech recognition.
     * @throws IOException  If an I/O error occurs while reading the files.
     */
    public VoiceAssistant(String MCFilename, int nGram, String dictFilename, String LMFilename) throws IOException {
        MCModel = new MCNPLNN(MCFilename, nGram);
        Configuration config = new Configuration();
        config.setAcousticModelPath(
                String.format("resource:%s", "/edu/cmu/sphinx/models/en-us/en-us"));
        config.setDictionaryPath(
                String.format("file:%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), dictFilename));
        config.setLanguageModelPath(
                String.format("file:%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), LMFilename));
        lsr = new LiveSpeechRecognizer(config);
    }

    /**
     * Starts the speech recognition process and performs tasks based on the recognized voice commands.
     *
     * @throws IOException          If an I/O error occurs while interacting with the voice assistant.
     * @throws JavaLayerException   If an error occurs during audio playback.
     */
    public void startSpeechRecognizing() throws IOException, JavaLayerException {
        lsr.startRecognition(true);
        SpeechResult speechResult;
        while ((speechResult = lsr.getResult()) != null) {
            String voiceCommand = speechResult.getHypothesis().toLowerCase();
            if (voiceCommand.contains("play music")) {
                /* Integrate YouTube Music */
                cmdExec("start chrome https://music.youtube.com/watch?list=RDAMVMljUtuoFt-8c");
            } else if (voiceCommand.contains("tell me a joke")) {
                /* Integrate MCNPLNN */
                String joke = MCModel.getSentence(5, "okay heres the joke");
                System.out.println(joke);
            } else if (voiceCommand.contains("weather forecast")) {
                /* Integrate Gismeteo */
                cmdExec("start chrome https://www.gismeteo.ua/");
            } else if (voiceCommand.contains("search for")) {
                /* Integrate Google Chrome */
                cmdExec("start chrome https://www.google.com/search?q=" + getSubstringAfter(voiceCommand, "search for"));
            } else if (voiceCommand.contains("translate")) {
                /* Integrate DeepL */
                cmdExec("start chrome https://www.deepl.com/en/translator#en/uk/" + getSubstringAfter(voiceCommand, "translate"));
            } else if (voiceCommand.contains("hey vocalia")) {
                playMP3("greetings.mp3");
                // TODO: Add pointer to searchbar and voice-typing...
            } else if (voiceCommand.contains("bye vocalia")) {
                stopRecognizing();
                playMP3("farewell.mp3");
                System.exit(0);
            } else {
                System.err.printf("Command not found: %s...%n", voiceCommand);
            }
        }
    }

    /**
     * Stops the speech recognition process.
     */
    public void stopRecognizing() {
        lsr.stopRecognition();
    }

    /**
     * Executes a command in the command prompt.
     *
     * @param command   A string representing the command to be executed.
     * @throws IOException  If an I/O error occurs while executing the command.
     */
    private static void cmdExec(String command) throws IOException {
        Runtime.getRuntime().exec(new String[]{"cmd", "/c", command});
    }

    /**
     * Retrieves the substring after a specified search term in a given input string.
     *
     * @param input         The input string to search within.
     * @param searchTerm    The search term to find the substring after.
     * @return              The substring after the search term.
     */
    private static @NotNull String getSubstringAfter(@NotNull String input, String searchTerm) {
        return input
                .substring(input.indexOf(searchTerm) + searchTerm.length())
                .trim()
                .replaceAll("\\s", "%20");
    }

    /**
     * Plays an MP3 file.
     *
     * @param filename   A string representing the filename of the MP3 file to be played.
     * @throws IOException          If an I/O error occurs while reading the file.
     * @throws JavaLayerException   If an error occurs during audio playback.
     */
    private static void playMP3(String filename) throws IOException, JavaLayerException {
        try (FileInputStream in = new FileInputStream(String.format("%s/src/main/java/com/nickmegistone/resources/%s", System.getProperty("user.dir"), filename))) {
            new AdvancedPlayer(in).play();
        }
    }
}
