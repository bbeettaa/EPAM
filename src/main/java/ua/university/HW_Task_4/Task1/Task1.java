package ua.university.HW_Task_4.Task1;
/*
Task 1.
Create Test for Task3.task1
*/


import static ua.university.HW_Task_4.ArraySort.*;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
    }

    public static void checkArr(SortEnum sortEnum, int[] arr) {
        IntStream.iterate(0,i->++i).limit(arr.length).forEach(i->System.out.printf("%d ",arr[i]));
        System.out.printf(" %s -> %s\n",sortEnum,isSort(sortEnum,arr));
    }


}