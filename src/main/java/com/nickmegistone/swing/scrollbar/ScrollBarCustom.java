package com.nickmegistone.swing.scrollbar;

import javax.swing.*;
import java.awt.*;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom(Color color) {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setForeground(color);
        setUnitIncrement(20);
        setOpaque(false);
    }
}
