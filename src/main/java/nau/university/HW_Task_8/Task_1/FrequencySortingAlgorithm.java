package nau.university.HW_Task_8.Task_1;

import nau.university.HW_Task_8.AbstractSortingAlgorithm;

import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/*
Part 1 (frequency)
In the input file, find the three words that occur most often (if the frequencies coincide,
choose that that occurs earlier), and print them sorted alphabetically in reverse order, in the format: word ==> frequency

Output example:
panda ==> 15
ezhik ==> 20
apple ==> 19
*/

public class FrequencySortingAlgorithm extends AbstractSortingAlgorithm {

    @Override
    protected  void createHashmap(String str) {
        inputData=str.split("[,()\s\n]");

        for (String word : this.inputData) {
            if (!Objects.equals(word, ""))
                if (!this.hashMap.containsKey(word)) this.hashMap.put(word, 1);
                else this.hashMap.put(word, this.hashMap.get(word) + 1);
        }
        this.sortList = new ArrayList<>(this.hashMap.entrySet());
    }

}