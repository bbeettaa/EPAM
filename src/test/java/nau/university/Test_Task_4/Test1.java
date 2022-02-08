package nau.university.Test_Task_4;

import static nau.university.HW_Task_4.ArraySort.SortEnum.ASC;
import static nau.university.HW_Task_4.ArraySort.SortEnum.DESC;
import static nau.university.HW_Task_4.ArraySort.isSort;
import static nau.university.HW_Task_4.Task1.Task1.checkArr;
import static org.junit.Assert.*;
import org.junit.Test;

public class Test1 {

    @Test
    public void isSortedTestPass()
    {
        assertTrue("1) isSorted", isSort(ASC, new int[]{-11, -1, 11, 11, 24, 100, 105, 999, 999, 1000}));
        assertFalse("2) isSorted", isSort(ASC, new int[]{1, 0, 3, 4, 4, 6, 7, 8, 8}));
        assertTrue("3) isSorted", isSort(DESC, new int[]{11, 9, 9, 6, 6, 5, 5, -1, -1}));
        assertFalse("4) isSorted", isSort(DESC, new int[]{-1, 0, 3, 2, 4, 6, 7, 8, 8}));

        assertTrue("5) isSorted", isSort(DESC, new int[]{-1}));
        assertTrue("6) isSorted", isSort(ASC, new int[]{-1}));

        assertTrue("7) isSorted", isSort(ASC, new int[5]));
        assertTrue("8) isSorted", isSort(DESC, new int[5]));
    }
    @Test
    public void isSortedTestThrow() {
        assertThrows("1) throw isSorted",
                IllegalArgumentException.class, () -> checkArr(ASC, new int[]{}));
        assertThrows("2) throw isSorted",
                IllegalArgumentException.class, () -> checkArr(ASC, new int[0]));

    }
}
