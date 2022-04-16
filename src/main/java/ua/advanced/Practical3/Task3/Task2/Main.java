package ua.advanced.Practical3.Task3.Task2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        MedianQueue mq = new MedianQueue();
        mq.clear();
        mq.addAll(Arrays.stream(new Integer[]{100,10,1}).toList());
        System.out.printf("%s -> %d\n",mq,mq.element());//[1, 10, 100] -> 10
        mq.clear();
        mq.addAll(Arrays.stream(new Integer[]{1,10,100}).toList());
        System.out.printf("%s -> %d\n",mq,mq.element());//[100, 10, 1] -> 10
        mq.clear();
        mq.addAll(Arrays.stream(new Integer[]{10,1,100}).toList());
        System.out.printf("%s -> %d\n",mq,mq.element());//[100, 1, 10] -> 10
        mq.clear();
        mq.addAll(Arrays.stream(new Integer[]{2,987,1}).toList());
        System.out.printf("%s -> %d\n",mq,mq.element());//[1, 987, 2] -> 2
        mq.clear();
        mq.addAll(Arrays.stream(new Integer[]{3,2,987,1}).toList());
        System.out.printf("%s -> %d\n",mq,mq.element());//[1, 987, 2, 3] -> 2
        mq.clear();
        mq.addAll(Arrays.stream(new Integer[]{3,2,4,987,1}).toList());
        System.out.printf("%s -> %d\n",mq,mq.element());//[1, 987, 4, 2, 3] -> 3
        mq.clear();
        mq.addAll(Arrays.stream(new Integer[]{3,3,3,2,1}).toList());
        System.out.printf("%s -> %d\n",mq,mq.element());//[1, 2, 3, 3, 3] -> 3

/*
1, 10, 100 → 10
100, 10, 1 → 10
100, 1, 10 → 10
1, 987, 2 → 2
1, 987, 2, 3 → 2
1, 987, 4, 2, 3 → 3
1, 2, 3, 3, 3 → 3
*/
    }
}
