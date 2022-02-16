package ua.university.Test_Task_8;

import ua.university.HW_Task_8.SortEnum;
import ua.university.HW_Task_8.Task_3.DuplicatesSortingAlgorithm;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Test_DuplicatesSortingAlgorithm {
        @Test
        public void testDuplicates_number_without_repeating_desc() throws FileNotFoundException {
            DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();
            Map<String, Integer> linkedMap = new LinkedHashMap<>();
            List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

            duplicatesSortingAlgorithm.startFrequency("one two three four", SortEnum.DESC);

            assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
        }

        @Test
        public void testDuplicates_number_desc() throws FileNotFoundException {
            DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();
            Map<String, Integer> linkedMap = new LinkedHashMap<>();
            linkedMap.put("2",2);
            linkedMap.put("3",2);
            linkedMap.put("4",2);
            List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


            duplicatesSortingAlgorithm.startFrequency("1 2 3 4 5 5 3 2 4",SortEnum.DESC);

            assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
        }

        @Test
        public void testDuplicates_words_scattered_with_space_desc() throws FileNotFoundException {
            DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();

            Map<String, Integer> linkedMap = new LinkedHashMap<>();
            linkedMap.put("ELPPA",2);
            linkedMap.put("KIHZE",2);
            linkedMap.put("ADNAP",2);
            List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

            duplicatesSortingAlgorithm.startFrequency("apple ezhik panda age one    age panda apple ezhik    1 2 1 2    one",SortEnum.DESC);

            assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
        }

        @Test
        public void testDuplicates_null_desc() throws FileNotFoundException {
            DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();

            Map<String, Integer> linkedMap = new LinkedHashMap<>();
            List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

            duplicatesSortingAlgorithm.startFrequency("",SortEnum.DESC);

            assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
        }







    @Test
    public void testDuplicates_number_without_repeating_asc() throws FileNotFoundException {
        DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        duplicatesSortingAlgorithm.startFrequency("one two three four", SortEnum.ASC);

        assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
    }

    @Test
    public void testDuplicates_number_asc() throws FileNotFoundException {
        DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("2",2);
        linkedMap.put("3",2);
        linkedMap.put("4",2);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


        duplicatesSortingAlgorithm.startFrequency("1 2 3 4 5 5 3 2 4",SortEnum.ASC);

        assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
    }

    @Test
    public void testDuplicates_words_scattered_with_space_asc() throws FileNotFoundException {
        DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("ELPPA",2);
        linkedMap.put("KIHZE",2);
        linkedMap.put("ADNAP",2);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        duplicatesSortingAlgorithm.startFrequency("apple ezhik panda age one    age panda apple ezhik    1 2 1 2    one"
                ,SortEnum.ASC);

        assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
    }

    @Test
    public void testDuplicates_null_asc() throws FileNotFoundException {
        DuplicatesSortingAlgorithm duplicatesSortingAlgorithm = new DuplicatesSortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        duplicatesSortingAlgorithm.startFrequency("",SortEnum.ASC);

        assertEquals(list,duplicatesSortingAlgorithm.getHashMap());
    }
}
