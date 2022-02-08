package nau.university.HW_Task_8.Task_1;

import nau.university.HW_Task_8.SortEnum;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FrequencySortingAlgorithm f = new FrequencySortingAlgorithm();
        f.startFrequency(new File("File_To_Task_1.txt"),SortEnum.DESC);
        f.printFrequency();
    }
}
