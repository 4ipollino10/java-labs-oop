package game.minesweeper.lab3.controllers;

import game.minesweeper.lab3.utils.Constants;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Comparator.comparing;

public class StatisticController {
    private static final String STAT_PATH = "src/main/resources/data.csv";
    private static final String CSV_SEPARATOR = ",";

    public StatisticController(){}

    public boolean setPlayerName(String playerName) {
        Pattern p = Pattern.compile(Constants.REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(playerName);

        if (m.matches()) {
            writeStat(playerName);
            return true;
        }
        return false;
    }

    public void writeStat(String playerName) {
        String line;
        ArrayList<Pair<String ,Integer>> inData = new ArrayList<>();
        boolean flag = false;
        try (BufferedReader br = new BufferedReader(new FileReader(STAT_PATH))) {
            while ((line = br.readLine()) != null) {

                String[] data = line.split(CSV_SEPARATOR);
                if (data[Constants.NAME].equals(playerName)) {
                    data[Constants.COUNT] = String.valueOf(Integer.parseInt(data[Constants.COUNT]) + 1);
                    flag = true;
                }
                inData.add(new Pair<>(data[Constants.NAME], Integer.parseInt(data[Constants.COUNT])));
            }

        } catch (Exception e) {
            System.out.print(Constants.DATABASE_ERROR);
        }

        if (!flag) inData.add(new Pair<>(playerName, 1));

        inData.sort(comparing(Pair::getValue));
        Collections.reverse(inData);


        try (FileWriter fileWriter = new FileWriter(STAT_PATH)) {
            for (Pair<String ,Integer> p: inData ) {

                String result_CSV_line = p.getKey() + CSV_SEPARATOR + p.getValue();
                fileWriter.write(result_CSV_line + System.lineSeparator());
            }
            fileWriter.flush();
        } catch (Exception e) {
            System.out.print(Constants.DATABASE_ERROR);
        }
    }

}

