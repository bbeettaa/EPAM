package ua.advanced.Practical2.Task3;

import ua.advanced.Practical2.Task1.ListImpl;
import ua.advanced.Practical2.Task2.Queue;
import ua.advanced.Practical2.Task2.QueueImpl;

import java.util.Iterator;

public class StackImpl implements Stack {
    private ListImpl list;

    public StackImpl() {
        clear();
    }

    public static void main(String[] args) {
        Stack stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");

        System.out.println(stack);

        System.out.printf("remove %s\n",stack.pop());
        System.out.printf("remove %s\n",stack.pop());

        System.out.println(stack);
        System.out.println("size - "+stack.size());

        System.out.printf("top element - %s\n",stack.top());

        //---------------------------------------------------------------
        System.out.println("\nIterator demonstration");
        ListImpl.IteratorImpl iterator = (ListImpl.IteratorImpl) stack.iterator();
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
    public Iterator<Object> iterator() {
        return list.iterator();
    }

    @Override
    public void push(Object element) {
        list.addFirst(element);
    }

    @Override
    public Object pop() {
        Object obj  = list.getFirst();
        list.removeFirst();
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
