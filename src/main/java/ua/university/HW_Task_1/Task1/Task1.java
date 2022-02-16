package ua.university.HW_Task_1.Task1;
// ctrl alt l
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Task1 {

    private static final Scanner sc= new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("----------------");
        System.out.print("Input a number: ");
        int number;

        try {
            number = sc.nextInt();

            if(number > 0)
                System.out.println("square of "+number+" is "+number*number);
            else if (number < 0)
                System.out.println("module of "+number+" is "+number * -1);
            else
                System.out.println("number is " + number);
        }
        catch (Exception exception)
        {
            System.out.println("Error: wrong number");
        }
        exit();
    }
    static void exit() throws IOException {
        System.out.print("Exit the program? (yes/no): ");

        String str = sc.next();

        if(Objects.equals(str.toLowerCase(), "y") || Objects.equals(str.toLowerCase(), "yes"))
            System.exit(0);
        else if(Objects.equals(str.toLowerCase(), "n") || Objects.equals(str.toLowerCase(), "no"))
            main(new String[0]);
    }
}
