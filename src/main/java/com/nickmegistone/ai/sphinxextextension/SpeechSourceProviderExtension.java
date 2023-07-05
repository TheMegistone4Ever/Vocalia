package com.nickmegistone.ai.sphinxextextension;

import edu.cmu.sphinx.api.Microphone;

/**
 * This class represents an extension for providing speech sources using the Sphinx speech recognition library.
 * It includes functionality for accessing a microphone as a speech source.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class SpeechSourceProviderExtension {

    private static final Microphone microphone = new Microphone(16000.0F, 16, true, false);

    /**
     * Constructs a SpeechSourceProviderExtension object.
     * This constructor creates an instance of the extension class.
     */
    public SpeechSourceProviderExtension() {
    }

    /**
     * Retrieves the microphone instance.
     * This function returns the microphone object used as a speech source.
     *
     * @return The Microphone object representing the speech source.
     */
    Microphone getMicrophone() {
        return microphone;
    }
}
