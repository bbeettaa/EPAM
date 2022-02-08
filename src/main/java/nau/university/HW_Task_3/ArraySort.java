package nau.university.HW_Task_3;

import nau.university.HW_Task_3.Task1.*;
import java.util.stream.IntStream;


public class ArraySort {
    public enum SortEnum{ASC, DESC}
    public static boolean isSort(SortEnum sortEnum, int[] arr) {
        if (sortEnum == SortEnum.ASC){
//            for (int i = 1; i < arr.length; i++)
//                if(arr[i-1] > arr[i])
//                    return ;
            //IntStream.of(arr).forEach(System.out::print);
            //IntStream.iterate(arr[1],i->arr[i]).forEach(System.out::print);//noneMatch(i -> arr[i] < arr[i-1]) )
            //IntStream.range(arr[0], arr[arr.length-1]).forEach(i->System.out.print(arr[i]));
            //IntStream.iterate(0,i->i+=1).limit(arr.length).forEach(i->System.out.print(""+arr[i]));
            return IntStream.iterate(1, i -> ++i).limit(arr.length - 1).noneMatch(i -> arr[i] < arr[i - 1]);
        }
        else if (sortEnum == SortEnum.DESC)//desc
        {
            return IntStream.iterate(1, i -> ++i).limit(arr.length - 1).noneMatch(i -> arr[i] > arr[i - 1]);
        }

        throw new IllegalArgumentException();
    }

}
