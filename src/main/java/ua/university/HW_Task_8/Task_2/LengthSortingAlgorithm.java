package ua.university.HW_Task_8.Task_2;

import ua.university.HW_Task_8.AbstractSortingAlgorithm;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;

/*Find the three longest words in the input file and print them out in the format:
word ==> the quantity of the letters in the word.
The list should be sorted in descending order of the letters quantity in the word.
If two words have the same quantity of the letters, then the word that occurred in the source file earlier should
also be earlier in the resulting list.*/
public class LengthSortingAlgorithm extends AbstractSortingAlgorithm {

    @Override
    protected  void createHashmap(String str) {
        inputData=str.split("[,.()\s]");

        for (String word : this.inputData) {
            if (!Objects.equals(word, "") && !this.hashMap.containsKey(word)) this.hashMap.put(word, word.length());
        }
        this.sortList = new ArrayList<>(this.hashMap.entrySet());
    }
@Override
    protected void sortByKey(){/*empty because sort by value don`t needed*/}

}
