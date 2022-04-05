package game.minesweeper.lab3.utils;

import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Constants {
    private final static String FONT_PATH = "src/main/resources/sunday.ttf";

    private static Font DEFAULT_FONT = null;
    private static Font SMALL_FONT = null;
    public final static int MENU_BUTTON_START_X = 100;
    public final static int TEXT_FIELD_START_X = 100;
    public final static int TEXT_FIELD_START_Y = 170;
    public final static int MENU_BUTTON_START_X1 = 10;
    public final static int MENU_BUTTON_START_Y = 100;
    public final static int MENU_BUTTON_START_Y2 = 10;
    public static final int SHIFT = 100;
    public static final String PlAY_BUTTON_NAME = "play";
    public static final String STATISTIC_BUTTON_NAME = "statistic";
    public static final String EXIT_BUTTON_NAME = "exit";
    public static final String ENTER_BUTTON_NAME = "enter";
    public static final String FST_MODE_BUTTON_NAME = """
            10x10
            10 bombs
            """;
    public static final String SND_MODE_BUTTON_NAME = """
    16x16
    50 bombs
    """;
    public static final String TRD_MODE_BUTTON_NAME = """
            18x18
            100 bombs
            """;

    public static final String BACK_BUTTON_NAME = "back";
    public static final String BG_IMAGE = "bg.jpg";
    public static final String BG_WIN_IMAGE = "win_bg.jpg";
    public static final String BG_LOST_IMAGE = "loose_bg.jpg";

    static {
        try {
            DEFAULT_FONT = Font.loadFont(new FileInputStream(FONT_PATH), 23);
            SMALL_FONT = Font.loadFont(new FileInputStream(FONT_PATH), 14);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Font getDefaultFont() {
        return DEFAULT_FONT;
    }

    public static Font getSmallFont() {
        return SMALL_FONT;
    }
    public static final String END_GAME_MENU_MAP = """
            --------------------------------
            |          MineSweeper         |
            |                              |
            |       Enter your name:       |
            |                              |
            |prod.GT                  v1.0 |
            --------------------------------
            """;


    public static final String MAIN_MENU_MAP = """
            --------------------------------
            |          MineSweeper         |
            |                              |
            |             play             |
            |           statistic          |
            |             exit             |
            |prod.GT                  v1.0 |
            --------------------------------
            """;
    public static final String STATISTIC_MENU_MAP = """
            -----------------------------------------
            |              MineSweeper              |
            |     1.sdkjlfdsah wins: 2, looses: 3   |
            |                                       |
            |                 back                  |
            |prod.GT                           v1.0 |
            -----------------------------------------
            """;
    public static final String MODE_MENU_MAP = """
            --------------------------------
            |          MineSweeper         |
            |            1.10x10, 10       |
            |            2.16x16, 30       |
            |            3.18x18, 50       |
            |                              |
            |             back             |
            |prod.GT                  v1.0 |
            --------------------------------
            """;
    public static final String STAT_PATH = "src/main/resources/data.csv";
    public static final int NAME = 0;
    public static final int COUNT = 1;
    public static final String STAT_LABEL = "TOP 10 PLAYERS: ";
    public static final int MAX_TOP_PLAYERS_COUNT = 10;
    public static final int STAT_TOP_LAYOUT_POS_X = 100;
    public static final int STAT_TOP_LAYOUT_POS_Y = 155;
    public static final String BRACKET = ") ";
    public static final String SPACE = " ";
    public static final int STAT_Y_LAYOUT_POS = 175;
    public static final int SMALL_SHIFT = 30;

    public static final String LABEL_STYLE = "-fx-text-fill: white";
    public static final String DATABASE_ERROR = "database problems\n";
    public static final int LABEL_START_X = 40;
    public static final int LABEL_START_Y = 220;
    public static final String PROMPT_TEXT = "Enter your name:";
    public static final int PREF_HEIGHT = 45;
    public static final int PREF_WIDTH = 190;
    public static final String REGEX = "^[a-zA-Z0-9_]{3,10}$";
    public static final String NAME_IS_EMPTY = "Name is empty!";
    public static final String PLAY_COMMAND_NAME = "play";
    public static final String STATISTIC_COMMAND_NAME = "statistic";
    public static final String EXIT_COMMAND_NAME = "exit";
    public static final String FST_MODE_COMMAND_NAME = "1";
    public static final String SND_MODE_COMMAND_NAME = "2";
    public static final String TRD_MODE_COMMAND_NAME = "3";
    public static final String BACK_COMMAND_NAME = "back";
    public static final String TURN_COMMAND_NAME = "turn";
    public static final String BUTTON_FLAG_STYLE = "-fx-background-color: transparent; -fx-background-image: url('flag.png');";

    public static final int FST_MODE_BOMBS_AMOUNT = 10;
    public static final int SND_MODE_BOMBS_AMOUNT = 30;
    public static final int TRD_MODE_BOMBS_AMOUNT = 50;
    public static final int FST_MODE_MATRIX_SIZE = 10;
    public static final int SND_MODE_MATRIX_SIZE = 16;
    public static final int TRD_MODE_MATRIX_SIZE = 18;


    public static final String EMPTY_STR = "";
    public static final String SIS_GAME_VIEW = "1";
    public static final String GUI_GAME_VIEW = "2";
    public static final int FST_PLAYER_TURN_ARG = 0;
    public static final int SND_PLAYER_TURN_ARG = 1;
    public static final int TRD_PLAYER_TURN_ARG = 2;
    public static final int LABEL_PANE = 2;

    public static final String WRONG_COMMAND_ERROR_TEXT = "Unknown command, try again, but now try to use your eyes, bro :)";
    public static final String EXIT_MESSAGE = "Thanks for playing!";
    public static final String GAME_OVER_LOOSE_MESSAGE = "BOOOOOOOOOOOOOOOOOOOOOOOOOM!";
    public static final String GAME_OVER_WIN_MESSAGE = "GOOD JOB!";
    public static final int TOP_NAME_LENGTH = 10;
    public static final int BOTTOM_NAME_LENGTH = 3;
    public static final String BOMB = "9";
    public static final String EMPTY_CELL_TYPE = "0";
    public static final String FLAG = "P";
    public static final String CLOSED = "@";
    public static final String BAD_TURN = "Bad turn, try harder!";
    public static final String BAD_NAME = "Bad name, remember, you can come up with it from 3 to 10 letters!";
    public static final String GOOD_NAME = "Nice nick bro :)";
    public static final String GOOD_TURN = "Nice turn!";
    public static final String NO_FLAG_MESSAGE = "There is no flag nearby!";
    public static final String FLAG_TURN_TYPE = "flag";
    public static final String UNFLAG_TURN_TYPE = "unflag";
    public static final String PRESS_TURN_TYPE = "press";
    public static final String REVEAL_TURN_TYPE = "reveal";
    public static final int MAX_TURN_ARGS_AMOUNT = 3;

}
