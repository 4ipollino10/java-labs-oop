package game.minesweeper.lab3.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticModel {
    private static final String STAT_PATH = "src/main/resources/data.csv";
    private static final String CSV_SEPARATOR = ",";


    public void setPlayerName(String playerName){

    }


    public List<String[]> getStat() {
        String line = "";
        List<String[]> outputData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(STAT_PATH));) {
            while ((line = br.readLine()) != null) {

                String[] data = line.split(CSV_SEPARATOR);
                outputData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputData;
    }
}
