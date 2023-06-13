package com.nickmegistone.form;

import com.nickmegistone.ai.MCNPLNN;
import com.nickmegistone.ai.VoiceAssistant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class InitForm extends javax.swing.JPanel {

    VoiceAssistant va;
    MCNPLNN MCModel;

    public InitForm() {
        initComponents();
        MCModel = new MCNPLNN("mctext.txt", 4);
        setOpaque(false);
        Thread recognitionThread = new Thread(() -> {
            try {
                va = new VoiceAssistant("dict.dic", "language-model.lm");
                va.startRecognizing();
                while (va.isRecognizing) {
                    handleCommand(va.getCommand());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        recognitionThread.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setForeground(new java.awt.Color(0, 102, 102));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(1024, 680));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Welcome to Vocalia!");
        jLabel2.setMaximumSize(new java.awt.Dimension(32767, 64));
        jLabel2.setRequestFocusEnabled(false);

        search.setBackground(new java.awt.Color(0, 102, 102));
        search.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Send");
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
        search.addActionListener(this::searchActionPerformed);
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("To aks a question insert text into \"Search\" field or say \"Hey, Vocalia!\" and ask it by voice...");
        jLabel1.setMaximumSize(new java.awt.Dimension(32767, 16));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/microphone.png"));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/search.png"));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(522, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        search.setEnabled(true);
    }//GEN-LAST:event_searchActionPerformed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        System.out.println(evt);
        search.setEnabled(true);
        search.setText("");
    }//GEN-LAST:event_searchMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println(evt);
        search.setEnabled(false);
    }//GEN-LAST:event_formMouseClicked

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String searchQuery = search.getText();
            handleCommand(searchQuery);
        }
    }//GEN-LAST:event_searchKeyPressed

    private void handleCommand(String searchQuery) {
        int code = va.getCode(searchQuery);
        if (code != -1) {
            search.setText(searchQuery);
        } else {
            search.setText("I don't understand your command!");
            va.playMP3("farewell.mp3");
        }
        switch (code) {
            case 0 -> handlePlayMusicCommand();
            case 1 -> handleTellJokeCommand();
            case 2 -> handleWeatherForecastCommand();
            case 3 -> handleSearchCommand(searchQuery);
            case 4 -> handleTranslationCommand(searchQuery);
            case 5 -> handleGreetingsCommand();
            case 6 -> handleExitCommand();
            default -> handleUnknownCommand(searchQuery);
        }
    }

    public void handlePlayMusicCommand() {
        va.cmdExec("start chrome https://music.youtube.com/watch?list=RDAMVMljUtuoFt-8c");
    }

    public void handleTellJokeCommand() {
        System.out.println(MCModel.getSentence(5, "okay heres the joke"));
    }

    public void handleWeatherForecastCommand() {
        va.cmdExec("start chrome https://www.gismeteo.ua/");
    }

    public void handleSearchCommand(String searchQuery) {
        va.cmdExec("start chrome https://www.google.com/search?q=" + va.getSubstringAfter(searchQuery, "search for"));
    }

    public void handleTranslationCommand(String searchQuery) {
        va.cmdExec("start chrome https://www.deepl.com/en/translator#en/uk/" + va.getSubstringAfter(searchQuery, "translate"));
    }

    public void handleGreetingsCommand() {
        va.startRecognizing();
    }

    public void handleExitCommand() {
        va.stopRecognizing();
        System.exit(0);
    }

    public void handleUnknownCommand(String searchQuery) {
        System.err.printf("Command not found: %s...%n", searchQuery);
        search.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
