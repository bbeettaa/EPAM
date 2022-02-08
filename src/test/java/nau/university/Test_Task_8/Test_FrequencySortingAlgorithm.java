package nau.university.Test_Task_8;

        import nau.university.HW_Task_8.SortEnum;
        import nau.university.HW_Task_8.Task_1.FrequencySortingAlgorithm;
        import org.junit.Test;

        import java.io.FileNotFoundException;
        import java.util.*;

        import static org.junit.Assert.assertEquals;

public class Test_FrequencySortingAlgorithm  {

    @Test
    public void testFrequency_number_without_repeating_desc() throws FileNotFoundException {
        FrequencySortingAlgorithm frequencySortingAlgorithm = new FrequencySortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("3",1);
        linkedMap.put("2",1);
        linkedMap.put("1",1);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


        frequencySortingAlgorithm.startFrequency("1 2 3 4 5", SortEnum.DESC);

        assertEquals(list,frequencySortingAlgorithm.getHashMap());
    }

    @Test
    public void testFrequency_number_desc() throws FileNotFoundException {
        FrequencySortingAlgorithm frequencySortingAlgorithm = new FrequencySortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("4",2);
        linkedMap.put("3",2);
        linkedMap.put("2",2);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


        frequencySortingAlgorithm.startFrequency("1 2 3 4 5 5 3 2 4",SortEnum.DESC);

        assertEquals(list,frequencySortingAlgorithm.getHashMap());
    }

    @Test
    public void testFrequency_words_scattered_with_space_desc() throws FileNotFoundException {
        FrequencySortingAlgorithm frequencySortingAlgorithm = new FrequencySortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("panda",2);
        linkedMap.put("ezhik",2);
        linkedMap.put("apple",2);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        frequencySortingAlgorithm.startFrequency("apple ezhik panda age one    age panda apple ezhik    1 2 1 2    one",SortEnum.DESC);

        assertEquals(list,frequencySortingAlgorithm.getHashMap());
    }

    @Test
    public void testFrequency_null_desc() throws FileNotFoundException {
        FrequencySortingAlgorithm frequencySortingAlgorithm = new FrequencySortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        frequencySortingAlgorithm.startFrequency("",SortEnum.DESC);

        assertEquals(list,frequencySortingAlgorithm.getHashMap());
    }





    @Test
    public void testFrequency_number_without_repeating_asc() throws FileNotFoundException {
        FrequencySortingAlgorithm frequencySortingAlgorithm = new FrequencySortingAlgorithm();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("1",1);
        linkedMap.put("2",1);
        linkedMap.put("3",1);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());


        frequencySortingAlgorithm.startFrequency("1 2 3 4 5",SortEnum.ASC);

        assertEquals(list,frequencySortingAlgorithm.getHashMap());
    }


    @Test
    public void testFrequency_words_scattered_with_space_asc() throws FileNotFoundException {
        FrequencySortingAlgorithm frequencySortingAlgorithm = new FrequencySortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("panda",2);
        linkedMap.put("ezhik",2);
        linkedMap.put("apple",2);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        frequencySortingAlgorithm.startFrequency("apple ezhik panda age one    age panda apple ezhik    1 2 1 2    one",SortEnum.DESC);

        assertEquals(list,frequencySortingAlgorithm.getHashMap());
    }

    @Test
    public void testFrequency_null_asc() throws FileNotFoundException {
        FrequencySortingAlgorithm frequencySortingAlgorithm = new FrequencySortingAlgorithm();

        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(linkedMap.entrySet());

        frequencySortingAlgorithm.startFrequency("",SortEnum.ASC);

        assertEquals(list,frequencySortingAlgorithm.getHashMap());
    }
}