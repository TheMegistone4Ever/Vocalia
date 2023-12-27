package com.nickmegistone.ai;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * The Synthesizer class represents a text-to-speech synthesizer that can convert text into speech.
 * It uses the FreeTTS library for speech synthesis. <a href="https://cmusphinx.github.io/wiki/tutorial/">Tutorial</a>.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class Synthesizer implements AutoCloseable {

    private final Voice voice;

    /**
     * Constructs a Synthesizer object with the specified voice name and pitch.
     *
     * @param voiceName The name of the voice to be used for speech synthesis.
     * @param pitch     The pitch of the synthesized speech (range: 0.0 to 500.0).
     */
    private Synthesizer(String voiceName, float pitch) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice(voiceName);
        voice.setPitch(pitch);
        voice.allocate();
    }

    private static final class SynthesizerHolder {
        private static final Synthesizer instance = new Synthesizer("kevin16", 120);
    }

    /**
     * Returns the singleton instance of the Synthesizer class.
     *
     * @return The Synthesizer instance.
     */
    public static Synthesizer getInstance() {
        return SynthesizerHolder.instance;
    }

    /**
     * Converts the given message into speech.
     *
     * @param message The message to be spoken.
     */
    public void speak(String message) {
        voice.speak(message);
    }

    @Override
    public void close() {
        voice.deallocate();
    }
}
