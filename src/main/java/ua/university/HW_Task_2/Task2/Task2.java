package ua.university.HW_Task_2.Task2;
/*
Task 2.
        In a given array of integers num calculate integer result value, that is
        equal to the distance between the first and the last entry of the maximum
        value in the array.
        Example,
        {4, 100, 3, 4} result = 0
        {5, 50, 50, 4, 5} result = 1
        {5, 350, 350, 4, 350} result = 3
        {10, 10, 10, 10, 10} result = 4
*/

import java.util.Scanner;
import static ua.university.HW_Task_2.Task2.ArrayLibT2.*;

public class Task2 {
    public static void main(String[] args) {
        do {
            int[] arrNum = createArray();
            int[] numAndDistance = new int[0];

            System.out.print("Input array: ");
            printArray(arrNum);

            numAndDistance = createArrayOfDistance(arrNum, numAndDistance);
            numAndDistance = deleteRepeatNum(numAndDistance);
            numAndDistance = deleteMinNums(numAndDistance);

            for (int i = 0; i < numAndDistance.length; i++) {
                i++;
                System.out.print("Number: " + numAndDistance[i - 1] + ", Distance: " + numAndDistance[i] + "\n");
            }

        } while (!isExit());
    }
    public static int[] createArray() {
        int[] arrNum = new int[0];
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the number to add to array or type 'end' to create an array: ");

        while(true)
        {
            String str = sc.nextLine();

            if (str.contains("end"))
                break;
            else {
                arrNum=resize(arrNum, arrNum.length+1);
                arrNum[arrNum.length - 1] = Integer.parseInt(str);
            }
        }
        return arrNum;
    }
    public static boolean isExit() {
        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Exit? (y/n): ");

            String exit = sc.nextLine();

            if (exit.contains("n")) {
                System.out.println("--------------------------------");
                return false;
            }
            else if (exit.contains("y"))
                return true;
            else
                System.out.print("Unknown command...");
        }
    }
}