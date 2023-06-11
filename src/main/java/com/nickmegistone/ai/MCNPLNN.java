package com.nickmegistone.ai;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a Markov Chain Natural Language Processing Neural Network.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class MCNPLNN {
    private final Random random;
    private final Map<String, Map<String, Double>> MCModel;

    /**
     * This constructor creates a Markov Chain model object from a file of raw text.
     *
     * @param filename  A filename that will be a list of cleaned words to be used as the basis for the model.
     * @param nGram     An integer representing the number of words in each state. Default value is 3.
     */
    public MCNPLNN(String filename, int nGram) {
        random = new Random();
        List<String> clearText = readAndCleanText(String.format(System.getProperty("user.dir") +
                "/src/main/java/com/nickmegistone/resources/%s.txt", filename));
        MCModel = new HashMap<>();
        for (int i = 0; i <= clearText.size() - (nGram << 1); ++i) {
            String currState = String.join(" ", clearText.subList(i, i + nGram));
            String nextState = String.join(" ", clearText.subList(i + nGram, i + (nGram << 1)));
            MCModel.putIfAbsent(currState, new HashMap<>());
            MCModel.get(currState).put(nextState, MCModel.get(currState).getOrDefault(nextState, 0.0) + 1);
        }
        // Relative frequency
        for (Map.Entry<String, Map<String, Double>> entry : MCModel.entrySet()) {
            double totalNeighbours = entry.getValue().values().stream().mapToDouble(Double::doubleValue).sum();
            Map<String, Double> transition = entry.getValue();
            for (Map.Entry<String, Double> transitionEntry : transition.entrySet()) {
                transitionEntry.setValue(transitionEntry.getValue() / totalNeighbours);
            }
        }
    }

    /**
     * This function generates a string of text based on a Markov chain model.
     *
     * @param maxTokens   An integer representing the maximum tokens in the generated text. Default value is 30.
     * @param start   A string representing the initial state to begin text generation. Default value is "для вашого організму".
     * @return A processed string of generated text.
     */
    public String getSentence(int maxTokens, String start) {
        List<String> text = new ArrayList<>(Collections.singletonList(start));
        for (int i = 0; i < maxTokens; ++i) {
            Map<String, Double> possibleWords = MCModel.get(text.get(text.size() - 1));
            String word = (String) possibleWords.keySet().toArray()[getRandomIndexByWeights(possibleWords.values().stream().toList())];
            if (word.equals(start)) {
                break;
            }
            text.add(word);
        }
        return StringUtils.capitalize(String.join(" ", text) + ".")
                .replaceFirst(" Okay", " Okay,")
                .replaceFirst(" heres", " here's")
                .replaceFirst(" joke", " joke:")
                .replaceFirst(" it", ", it")
                .replaceFirst(" because", "? - because")
                .replaceFirst(" youre", ", you're")
                .replaceFirst(" haha", String.format(" - %sha-ha%s,", getFunnyEmoji(), getFunnyEmoji()));
    }

    /**
     * This function reads text from a file and returns a list of cleaned words.
     *
     * @param filePath A string representing the path to the file to be read.
     * @return A list of cleaned words extracted from the file.
     */
    private static @NotNull List<String> readAndCleanText(String filePath) {
        List<String> cleanedWords = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.replaceAll("[^\\w\\s\\a(){}-]", "").toLowerCase());
                while (matcher.find()) {
                    cleanedWords.add(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cleanedWords;
    }

    /**
     * This function returns a random index based on a list of probabilities.
     *
     * @param probabilities A list of doubles representing the probabilities.
     * @return An integer representing the random index.
     */
    private int getRandomIndexByWeights(@NotNull List<Double> probabilities) {
        int i = 0;
        for (double cumulativeProbability = .0, rnd = random.nextDouble(); i < probabilities.size(); ++i) {
            cumulativeProbability += probabilities.get(i);
            if (rnd < cumulativeProbability) {
                break;
            }
        }
        return i;
    }

    /**
     * This function returns a random funny emoji.
     *
     * @return A string representing a funny emoji.
     */
    private String getFunnyEmoji() {
        return new String[]{"😄", "😂", "😊", "😜", "🤣", "😎", "🤪", "😆", "😁", "🤩"}[random.nextInt(10)];
    }
}
