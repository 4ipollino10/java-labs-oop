package game.minesweeper.lab3;

import game.minesweeper.lab3.GUI.views.GUIMainMenuView;
import game.minesweeper.lab3.SIS.views.SISMainMenuView;
import game.minesweeper.lab3.SIS.views.SISView;
import game.minesweeper.lab3.utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {
    public static String GAME_NAME = "MineSweeper";


    @Override
    public void start(Stage stage) {

        stage.setTitle(GAME_NAME);

        new GUIMainMenuView(stage);

    }

    public static void main(String[] args) throws IOException {
        String view = getGameView();

        if(Objects.equals(view, Constants.SIS_GAME_VIEW)){
            SISView currentView = new SISMainMenuView();
            while(true){
                if(currentView == null){
                    System.out.println(Constants.EXIT_MESSAGE);
                    System.exit(0);
                }
                Runtime.getRuntime().exec("cls");
                currentView = currentView.show();
            }

        }else{
            launch();
        }

    }

    public static String getGameView(){
        Scanner scanner = new Scanner(System.in);
        String view = scanner.next();
        while (!(Objects.equals(view, Constants.SIS_GAME_VIEW) | Objects.equals(view, Constants.GUI_GAME_VIEW))) {
            view = scanner.next();
        }
        return view;
    }
}