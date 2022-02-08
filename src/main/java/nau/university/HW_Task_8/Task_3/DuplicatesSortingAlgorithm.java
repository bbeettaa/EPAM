package nau.university.HW_Task_8.Task_3;

import nau.university.HW_Task_8.AbstractSortingAlgorithm;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class DuplicatesSortingAlgorithm extends AbstractSortingAlgorithm  {
    @Override
    protected void createHashmap(String str) {
        inputData=str.split("[,()\s\n]");

        for (String inputDatum : this.inputData) {
            String word = inputDatum;
            if (!Objects.equals(word, "")) {
                word = new StringBuilder(word).reverse().toString().toUpperCase();
                if (!this.hashMap.containsKey(word)) this.hashMap.put(word, 1);
                else this.hashMap.put(word, this.hashMap.get(word) + 1);
            }
        }
        this.sortList = new ArrayList<>(this.hashMap.entrySet());
    }

    @Override
    protected void dellMoreThanCountWordsAndNull() {
        //sortByValue();
        for (int i = 0; i < sortList.size(); i++) {
            if (i >= COUNTWORDS || sortList.get(i).getKey() == null || sortList.get(i).getValue() == 1) {
                sortList.remove(i);
                i--;
            }
        }
    }

    @Override
    protected void sortByKey() {

    }

    @Override
    public void printFrequency() {
        for (Map.Entry<String, Integer> en : this.sortList) {
            System.out.printf("%s\n", en.getKey());
        }
    }
}
