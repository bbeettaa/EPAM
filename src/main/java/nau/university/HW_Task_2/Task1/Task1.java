package nau.university.HW_Task_2.Task1;

import java.util.Scanner;
import static nau.university.HW_Task_2.Task1.ArrayLibT1.*;

public class Task1 {
    /*
    Task 1.
            In a given array of integers nums swap values of the first and the last array
            elements, the second and the penultimate etc., if the two exchanged values
            are even.
            Example,
                    {10, 5, 3, 4} =>  {4, 5, 3, 10}
            {100, 2, 3, 4, 5} => {100, 4, 3, 2, 5}
            {100, 2, 3, 45, 33, 8, 4, 54} => {54, 4, 3, 45, 33, 8, 2, 100}

    */
    public static void main(String[] args) {
        do {

            int[] arrNum = createArray();
            printArray(arrNum);
            swapEvenElementOfArray(arrNum);
            printArray(arrNum);


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
