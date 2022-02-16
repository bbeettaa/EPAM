package ua.advanced.Practical2.Task2;

import ua.advanced.Practical2.Task1.ListImpl;

import java.util.Iterator;

public class QueueImpl implements Queue {
    private ListImpl list;

    public QueueImpl(){
        clear();
    }

    public static void main(String[] args) {
        Queue queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");

        System.out.println(queue);

        System.out.printf("remove %s\n",queue.dequeue());
        System.out.printf("remove %s\n",queue.dequeue());

        System.out.println(queue);
        System.out.println("size - "+queue.size());

        System.out.printf("top element - %s\n",queue.top());

        //---------------------------------------------------------------
        System.out.println("\nIterator demonstration");
        ListImpl.IteratorImpl iterator = (ListImpl.IteratorImpl) queue.iterator();
        while(iterator.getCurrent()!=null) {
            System.out.println(iterator.getCurrentData());
            iterator.next();
        }
        iterator.reset();
        if(iterator.hasNext()) iterator.next();
        System.out.printf("current element -> %s\n",iterator.getCurrentData());
        System.out.printf("remove\n\n");
        iterator.remove();

        iterator.reset();
        while(iterator.getCurrent()!=null) {
            System.out.println(iterator.getCurrentData());
            iterator.next();
        }

    }

    @Override
    public void clear() {
        list = new ListImpl();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public ListImpl.IteratorImpl iterator() {
        return list.iterator();
    }

    @Override
    public void enqueue(Object element) {
        list.addLast(element);
    }

    @Override
    public Object dequeue() {
        Object obj=null;
        if(list.getFirst() != null) {
            obj = list.getFirst();
            list.removeFirst();
        }
        return obj;
    }

    @Override
    public Object top() {
        return list.getFirst();
    }

    @Override
    public String toString(){
        return list.toString();
    }
}
