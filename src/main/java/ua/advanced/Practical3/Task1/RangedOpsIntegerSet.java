package ua.advanced.Practical3.Task1;

import ua.advanced.Practical2.Task1.ListImpl;

import java.util.Iterator;

public class RangedOpsIntegerSet implements Iterable<Integer> {
    Node first;
    private final IteratorImpl iterator;

    public RangedOpsIntegerSet() {
        first = null;
        iterator = (IteratorImpl) iterator();
    }

    public static void main(String[] args) {
        RangedOpsIntegerSet set = new RangedOpsIntegerSet();
        set.add(1, 5);
        for (Integer el : set) {
            System.out.println(el);
        }
        System.out.println("---------------------");
        set = new RangedOpsIntegerSet();
        set.add(1, 5);
        set.add(9, 11);
        for (Integer el : set) {
            System.out.println(el);
        }
        System.out.println("---------------------");
        set = new RangedOpsIntegerSet();
        set.add(1, 15);
        set.remove(3, 12);
        for (Integer el : set) {
            System.out.println(el);
        }

    }

    boolean add(int singleValue) {
        return false;
    }

    boolean remove(int singleValue) {
        return false;
    }

    boolean add(int fromInclusive, int toExclusive) {
        iterator.reset();
        if(first==null) {
            first = new Node(fromInclusive++);
            iterator.reset();
        }

        while ( iterator.current != null && iterator.hasNext())
            iterator.next();


       //iterator.current = new Node(fromInclusive++);
        int count=toExclusive-fromInclusive;
        while(count>=0) {
            count--;
            iterator.current.next = new Node(fromInclusive++);
            iterator.next();
        }
        return true;
    }

    boolean remove(int fromInclusive, int toExclusive) {
        iterator.reset();
        while(iterator.hasNext() && iterator.current.data != fromInclusive){
                iterator.next();
        }
        while(iterator.current.data != toExclusive&&iterator.hasNext()  ) {
            iterator.remove();
            iterator.next();
        }

        return true;
    }

    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    int size() {
        int size = 0;
        while (iterator.current != null) {
            iterator.next();
            size++;
        }
        return size;
    }

    static class Node {
        Node next = null;
        int data ;
        Node(int data) {
            this.data = data;
        }
    }
    class IteratorImpl<I extends Number> implements Iterator<Object> {
        private Node current;

        public IteratorImpl() {
            reset();
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Integer next() {
                Integer data = current.data;
                current = current.next;
                return data;

        }

        public void reset() {
            current = first;
        }

        public Node getCurrent() {
            return current;
        }

        public Object getCurrentData() {
            return current.data;
        }

        @Override
        public void remove() {
            Node previous = first;
            IteratorImpl<Number> iter = new IteratorImpl<Number>();
            while (iter.hasNext()) {
                if (iter.current.next.equals(current)) {
                    previous = iter.current;
                    previous.next = current.next;
                    return;
                }
                iter.next();
            }


        }
        //private ListImpl ourList;


    }
}
