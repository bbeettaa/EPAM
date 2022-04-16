package ua.advanced.Practical3.Task2;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class IntStringCappedMap extends TreeMap<Integer,String> {
    int lastElement;
    String[] hashMap;
    public IntStringCappedMap(int capacity){
        hashMap = new String[capacity];
    }

    public AbstractSet entrySet(){
        return new AbstractSet() {
            @Override
            public Iterator iterator() {
                return new Iterator() {
                    private int index=0;
                    @Override
                    public boolean hasNext() {
                        return get(index)==null;
                    }
                    @Override
                    public Object next() {
                        do
                        index++;
                        while(hasNext());

                        return new Map.Entry<Integer,String>() {
                            @Override
                            public Integer getKey() {
                                return index;
                            }

                            @Override
                            public String getValue() {
                                return hashMap[index];
                            }

                            @Override
                            public String setValue(String value) {
                                return hashMap[index]=value;
                            }
                        };
                    }
                };
            }

            @Override
            public int size() {
                return lastElement;
            }
        };
    }

    public String get(int key){
        return hashMap[hashKey(key)];
    }
    public void put(int key, String value){
       hashMap[hashKey(key)] = value;
        lastElement++;
    }
    public int hashKey(int key){
        return key%hashMap.length;
    }
    public void remove (int key){
        hashMap[hashKey(key)] = null;
    }
    public int size(){return lastElement;}


}
