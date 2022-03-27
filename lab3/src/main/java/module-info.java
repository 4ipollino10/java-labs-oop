module game.minesweeper.lab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.minesweeper.lab3 to javafx.fxml;
    exports game.minesweeper.lab3;
}