package game.minesweeper.lab3.GUI.views;



import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class GUIView {
    public static final double HEIGHT = 768.0;
    private static final double WIDTH = 1024.0;
    private final Stage _stage;



    public GUIView(Stage stage) {
        this._stage = stage;
    }

    public Stage getStage() {
        return _stage;
    }

    public void setNewScene(AnchorPane anchorPane) {
        _stage.setScene(new Scene(anchorPane, WIDTH, HEIGHT));
    }
    public void show() { _stage.show(); }
}
