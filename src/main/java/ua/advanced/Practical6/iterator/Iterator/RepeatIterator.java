package ua.advanced.Practical6.iterator.Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RepeatIterator<T> implements Iterator<T> {
    int index;
    int repeatTime;
    int currentRepeat;
    T[] arr;

    public RepeatIterator(int repeatTime, T[] arr) {
        this.repeatTime = repeatTime;
        this.arr = arr;
        this.currentRepeat = 0;
    }

    @Override
    public boolean hasNext() {
        return index < arr.length && currentRepeat<repeatTime;
    }

    @Override
    public T next() {
        if(arr.length==0 || index == arr.length) throw new NoSuchElementException("Array is empty");
        T value = null;
        if (currentRepeat < repeatTime) {
            currentRepeat++;
            value = arr[index];
        }
        if (currentRepeat >= repeatTime) {
            currentRepeat=0;
            index++;
        }
        return value;
    }
}
