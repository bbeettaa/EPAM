package nau.university.Test_Task_8;

import nau.university.HW_Task_8.SortEnum;
import nau.university.HW_Task_8.Task_2.LengthSortingAlgorithm;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Test_LengthSortingAlgorithm {
    @Test
    public void testLength_number_without_repeating_desc() throws FileNotFoundException {
        LengthSortingAlgorithm lengthSortingAlgorithm = new LengthSortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("5555", 4);
        linkedMap.put("444", 3);
        linkedMap.put("22", 2);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


        lengthSortingAlgorithm.startFrequency("1 22 33 444 5555", SortEnum.DESC);

        assertEquals(list, lengthSortingAlgorithm.getHashMap());
    }

    @Test
    public void testLength_number_desc() throws FileNotFoundException {
        LengthSortingAlgorithm lengthSortingAlgorithm = new LengthSortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("55555", 5);
        linkedMap.put("4444", 4);
        linkedMap.put("333", 3);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


        lengthSortingAlgorithm.startFrequency("1 22 333 4444 55555 55555 333 22 4444", SortEnum.DESC);

        assertEquals(list, lengthSortingAlgorithm.getHashMap());
    }

    @Test
    public void testLength_words_scattered_with_space_desc() throws FileNotFoundException {
        LengthSortingAlgorithm lengthSortingAlgorithm = new LengthSortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("anesthetist", 11);
        linkedMap.put("kitchen", 7);
        linkedMap.put("bird", 4);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        lengthSortingAlgorithm.startFrequency("anesthetist kitchen bird age", SortEnum.DESC);

        assertEquals(list, lengthSortingAlgorithm.getHashMap());
    }

    @Test
    public void testLength_null_desc() throws FileNotFoundException {
        LengthSortingAlgorithm lengthSortingAlgorithm = new LengthSortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        lengthSortingAlgorithm.startFrequency("", SortEnum.DESC);

        assertEquals(list, lengthSortingAlgorithm.getHashMap());
    }


/*    @Test
    public void testFrequency_number_without_repeating_asc() throws FileNotFoundException {
        LengthSortingAlgorithm lengthSortingAlgorithm = new LengthSortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("1", 1);
        linkedMap.put("2", 1);
        linkedMap.put("3", 1);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


        lengthSortingAlgorithm.startFrequency("1 2 3 4 5", SortEnum.ASC);

        assertEquals(list, lengthSortingAlgorithm.getHashMap());
    }



    @Test
    public void testFrequency_words_scattered_asc() throws FileNotFoundException {
        LengthSortingAlgorithm lengthSortingAlgorithm = new LengthSortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("three", 5);
        linkedMap.put("the", 3);
        linkedMap.put("The", 3);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        lengthSortingAlgorithm.startFrequency("the The The the three three three two two 1 1", SortEnum.ASC);

        assertEquals(list, lengthSortingAlgorithm.getHashMap());
    }



    @Test
    public void testFrequency_null_asc() throws FileNotFoundException {
        LengthSortingAlgorithm lengthSortingAlgorithm = new LengthSortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        lengthSortingAlgorithm.startFrequency("", SortEnum.ASC);

        assertEquals(list, lengthSortingAlgorithm.getHashMap());
    }*/
}