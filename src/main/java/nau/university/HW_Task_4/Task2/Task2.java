package nau.university.HW_Task_4.Task2;

/*
Task 2.
Create Test for Task3.task2
*/

import java.util.stream.IntStream;
import static nau.university.HW_Task_4.ArraySort.*;


public class Task2 {
    public static void main(String[] args) {
    }

    public static int[] transform(SortEnum sortOrder, int[] arr) {
        if(isSort(sortOrder,arr)) {
            IntStream.iterate(0, i->++i).limit(arr.length).forEach(i->arr[i]=arr[i]+i);
            return arr;
        }
        return arr;
    }
}

