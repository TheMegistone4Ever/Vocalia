package com.nickmegistone.form;

import com.nickmegistone.ai.*;
import static com.nickmegistone.apputils.AppUtils.*;
import com.nickmegistone.swing.scrollbar.ScrollBarCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.*;
import java.util.function.Supplier;

public class InitForm extends javax.swing.JPanel {

    private VoiceAssistant va;
    private MCNPLNN MCModel;
    private GoogleTranslator googleTranslator;
    private OWMForecaster owmForecaster;
    private final Object lockVoiceCommand = new Object();
    private final Object lockSynthesizer = new Object();
    private final Object lockConnection = new Object();
    private final Thread voiceCommandThread;
    private final Thread synthesizerThread;
    private final Supplier<Boolean> isNetUnavailable = () -> {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80), INTERNET_TIMEOUT);
            return false;
        } catch (IOException e) {
            return true;
        }
    };
    private final Thread retryConnectionThread = new Thread(() -> {
        try {
            synchronized (lockConnection) {
                while (true) {
                    lockConnection.wait();
                    while (isNetUnavailable.get()) {
                        signalSearch(NO_INTERNET_CONNECTION_SEARCH, false);
                        lockConnection.wait(INTERNET_TIMEOUT);
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    private boolean synthesizerIsSpeaking = false;

    public InitForm() {
        initComponents();
        setOpaque(false);
        search.setText(SEND);
        retryConnectionThread.start();
        voiceCommandThread = new Thread(() -> {
            synchronized (lockConnection) {
                lockConnection.notifyAll();
            }
            try {
                synchronized (lockConnection) {
                    while (isNetUnavailable.get()) {
                        lockConnection.wait(INTERNET_TIMEOUT);
                    }
                }
                owmForecaster = new OWMForecaster("bcebc1ab15b0bf", "5a38a0988a6a37301a3b4963d6106fa2");
                googleTranslator = new GoogleTranslator("AKfycbxiVh8Fxy0opG1ygpNdNBaD9t_HC0nqk5IElpLLpgPMdpks_7E8hcH4N74065VJFohn");
                va = new VoiceAssistant("dict.dic", "language-model.lm");
                va.startRecognizing();
                jLabel3.setEnabled(true);
                MCModel = new MCNPLNN("mctext.txt", 4);
                synchronized (lockVoiceCommand) {
                    while (true) {
                        lockVoiceCommand.wait();
                        jLabel3.setEnabled(false);
                        handleCommand(va.getCommand());
                        jLabel3.setEnabled(true);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        voiceCommandThread.start();
        synthesizerThread = new Thread(() -> {
            synchronized (lockSynthesizer) {
                try (Synthesizer synthesizer = Synthesizer.getInstance()) {
                    while (true) {
                        lockSynthesizer.wait();
                        synthesizerIsSpeaking = true;
                        synthesizer.speak(vocaliaAnswer.getText());
                        synthesizerIsSpeaking = false;
                        if (search.getText().equals(SYNTHESIZER_IS_SPEAKING)) {
                            signalSearch(SEND, true);
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        synthesizerThread.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        vocaliaAnswer = new javax.swing.JTextArea();

        setForeground(new java.awt.Color(0, 102, 102));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(1024, 680));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vocalia's answers will appear here:");
        jLabel2.setMaximumSize(new java.awt.Dimension(32767, 64));
        jLabel2.setRequestFocusEnabled(false);

        search.setBackground(new java.awt.Color(0, 102, 102));
        search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Send...");
        search.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        search.setCaretColor(new java.awt.Color(255, 102, 0));
        search.setDisabledTextColor(new java.awt.Color(0, 102, 102));
        search.setEnabled(false);
        search.setMargin(getInsets());
        search.setMaximumSize(new java.awt.Dimension(2147483647, 45));
        search.setMinimumSize(new java.awt.Dimension(73, 45));
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("To aks a question insert text into \"Search\" field or ask it by voice button =)");
        jLabel1.setMaximumSize(new java.awt.Dimension(32767, 16));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/microphone.png"));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setDisabledIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/microphone_active.png"));
        jLabel3.setEnabled(false);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/search.png"));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Welcome to Vocalia!");
        jLabel5.setMaximumSize(new java.awt.Dimension(32767, 64));
        jLabel5.setRequestFocusEnabled(false);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom(new Color(130, 130, 130, 100)));

        vocaliaAnswer.setEditable(false);
        vocaliaAnswer.setBackground(new java.awt.Color(25, 25, 25));
        vocaliaAnswer.setColumns(20);
        vocaliaAnswer.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        vocaliaAnswer.setForeground(new java.awt.Color(255, 255, 255));
        vocaliaAnswer.setLineWrap(true);
        vocaliaAnswer.setRows(5);
        vocaliaAnswer.setText("A very witty, self-sufficient and self-explanatory response from Vocalia the Oracle...");
        vocaliaAnswer.setWrapStyleWord(true);
        vocaliaAnswer.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        vocaliaAnswer.setPreferredSize(new java.awt.Dimension(800, 200));
        jScrollPane2.setViewportView(vocaliaAnswer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        System.out.println(evt);
        synchronized (lockConnection) {
            lockConnection.notifyAll();
        }
        if (synthesizerIsSpeaking) {
            signalSearch(SYNTHESIZER_IS_SPEAKING, false);
        } else {
            signalSearch(SEARCH_WHEN_CLICKED, true);
        }
    }//GEN-LAST:event_searchMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println(evt);
        search.setEnabled(false);
    }//GEN-LAST:event_formMouseClicked

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        System.out.println(evt);
        // Optimised key pressing method - check for network only when <ENTER> is pressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            synchronized (lockConnection) {
                lockConnection.notifyAll();
            }
            if (synthesizerIsSpeaking) {
                signalSearch(SYNTHESIZER_IS_SPEAKING, false);
            } else {
                handleCommand(search.getText());
            }
        }
    }//GEN-LAST:event_searchKeyPressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.out.println(evt);
        synchronized (lockConnection) {
            lockConnection.notifyAll();
        }
        if (synthesizerIsSpeaking) {
            signalSearch(SYNTHESIZER_IS_SPEAKING, false);
        } else {
            handleCommand(search.getText());
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        System.out.println(evt);
        synchronized (lockConnection) {
            lockConnection.notifyAll();
        }
        if (synthesizerIsSpeaking) {
            signalSearch(SYNTHESIZER_IS_SPEAKING, false);
        } else {
            synchronized (lockVoiceCommand) {
                lockVoiceCommand.notifyAll();
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void signalSearch(String text, boolean isEnabled) {
        search.setEnabled(isEnabled);
        search.setText(text);
        search.setDisabledTextColor(isEnabled ? SEARCH_ENABLED_COLOR : SEARCH_DISABLED_COLOR);
    }

    private void handleCommand(String searchQuery) {
        search.setText(searchQuery);
        switch (va.getCode(searchQuery)) {
            case 0 -> handlePlayMusicCommand();
            case 1 -> handleTellJokeCommand();
            case 2 -> handleWeatherForecastCommand();
            case 3 -> handleSearchCommand(searchQuery);
            case 4 -> handleTranslationCommand(searchQuery);
            case 5 -> handleGreetingsCommand();
            case 6 -> handleExitCommand();
            default -> handleUnknownCommand(searchQuery);
        }
        synchronized (lockSynthesizer) {
            lockSynthesizer.notifyAll();
        }
    }

    public void handlePlayMusicCommand() {
        vocaliaAnswer.setText("Right now your loudspeakers are playing wonderful songs via Youtube Music, enjoy it ;)");
        cmdExec("start chrome https://music.youtube.com/watch?list=RDAMVMljUtuoFt-8c");
    }

    public void handleTellJokeCommand() {
        vocaliaAnswer.setText(MCModel.getSentence(5, "okay heres the joke"));
    }

    public void handleWeatherForecastCommand() {
        vocaliaAnswer.setText(owmForecaster.forecast());
    }

    public void handleSearchCommand(String searchQuery) {
        vocaliaAnswer.setText("Running the Google browser to search for this information...");
        cmdExec("start chrome https://www.google.com/search?q=" + va.getSubstringAfter(searchQuery, "search for"));
    }

    public void handleTranslationCommand(String searchQuery) {
        vocaliaAnswer.setText(googleTranslator.translate("en", "uk", va.getSubstringAfter(searchQuery, "translate")));
    }

    public void handleGreetingsCommand() {
        vocaliaAnswer.setText("Hello world! ;)");
    }

    public void handleExitCommand() {
        try {
            retryConnectionThread.interrupt();
            voiceCommandThread.interrupt();
            synthesizerThread.interrupt();
            va.stopRecognizing();
            System.exit(0);
        } catch (NullPointerException | IllegalStateException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void handleUnknownCommand(String searchQuery) {
        vocaliaAnswer.setText(String.format("I don't understand you: \"%s\" ;(", searchQuery));
        System.err.printf("Command not found: %s...%n", searchQuery);
        search.setEnabled(false);
        va.playMP3("farewell.mp3");
    }

    /**
     * Executes a command in the command prompt.
     *
     * @param command A string representing the command to be executed.
     */
    public void cmdExec(String command) {
        try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", command});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField search;
    private javax.swing.JTextArea vocaliaAnswer;
    // End of variables declaration//GEN-END:variables
}
