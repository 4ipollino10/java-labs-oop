package ru.gulyaev.factory.lab4;

import javafx.concurrent.Task;

public class CopyTask extends Task {

    @Override
    protected Integer call() throws Exception {
        int counter = 0;
        int maxCounter = 1000;
        while(counter != maxCounter){
            counter++;
            Thread.sleep(20);
        }
        updateProgress(counter, maxCounter);
        return 1;
    }

}
