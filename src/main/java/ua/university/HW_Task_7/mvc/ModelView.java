package ua.university.HW_Task_7.mvc;

import java.util.ArrayList;
import java.util.List;

public class ModelView {
    static List<String> statistic = new ArrayList<>();

    public void printStatistic(int min, int max, int randNum, int tryNum) {
        boolean bool = randNum == tryNum;
        statistic.add("[" + min + "," + max + "] -> " + randNum + " ; " + tryNum + ". " + bool);
        System.out.println(statistic.get(statistic.size() - 1));
    }
}
