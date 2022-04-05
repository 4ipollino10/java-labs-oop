package game.minesweeper.lab3.SIS.utils;

import game.minesweeper.lab3.SIS.views.SISView;

import java.io.IOException;

public class Command {

    private final MyEventHandler _handler;

    public Command(MyEventHandler handler){
        _handler = handler;
    }

    public SISView action() throws IOException {
        return _handler.handle();
    }

}
