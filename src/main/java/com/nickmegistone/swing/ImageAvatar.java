package com.nickmegistone.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageAvatar extends JComponent {

    public void setIcon(Icon icon) {
        this.icon = icon;
        repaint();
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }
    private Icon icon;
    private int borderSize;

    @Override
    protected void paintComponent(Graphics graphics) {
        if (icon != null) {
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height);
            int x = (width >> 1) - (diameter >> 1);
            int y = (height >> 1) - (diameter >> 1);
            int border = borderSize << 1;
            diameter -= border;
            Rectangle size = getAutoSize(icon, diameter);
            BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2_img = img.createGraphics();
            g2_img.fillOval(0, 0, diameter, diameter);
            g2_img.setComposite(AlphaComposite.SrcIn);
            g2_img.drawImage(((ImageIcon) icon).getImage(), size.x, size.y, size.width, size.height, null);
            g2_img.dispose();
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (borderSize > 0) {
                diameter += border;
                g2.setColor(getForeground());
                g2.fillOval(x, y, diameter, diameter);
            }
            if (isOpaque()) {
                g2.setColor(getBackground());
                diameter -= border;
                g2.fillOval(x + borderSize, y + borderSize, diameter, diameter);
            }
            g2.drawImage(img, x + borderSize, y + borderSize, null);
            g2.dispose();
        }
        super.paintComponent(graphics);
    }

    private Rectangle getAutoSize(Icon image, int size) {
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double scale = Math.max((double) size / iw, (double) size / ih);
        int width = Math.max(1, (int) (scale * iw));
        int height = Math.max(1, (int) (scale * ih));
        return new Rectangle((size - width) >> 1, (size - height) >> 1, width, height);
    }
}
