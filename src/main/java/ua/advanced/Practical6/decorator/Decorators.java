package ua.advanced.Practical6.decorator;

import java.util.Iterator;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        int index=0;
        Iterator<String> iterator = sourceList.iterator();
        while (iterator.hasNext()){
            String element = iterator.next();
            if(isEven(index++))
                iterator.remove();
        }
        return sourceList;
    }

    public static boolean isEven(int num) {
        return (num & 1)!=0;
    }
}
/*
evenIndexElementsSubList - returns a decorator, that manages only the elements with even indices in a source list.
The decorated list should support the "read" methods: Decorated list should support "read" methods: get(), size(), iterator().
*/