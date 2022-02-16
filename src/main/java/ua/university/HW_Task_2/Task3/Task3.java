package ua.university.HW_Task_2.Task3;

/*
Task 3.
        In a predetermined two-dimensional integer array (square matrix) matrix
        insert  0  into  elements  to  the  left  side  of  the  main  diagonal,  and  1  into
        elements to the right side of the diagonal.
        Example,
                {{2, 4, 3, 3},
                 {5, 7, 8, 5},
                 {2, 4, 3, 3},
                 {5, 7, 8, 5}}

                        =>
                {{2, 1, 1, 1},
                 {0, 7, 1, 1},
                 {0, 0, 3, 1},
                 {0, 0, 0, 5}}

*/

import static ua.university.HW_Task_2.Task3.ArrayLibT3.*;

public class Task3 {
    public static void main(String[] args) {

            int[][] arrNum =
                    {{5,7,8,0},
                     {6,2,5,7},
                     {9,4,7,6},
                     {1,6,3,9}};

            System.out.print("Input array: \n");
            printArray(arrNum);
            System.out.print("-------------\n");
            deleteRepeatNum(arrNum);
            printArray(arrNum);


    }
}