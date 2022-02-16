package ua.university.Test_Task_4;

import org.junit.Test;

import static ua.university.HW_Task_4.ArraySort.SortEnum.ASC;
import static ua.university.HW_Task_4.ArraySort.SortEnum.DESC;
import static ua.university.HW_Task_4.Task2.Task2.transform;
import static org.junit.Assert.*;

public class Test2 {
    @Test
    public void transformTestPass()
    {
        assertArrayEquals("1) transform",
                new int[]{-1,2,5,7,9,11,13,15,16},transform(ASC,new int[]{-1, 1, 3, 4, 5, 6, 7, 8, 8}));
        assertArrayEquals("2) transform",
                new int[]{1, 0, 3, 4, 5, 6, 7, 8, 8},transform(ASC,new int[]{1, 0, 3, 4, 5, 6, 7, 8, 8}));
        assertArrayEquals("3) transform",
                new int[]{11, 10, 11, 9, 10, 10, 11, 6, 7},transform(DESC,new int[]{11,9, 9, 6, 6, 5,5,-1,-1}));
        assertArrayEquals("4) transform",
                new int[]{-1, 0, 3, 2, 4, 6, 7, 8, 8},transform(DESC,new int[]{-1, 0, 3, 2, 4, 6, 7, 8, 8}));

        assertArrayEquals("5) isSorted",new int[]{-1},transform(DESC,new int[]{-1}));
        assertArrayEquals("6) isSorted",new int[]{-1},transform(ASC,new int[]{-1}));

        assertArrayEquals("7) isSorted",new int[]{0,1,2,3,4},transform(ASC,new int[5]));
        assertArrayEquals("8) isSorted",new int[]{0,1,2,3,4},transform(DESC,new int[5]));
    }
    @Test
    public void transformTestThrow() {
        assertThrows("1) throw transform",
                IllegalArgumentException.class, () -> transform(ASC, new int[]{}));
        assertThrows("2) throw transform",
                IllegalArgumentException.class, () -> transform(ASC, new int[0]));
    }
}
