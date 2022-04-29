package ua.advanced.Practical5.Task3;

import java.util.*;

public class MM {
    class Student{
        public Student(int r){
            rollNo = r;
        }
        int rollNo;
    }
    public static void main(String[] args) {
        Deque<String> d = new ArrayDeque<>();
        ArrayList<Integer> s=new ArrayList<>();
        HashSet<String> hs=new HashSet<>();
        HashSet<String> hs1=new LinkedHashSet<>();

        Set<String> sss= new TreeSet<>();
        sss.add("S");
        sss.add("R");

        Iterator<String> iter = sss.iterator();
        sss.add("P");
        sss.add("Q");
        while(iter.hasNext())
            System.out.println(iter.next()+" ");
    }
}
