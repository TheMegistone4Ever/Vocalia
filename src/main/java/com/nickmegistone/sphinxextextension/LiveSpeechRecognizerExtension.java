package com.nickmegistone.sphinxextextension;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.Microphone;
import edu.cmu.sphinx.frontend.util.StreamDataSource;

import java.io.IOException;

//

/**
 * This class represents an extension of the LiveSpeechRecognizer class from the Sphinx library.
 * It provides additional functionality for live speech recognition using a microphone input.
 * The extension allows starting and stopping the recognition process.
 * With help: <a href="http://stackoverflow.com/questions/29121188/cant-access-microphone-while-running-dialog-demo-in-sphinx4-5prealpha#comment46488376_29121188">Nickolay</a>
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class LiveSpeechRecognizerExtension extends AbstractSpeechRecognizerExtension {

    private final Microphone microphone;

    /**
     * Constructs a LiveSpeechRecognizerExtension object with the given configuration.
     *
     * @param configuration The configuration object for the Sphinx speech recognizer.
     * @throws IOException if an I/O error occurs.
     */
    public LiveSpeechRecognizerExtension(Configuration configuration) throws IOException {
        super(configuration);
        microphone = speechSourceProviderExtension.getMicrophone();
        context.getInstance(StreamDataSource.class).setInputStream(microphone.getStream());
    }

    /**
     * Starts the speech recognition process.
     * Allocates the recognizer and starts recording from the microphone.
     */
    public void startRecognition() {
        recognizer.allocate();
        microphone.startRecording();
    }

    /**
     * Stops the speech recognition process.
     * Stops recording from the microphone and deallocates the recognizer.
     */
    public void stopRecognition() {
        microphone.stopRecording();
        recognizer.deallocate();
    }
}
