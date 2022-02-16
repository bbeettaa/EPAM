package ua.university.HW_Task_1.Task3;
import java.util.*;

public class Task3 {

    private static final Scanner sc= new Scanner(System.in);

    public static void main(String[] args) {
        int num;

        do {
            try {
                System.out.println("----------------");
                num = inputNum();
                printOddSum(num);
            } catch (InputMismatchException exception) {
                System.out.println("Error: input value not a number");
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
        while(exit());
    }
    static int inputNum() throws Exception {
        System.out.print("Input positive number: ");
        int num = sc.nextInt();

        if (num <= 0)
            throw new Exception("Wrong number, number must be positive");
        return num;
    }
    static boolean exit() {
        System.out.print("Exit the program? (yes/no): ");
        sc.nextLine();
        String str = sc.nextLine();

        if (Objects.equals(str.toLowerCase(), "y") || Objects.equals(str.toLowerCase(), "yes"))
            return false;
        else if (Objects.equals(str.toLowerCase(), "n") || Objects.equals(str.toLowerCase(), "no"))
            return true;
        return true;
    }
    static void printOddSum(int number)
    {
        int sumOdd=0;
        while(number > 0)
        {
            if((number % 10)%2==1)
                sumOdd+=number % 10;
            number /= 10;
        }
        System.out.print("sum of odd numbers is " + sumOdd + "\n");
    }
}
