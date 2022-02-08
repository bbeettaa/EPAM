package nau.university.HW_Task_8.Task_3;

import nau.university.HW_Task_8.SortEnum;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DuplicatesSortingAlgorithm d = new DuplicatesSortingAlgorithm();
        d.startFrequency(new File("src\\main\\java\\nau\\university\\Task8\\File_To_Task_3.txt"), SortEnum.DESC);
        d.printFrequency();
    }
}
