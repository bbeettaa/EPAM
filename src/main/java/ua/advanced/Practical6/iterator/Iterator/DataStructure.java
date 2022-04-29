package ua.advanced.Practical6.iterator.Iterator;

import java.util.Iterator;
import java.util.stream.IntStream;

public class DataStructure implements Iterable<String> {
    String[] columns;
    Integer[] rows;

    public DataStructure(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = IntStream.of(rows).boxed().toArray(Integer[]::new);
    }

    @Override
    public Iterator<String> iterator() {
        return new MyTableIterator(columns, rows);
    }

    static class MyTableIterator implements java.util.Iterator<String> {
        MyIterator<String> stringIterator;
        MyIterator<Integer> integerIterator;

        public MyTableIterator(String[] columns, Integer[] rows) {
            stringIterator = new MyIterator<>(columns);
            integerIterator = new MyIterator<Integer>(rows);
        }

        @Override
        public boolean hasNext() {
            return stringIterator.hasNext();
        }

        @Override
        public String next() {
            String value = "";

            value = stringIterator.current() + integerIterator.next();
            if (!integerIterator.hasNext()) {
                integerIterator.reset();
                stringIterator.next();
            }


            return value;
        }

        static class MyIterator<T> implements java.util.Iterator<T> {
            T[] arr;
            int index;

            public MyIterator(T[] arr) {
                this.arr = arr;
                index = 0;
            }

            @Override
            public boolean hasNext() {
                if (index < arr.length)
                    return true;
                return false;
            }

            @Override
            public T next() {
                return arr[index++];
            }

            public T current() {
                return arr[index];
            }

            public void reset() {
                index = 0;
            }
        }
    }
}
