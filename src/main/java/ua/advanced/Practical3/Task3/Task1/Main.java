package ua.advanced.Practical3.Task3.Task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PairStringList ps = new PairStringList();
        ps.add("One");
        ps.add("two");
        ps.add("Three");
        ps.add("Four");

        Iterator i = ps.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println(i.next());

        System.out.println("-------------------");
        System.out.printf("add %s at index %d\n", "\"Zero\"",3);
        ps.add(3,"Zero");
        i = ps.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println(i.next());

        System.out.println("-------------------");
        System.out.println("deleted: "+ps.remove(1));

       i = ps.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println(i.next());

        System.out.println("-------------------");
        System.out.printf("index of %s is %d \n","\"Four\"",ps.indexOf("Four"));

        System.out.println("-------------------");
        List<String> collection = new LinkedList<>();
        collection.add("Seven");
        collection.add("Six");
        collection.add("Five");


        ps.addAll(collection);
        System.out.printf("Collection %s has added\n", collection.toString());
        i = ps.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println(i.next());

        System.out.println("-------------------");
        System.out.println("ascending sort");
        ps.sort(Comparator.naturalOrder());
        i = ps.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println(i.next());
    }

}
