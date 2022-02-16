package ua.university.HW_Task_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;

public abstract class AbstractSortingAlgorithm {

    protected final int COUNTWORDS = 3;

    protected String[] inputData = new String[0];
    protected HashMap<String, Integer> hashMap = new LinkedHashMap<>();
    protected List<Map.Entry<String, Integer>> sortList = null;

    protected File file = null;
    protected SortEnum comparator;


    public void startFrequency(File file, SortEnum comparator) throws FileNotFoundException {
        this.file = file;
        String str;
        if (file.exists())
            str = getInputDataFromFile();
        else
            throw new FileNotFoundException("File <" + file.getName() + "> not found");
        startFrequency(str, comparator, true);
    }

    protected String getInputDataFromFile() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        StringBuilder s = new StringBuilder();
        while (sc.hasNextLine())
            s.append(sc.nextLine());
        return s.toString();
    }

    public void startFrequency(String str, SortEnum comparator) throws FileNotFoundException {
        startFrequency(str, comparator, false);
    }

    protected void startFrequency(String str, SortEnum comparator, boolean isDataFromFile) throws FileNotFoundException {
        this.comparator = comparator;

        createHashmap(str);

        dellMoreThanCountWordsAndNull();
        sortByKey();
    }

    abstract protected void createHashmap(String str) throws FileNotFoundException;

    protected void dellMoreThanCountWordsAndNull() {
        sortByValue();
        for (int i = 0; i < sortList.size(); i++) {
            if (i >= COUNTWORDS || sortList.get(i).getKey() == null) {
                sortList.remove(i);
                i--;
            }
        }
    }

    protected void sortByValue() {
        if (comparator == SortEnum.ASC)
            this.sortList.sort(comparingByValue(Comparator.naturalOrder()));
        else
            this.sortList.sort(comparingByValue(Comparator.reverseOrder()));
    }

    protected void sortByKey() {
        if (comparator == SortEnum.ASC)
            this.sortList.sort(comparingByKey(Comparator.naturalOrder()));
        else
            this.sortList.sort(comparingByKey(Comparator.reverseOrder()));
    }


    public void printFrequency() {
        for (Map.Entry<String, Integer> en : this.sortList) {
            System.out.printf("%s ==> %d\n", en.getKey(), en.getValue());
        }
    }

    public List<Map.Entry<String, Integer>> getHashMap() {
        return sortList;
    }
}
