package ua.advanced.Practical3.Task3.Task1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PairStringList implements List<String> {
    private Node first;

    public PairStringList() {
        first = null;
    }


    @Override
    public boolean add(String s) {
        if (first == null) {
            first = new Node(s);
            first.next=new Node(s);
            return true;
        } else {
            Node cur = first;
            while (cur.next != null)
                cur = cur.next;
            cur.next = new Node(s);
            cur.next.next = new Node(s);
            return true;
        }
    }

    @Override
    public void add(int index, String element) {
        //if (indexOf(element) == -1) return;
        if(index>=size()/2);

        int indexEl = 0;
        Node cur = first;
        Node next = first;
        while ((indexEl++) != index-1)
            cur = cur.next.next;

        next = cur.next.next;
        cur.next.next = new Node(element);
        cur.next.next.next = new Node(element);
        cur.next.next.next.next = next;
    }

    @Override
    public boolean remove(Object o) {
        Node cur = first;
        if(cur.data.equals(o)){
            first = first.next.next;
            return true;
        }

        while (cur.next!=null) {
            if(cur.next.data.equals(o)) {
                cur.next=cur.next.next.next;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String remove(int index) {
        if(index>=size()) return null;
String data = get(index);
        remove(data);
            return data;
    }

    @Override
    public String get(int index) {
        if(index>= size() ) return null;

        int ind=0;
        Node cur = first;
        while (ind!=index) {
            ind++;
            cur = cur.next;
        }

        return cur.data;
    }

    @Override
    public String set(int index, String element) {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        for (var obj: c) {
            add(obj);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null && current.next != null;
            }

            @Override
            public String next() {
                if(current == null) return null;
                String data = current.data;
                current = current.next;
                return data;
            }

            @Override
            public void remove() {
                Node previous = first;
                while (previous.next.data != current.data)
                    previous = previous.next;

                previous.next = current.next;
            }
        };

    }

    @Override
    public void sort(Comparator<? super String> c) {
        List<String> arr = Arrays.stream(toArray()).toList();
        first = null;
        addAll(arr.stream().sorted().collect(Collectors.toList()));
    }

    @Override
    public int size() {
        int size=0;
        Node cur = first;
        while(cur!=null) {
            size++;
            cur = cur.next;
        }
        return size;
    }

    @Override
    public void clear() {
first = null;
    }

    @Override
    public int indexOf(Object o) {
        Iterator i = iterator();
        int index = 0;
        while (i.hasNext()) {
            index++;
            boolean value = i.next().equals(o);
            //i.next();
            if (value) return index/2;
        }
        i.next();
        if(i.next()!= null)
            if (i.next().equals(o)) return index;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public String[] toArray() {
        Node cur = first;
        String[] arr = new String[size()/2];
        int index =0;
        while(cur!=null) {
            arr[index++] = cur.data;
        cur=cur.next.next;
        }
        //arr[index++] = cur.data;
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ListIterator<String> listIterator() {
        return null;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return null;
    }

    static class Node {
        Node next = null;
        String data = null;

        Node(String data) {
            this.data = data;
        }
    }
}
