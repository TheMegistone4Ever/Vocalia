package com.nickmegistone.swing.scrollbar;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

import static com.nickmegistone.apputils.AppUtils.THUMB_SIZE;

public class ModernScrollBarUI extends BasicScrollBarUI {

    @Override
    protected Dimension getMaximumThumbSize() {
        return scrollbar.getOrientation() == JScrollBar.VERTICAL ? new Dimension(0, THUMB_SIZE) : new Dimension(THUMB_SIZE, 0);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return getMaximumThumbSize();
    }

    @Override
    protected JButton createIncreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createDecreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics graphics, JComponent jc, Rectangle rectangle) {

    }

    @Override
    protected void paintThumb(Graphics graphics, JComponent jc, @NotNull Rectangle rectangle) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = rectangle.x;
        int y = rectangle.y;
        int width = rectangle.width;
        int height = rectangle.height;
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            y += 8;
            height -= 16;
        } else {
            x += 8;
            width -= 16;
        }
        g2.setColor(scrollbar.getForeground());
        g2.fillRoundRect(x, y, width, height, 1, 1);
    }

    private static class ScrollBarButton extends JButton {

        public ScrollBarButton() {
            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public void paint(Graphics graphics) {
        }
    }
}
