package ua.advanced.Practical6.iterator;

import ua.advanced.Practical6.iterator.Iterator.DataStructure;
import ua.advanced.Practical6.iterator.Iterator.RepeatIterator;

import java.util.Iterator;
import java.util.stream.IntStream;

public class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new RepeatIterator<>(2,IntStream.of(array).boxed().toArray(Integer[]::new));
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new RepeatIterator<>(3,IntStream.of(array).boxed().toArray(Integer[]::new));
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new RepeatIterator<>(5,IntStream.of(array).boxed().toArray(Integer[]::new));
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return new DataStructure(columns, rows);
    }




}
