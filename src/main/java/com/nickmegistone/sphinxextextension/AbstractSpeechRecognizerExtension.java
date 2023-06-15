package com.nickmegistone.sphinxextextension;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.Context;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * This class serves as an abstract base class for implementing speech recognition using the Sphinx library.
 * It provides common functionality for configuring and accessing speech recognition results.
 * It provides common functionality for configuring and accessing speech recognition results.
 * The class relies on a {@link Context} object for configuration and a {@link Recognizer} object for recognition.
 * It also utilizes a {@link SpeechSourceProviderExtension} to obtain the speech source.
 * Subclasses can extend this abstract class to implement specific speech recognition functionalities.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class AbstractSpeechRecognizerExtension {

    protected final Context context;
    protected final Recognizer recognizer;
    protected final SpeechSourceProviderExtension speechSourceProviderExtension;

    /**
     * Constructs an AbstractSpeechRecognizerExtension object.
     * This constructor initializes the context, recognizer, and speechSourceProviderExtension objects.
     *
     * @param configuration The configuration object for speech recognition.
     * @throws IOException If an I/O error occurs during initialization.
     */
    public AbstractSpeechRecognizerExtension(Configuration configuration) throws IOException {
        this(new Context(configuration));
    }

    /**
     * Constructs an AbstractSpeechRecognizerExtension object with a provided context.
     * This constructor initializes the context, recognizer, and speechSourceProviderExtension objects.
     *
     * @param context The context object for speech recognition.
     */
    protected AbstractSpeechRecognizerExtension(@NotNull Context context) {
        this.context = context;
        recognizer = context.getInstance(Recognizer.class);
        speechSourceProviderExtension = new SpeechSourceProviderExtension();
    }

    /**
     * Retrieves the speech recognition result.
     * This function performs speech recognition and returns the result as a {@link SpeechResult} object.
     *
     * @return The speech recognition result as a {@link SpeechResult} object.
     */
    public SpeechResult getResult() {
        Result result = recognizer.recognize();
        return result == null ? null : new SpeechResult(result);
    }
}
