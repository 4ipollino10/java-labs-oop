package ru.gulyaev.factory.lab4.GUI;

import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FontFactory {
    private final static String FONT_PATH = "src/main/resources/font.otf";
    private static final int SIZE = 14;
    private static Font _defaultFont = null;
    private static volatile FontFactory _fontFactory;

    private FontFactory() {
        try {
            _defaultFont = Font.loadFont(new FileInputStream(FONT_PATH), SIZE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static FontFactory getInstance() {
        if (_fontFactory == null) {
            synchronized (FontFactory.class) {
                if (_fontFactory == null) {
                    _fontFactory = new FontFactory();
                }
            }
        }
        return _fontFactory;
    }

    public Font getDefaultFont() {
        return _defaultFont;
    }
}
