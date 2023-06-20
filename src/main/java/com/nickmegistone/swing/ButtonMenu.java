package com.nickmegistone.swing;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class ButtonMenu extends JButton {

    private final Animator animator;
    private int targetSize;
    private float animateSize;
    private Point pressedPoint;
    private float alpha;
    private final Color effectColor;

    public ButtonMenu() {
        effectColor = new Color(173, 173, 173);
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 10, 8, 10));
        setHorizontalAlignment(JButton.LEFT);
        setBackground(new Color(43, 44, 75));
        setForeground(new Color(250, 250, 250));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) << 1;
                animateSize = 0;
                pressedPoint = me.getPoint();
                alpha = .5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animateSize = fraction * targetSize;
                repaint();
            }
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(effectColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        if (pressedPoint != null) {
            Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
            area.intersect(new Area(new Ellipse2D.Double((pressedPoint.x - animateSize / 2), (pressedPoint.y - animateSize / 2), animateSize, animateSize)));
            g2.fill(area);
        }
        g2.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(graphics);
    }

    @Override
    public void paint(Graphics graphics) {
        if (isSelected()) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setColor(new Color(25, 25, 25));
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
        }
        super.paint(graphics);
    }
}
