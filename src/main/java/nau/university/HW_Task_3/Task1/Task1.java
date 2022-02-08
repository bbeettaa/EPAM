package nau.university.HW_Task_3.Task1;
/*
Task 1.
Create function IsSorted, determining whether a given array of integer
values of arbitrary length is sorted in a given order (the order is set up by
enum value SortOrder). Array and sort order are passed by parameters.
Function does not change the array.
*/


import static nau.university.HW_Task_3.ArraySort.*;

import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
        SortEnum sortEnum;
        int[] arr;

        sortEnum = SortEnum.ASC;
        arr = new int[]{1, 1, 3, 4, 5, 6, 7, 8, 8};
        checkArr(sortEnum,arr);

        arr = new int[]{1, 0, 3, 4, 5, 6, 7, 8, 8};
        checkArr(sortEnum,arr);

        sortEnum = SortEnum.DESC;
        arr = new int[]{9, 9, 6, 6, 5, 4, 2, 2, 1};
        checkArr(sortEnum,arr);

        arr = new int[]{9, 10, 7, 6, 5, 4, 3, 2, 1};
        checkArr(sortEnum,arr);
    }

    public static void checkArr(SortEnum sortEnum, int[] arr) {
        IntStream.iterate(0,i->++i).limit(arr.length).forEach(i->System.out.printf("%d ",arr[i]));
        System.out.printf(" %s -> %s\n",sortEnum,isSort(sortEnum,arr));
    }


}