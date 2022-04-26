package ru.gulyaev.factory.lab4.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public final class InfoLabel extends Label {

    public static final int WIDTH = 230;
    public static final int HEIGHT = 55;

    InfoLabel() {
        this("");
    }

    public InfoLabel(String text) {

        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        setAlignment(Pos.CENTER);
        setFont(FontFactory.getInstance().getDefaultFont());
        setText(text);
    }
}

