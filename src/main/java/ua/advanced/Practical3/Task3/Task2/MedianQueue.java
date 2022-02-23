package ua.advanced.Practical3.Task3.Task2;

import java.util.*;
import java.util.stream.Collectors;

public class MedianQueue implements Queue<Integer> {
    private Node first;

    public MedianQueue() {
        first = null;
    }


    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        return offer(integer);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        for (var i: c) {
            offer(i);
        }
        return true;
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
    public void clear() {
        first = null;
    }

    @Override
    public Integer remove() {
        Integer data = first.data;
        first = first.next;
        return data;
    }


    @Override
    public Integer[] toArray() {
        Node cur = first;
        Integer[] arr = new Integer[size()];
        int index = 0;
        while (cur != null) {
            arr[index++] = cur.data;
            cur = cur.next;
        }
        //arr[index++] = cur.data;
        return arr;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null && current.next != null;
            }

            @Override
            public Integer next() {
                if (current == null) return null;
                Integer data = current.data;
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
    public int size() {
        int size = 0;
        Node cur = first;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }

    @Override
    public boolean offer(Integer integer) {
        Node node = new Node(integer);
        node.next = first;
        first = node;
        return true;
    }

    @Override
    public Integer poll() {
        return remove();
    }

    @Override
    public Integer element() {
        Integer[] arr = toArray();
        Arrays.sort(arr);
        int index = (size()%2==0)? size()/2-1: (size()/2);
        return arr[index];
    }

    @Override
    public Integer peek() {
        return first.data;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node cur = first;
        while(cur!= null) {
            sb.append(cur.data + ", ");
            cur = cur.next;
        }
        sb.replace(sb.length()-2,sb.length(),"");
        sb.append("]");
        return String.valueOf(sb);
    }

    static class Node {
        Node next = null;
        Integer data = null;

        Node(Integer data) {
            this.data = data;
        }
    }

}
