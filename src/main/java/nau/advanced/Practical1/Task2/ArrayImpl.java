package nau.advanced.Practical1.Task2;

import nau.advanced.Practical1.Array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    int lastElement;
    Object[] array;

    public ArrayImpl(int size) {
        if(size<=0)
            size = 1;
        array = new Object[size];
    }

    public ArrayImpl() {
        this(10);
    }

    @Override
    public void add(Object element) {
        set(lastElement, element);
    }

    @Override
    public void set(int index, Object element) {
        if(isFull())
            resize(array.length*2);
        if(index < 0 || index > lastElement)
            throw new NoSuchElementException();

        System.arraycopy(array, index, array, index + 1, array.length - 1 - index);
        array[index] = element;
        lastElement++;
    }

    private void resize(int newSize){
        Object newArr[] = new Object[newSize];
        for (int i =0,j=0 ;i<newSize && j< array.length;i++,j++) {
            newArr[i]=array[j];
        }
       array=newArr;
    }

    @Override
    public Object get(int index) {
        if(index < 0 || index >= lastElement)
            throw new NoSuchElementException();
            return array[index];
    }

    @Override
    public int indexOf(Object element) {
        Iterator<Object> iterator = iterator();
        int index=0;
        while(iterator.hasNext()) {
            if(iterator.next().equals(element))
                return index;
            index++;
        }
            return -1;

//        while (iterator.hasNext())
//            if (iterator.next().equals(element))
//                return ((IteratorImpl) iterator).getIndex();

    }

    @Override
    public void remove(int index) {
        if(isEmpty())
            throw new NoSuchElementException();
        if(index < 0 || index >= lastElement)
            throw new NoSuchElementException();

        Iterator<Object> iterator = iterator();
        while(index>0){
            iterator.next();
            index--;
        }
        iterator.remove();

        lastElement--;
    }

    private boolean isEmpty() {
        return lastElement == 0;
    }

    private boolean isFull() {
        return lastElement >= array.length;
    }


    @Override
    public void clear() {
        array = new Object[array.length];
        lastElement = 0;
    }

    @Override
    public int size() {
        return lastElement;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    @Override
    public String toString() {
        Iterator<Object> iterator = iterator();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        while(iterator.hasNext()){
            stringBuilder.append(iterator.next());
            if(iterator.hasNext()) stringBuilder.append(", ");

        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public class IteratorImpl implements Iterator {
        private int index;

        public IteratorImpl() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size();
        }

        public Object next() {
            return get(index++);
        }

        public void remove() {
            array[index] = null;
            for (int i = index; i < lastElement - 1; i++) {
                array[i] = array[i + 1];
                array[i + 1] = null;
            }
        }
    }

}
