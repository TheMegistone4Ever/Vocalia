package com.nickmegistone.sphinxextextention;

import edu.cmu.sphinx.api.Microphone;

public class SpeechSourceProviderExtension {

    private static final Microphone microphone = new Microphone(16000.0F, 16, true, false);

    public SpeechSourceProviderExtension() {
    }

    Microphone getMicrophone() {
        return microphone;
    }
}
