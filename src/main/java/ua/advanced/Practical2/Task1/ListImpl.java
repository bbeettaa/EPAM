package ua.advanced.Practical2.Task1;

import java.util.Iterator;

public class ListImpl implements List {
    private IteratorImpl iterator;
    private Node first;

    public ListImpl() {
        first = null;
        iterator = iterator();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addFirst("A");
        list.addFirst("B");
        list.addLast ("C");
        list.addLast ("D");
        list.addLast ("E");
        list.addLast ("F");
        list.addFirst ("G");

        System.out.println(list);

        System.out.println("remove last");
        list.removeLast();
        System.out.println("remove first");
        list.removeFirst();

        System.out.println(list);
        System.out.println("size - "+list.size());

        System.out.printf("first element - %s,\nlast element - %s\n",list.getFirst(),list.getLast());

        System.out.printf("remove element 'E' -> %s\n",list.remove("E"));
        System.out.println(list);
        System.out.printf("find element %s\n",list.search("A"));
        System.out.printf("next element %s\n",list.iterator.next().data);
        //---------------------------------------------------------------
        System.out.println("\nIterator demonstration");
        IteratorImpl iterator = list.iterator();
        while(iterator.current!=null) {
            System.out.println(iterator.current.data);
            iterator.next();
        }
        iterator.reset();
        if(iterator.hasNext()) iterator.next();
        if(iterator.hasNext()) iterator.next();
        System.out.printf("current element -> %s\n",iterator.current.data);
        System.out.printf("remove\n\n");
        iterator.remove();

        iterator.reset();
        while(iterator.current!=null) {
            System.out.println(iterator.current.data);
            iterator.next();
        }

    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public int size() {
        iterator.reset();
        int size = 0;
        while (iterator.current != null) {
            iterator.next();
            size++;
        }
        return size;
    }

    @Override
    public IteratorImpl iterator() {
        return new IteratorImpl();
    }

    @Override
    public void addFirst(Object element) {
        Node node = new Node(element);

        node.next = first;
        first = node;
    }

    @Override
    public void addLast(Object element) {
        iterator.reset();
        if (iterator.current == null) {
            addFirst(element);
            return;
        }
        while (iterator.hasNext())
            iterator.next();
        iterator.current.next = new Node(element);
    }

    @Override
    public void removeFirst() {
        first = first.next;
    }

    @Override
    public void removeLast() {
        iterator.reset();
        while (iterator.hasNext())
            iterator.next();
        iterator.remove();
    }

    @Override
    public Object getFirst() {
        if(first==null)
            return null;
        return first.data;
    }

    @Override
    public Object getLast() {
        iterator.reset();
        while (iterator.hasNext())
            iterator.next();
        Object value = iterator.getCurrent().data;
        return value;
    }

    @Override
    public Object search(Object element) {
        Object value;
        iterator.reset();

        while (iterator.current != null) {
                if (!iterator.current.data.equals(element))
                    iterator.next();
                else {
                    value = iterator.current.data;
                    return value;
                }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (search(element) != null) {
            iterator.remove();
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder().append('[');
        iterator.reset();
        while(iterator.current!=null) {
            str.append(iterator.current.data.toString()+", ");
            iterator.next();
        }
        str.delete(str.length()-2,str.length());
        str.append(']');
        return String.valueOf(str);
    }

    static class Node {
        Node next = null;
        Object data = null;

        Node(Object data) {
            this.data = data;
        }
    }

    public class IteratorImpl implements Iterator<Object> {
        private Node current;

        public IteratorImpl() {
            reset();
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Node next() {
            return current = current.next;
        }

        public void reset() {
            current = first;
        }

        public Node getCurrent() {
            return current;
        }

        public Object getCurrentData(){
            return current.data;
        }

        @Override
        public void remove() {
            Node previous = first;
            IteratorImpl iter = new IteratorImpl();
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
