package ua.university.HW_Task_8.Task_2;

import ua.university.HW_Task_8.SortEnum;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LengthSortingAlgorithm f = new LengthSortingAlgorithm();
        f.startFrequency(new File("src\\main\\java\\nau\\university\\Task8\\File_To_Task_2.txt"), SortEnum.DESC);
        f.printFrequency();
    }
}
