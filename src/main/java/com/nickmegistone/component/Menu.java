package com.nickmegistone.component;

import com.nickmegistone.event.EventMenu;
import com.nickmegistone.swing.ButtonMenu;
import com.nickmegistone.swing.scrollbar.ScrollBarCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static com.nickmegistone.apputils.AppUtils.*;

public class Menu extends javax.swing.JPanel {

    private EventMenu event;
    private int index = 0;

    public Menu() {
        initComponents();
        setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom(SCROLLBAR_COLOR));
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        new Thread(() -> {
            synchronized (this) {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        wait();
                        for (Component com : panelMenu.getComponents()) {
                            if (com instanceof ButtonMenu b) {
                                b.setEnabled(false);
                            }
                        }
                        wait(MENU_SLEEP_MILLIS);
                        for (Component com : panelMenu.getComponents()) {
                            if (com instanceof ButtonMenu b && b.getIndex() != index) {
                                b.setEnabled(true);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void initMenu(EventMenu event) {
        this.event = event;
        addMenu(0, new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/voice.png"), "Voice Assistant");
        addMenu(1, new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/help.png"), "Manual");
        addMenu(2, new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/user.png"), "Creators");
        addMenu(3, new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/comingsoon.png"), "Coming soon");
        addEmpty();
        addMenu(9, new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/copyleft.png"), "Licenses");
    }

    private void addEmpty() {
        panelMenu.add(new JLabel(), "push");
    }

    private void addMenu(int index, Icon icon, String text) {
        ButtonMenu menu = new ButtonMenu(index);
        menu.setIcon(icon);
        menu.setText(" | " + text);
        menu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelMenu.add(menu);
        menu.addActionListener(ae -> {
            event.selected(index);
            setSelected(menu);
            setAllTemporarilyOffExcept(index);
        });
    }

    public synchronized void setAllTemporarilyOffExcept(int index) {
        this.index = index;
        notifyAll();
    }

    private void setSelected(ButtonMenu menu) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof ButtonMenu b) {
                b.setSelected(false);
            }
        }
        menu.setSelected(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.nickmegistone.swing.RoundPanel();
        imageAvatar1 = new com.nickmegistone.swing.ImageAvatar();
        jLabel1 = new JLabel();
        roundPanel2 = new com.nickmegistone.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel();

        roundPanel1.setBackground(MAIN_BACKGROUND_COLOR);

        imageAvatar1.setForeground(AVATAR_BORDER_TEXT_COLOR);
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new ImageIcon(System.getProperty("user.dir") + "/src/main/java/com/nickmegistone/resources/logo.png"));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", Font.BOLD, 36)); // NOI18N
        jLabel1.setForeground(AVATAR_BORDER_TEXT_COLOR);
        jLabel1.setText("Vocalia");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        roundPanel2.setBackground(MAIN_BACKGROUND_COLOR);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelMenu.setBackground(MAIN_BACKGROUND_COLOR);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelMenu);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    com.nickmegistone.swing.RoundPanel roundPanel1;
    com.nickmegistone.swing.ImageAvatar imageAvatar1;
    JLabel jLabel1 = new JLabel();
    com.nickmegistone.swing.RoundPanel roundPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
