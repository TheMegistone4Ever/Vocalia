package com.nickmegistone.sphinxextextention;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.Context;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AbstractSpeechRecognizerExtension {

    protected final Context context;
    protected final Recognizer recognizer;
    protected final SpeechSourceProviderExtension speechSourceProviderExtension;

    public AbstractSpeechRecognizerExtension(Configuration configuration) throws IOException {
        this(new Context(configuration));
    }

    protected AbstractSpeechRecognizerExtension(@NotNull Context context) {
        this.context = context;
        recognizer = context.getInstance(Recognizer.class);
        speechSourceProviderExtension = new SpeechSourceProviderExtension();
    }

    public SpeechResult getResult() {
        Result result = recognizer.recognize();
        return result == null ? null : new SpeechResult(result);
    }
}
