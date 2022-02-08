package nau.university.HW_Task_4;
//            for (int i = 1; i < arr.length; i++)
//                if(arr[i-1] > arr[i])
//                    return ;
//IntStream.of(arr).forEach(System.out::print);
//IntStream.iterate(arr[1],i->arr[i]).forEach(System.out::print);//noneMatch(i -> arr[i] < arr[i-1]) )
//IntStream.range(arr[0], arr[arr.length-1]).forEach(i->System.out.print(arr[i]));
//IntStream.iterate(0,i->i+=1).limit(arr.length).forEach(i->System.out.print(""+arr[i]));
import java.util.stream.IntStream;


public class ArraySort {
    public enum SortEnum{ASC, DESC}
    public static boolean isSort(SortEnum sortEnum, int[] arr) {
        boolean returnValue=false;
        if (sortEnum == SortEnum.ASC){
            returnValue = IntStream.iterate(1, i -> ++i).limit(arr.length - 1).noneMatch(i -> arr[i] < arr[i - 1]);
        }
        else if (sortEnum == SortEnum.DESC) {
            returnValue = IntStream.iterate(1, i -> ++i).limit(arr.length - 1).noneMatch(i -> arr[i] > arr[i - 1]);
        }
        return returnValue;
    }

}
