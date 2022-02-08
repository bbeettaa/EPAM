package nau.university.HW_Task_3.Task2;

/*
Task 2.
Create  function  Transform,  replacing  the  value  of  each  element  of  an
integer array with the sum of this element value and its index, only if the
given array is sorted in the given order (the order is set up by enum value
SortOrder).
Array and sort order are passed by parameters. To check, if
the array is sorted, the function IsSorted from the Task 1 is called.
Example,
For {5, 17, 24, 88, 33, 2} and “ascending” sort order values in the array do
not change;
For  {15,  10,  3}  and  “ascending”  sort  order  values  in  the  array  do  not
change;
For {15, 10, 3} and “descending” sort order the values in the array are
changing to {15, 11, 5}
*/

import java.util.stream.IntStream;

import static nau.university.HW_Task_3.ArraySort.*;


public class Task2 {
    public static void main(String[] args) {
        SortEnum sortEnum;
        int[] arr;

        sortEnum = SortEnum.ASC;
        arr = new int[]{1, 1, 3, 4, 5, 6, 7, 8, 8};
        transform(sortEnum,arr);

        arr = new int[]{1, 0, 3, 4, 5, 6, 7, 8, 8};
        transform(sortEnum,arr);

        sortEnum = SortEnum.DESC;
        arr = new int[]{9, 9, 6, 6, 5, 4, 2, 2, 1};
        transform(sortEnum,arr);

        arr = new int[]{9, 10, 7, 6, 5, 4, 3, 2, 1};
        transform(sortEnum,arr);
    }

    private static void transform(SortEnum sortEnum, int[] arr) {
        IntStream.iterate(0, i->++i).limit(arr.length).forEach(i->System.out.printf("%d\t",arr[i]));
        if(isSort(sortEnum,arr)) {
            System.out.printf(" %s -> %s\n",sortEnum,isSort(sortEnum,arr));
            IntStream.iterate(0, i->++i).limit(arr.length).forEach(i->arr[i]=arr[i]+i);
            IntStream.iterate(0, i->++i).limit(arr.length).forEach(i->System.out.printf("%d\t",arr[i]));
            System.out.print("\n\n");
        }
        else
            System.out.printf(" %s -> %s\n\n",sortEnum,isSort(sortEnum,arr));
    }
}

