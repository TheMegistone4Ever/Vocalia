package com.nickmegistone.sphinxextextention;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.Microphone;
import edu.cmu.sphinx.frontend.util.StreamDataSource;
import java.io.IOException;

// http://stackoverflow.com/questions/29121188/cant-access-microphone-while-running-dialog-demo-in-sphinx4-5prealpha#comment46488376_29121188

public class LiveSpeechRecognizerExtension extends AbstractSpeechRecognizerExtension {

    private final Microphone microphone;

    public LiveSpeechRecognizerExtension(Configuration configuration) throws IOException {
        super(configuration);
        microphone = speechSourceProviderExtension.getMicrophone();
        context.getInstance(StreamDataSource.class).setInputStream(microphone.getStream());
    }

    public void startRecognition() {
        recognizer.allocate();
        microphone.startRecording();
    }

    public void stopRecognition() {
        microphone.stopRecording();
        recognizer.deallocate();
    }
}
