package nau.university.HW_Task_1.Task2;
import java.util.*;

import static java.util.Comparator.reverseOrder;

public class Task2 {

    private static final Scanner sc= new Scanner(System.in);

    public static void main(String[] args) {
        boolean loop = true;

        while(loop)
        {
            System.out.println("----------------");
            inputNum();

            loop = exit();
        }
    }
    static void inputNum() {
        System.out.print("Input number, where number is (100 <= n <= 999): ");
        int num;

        try {
            num = sc.nextInt();

            if(num >= 100 && num <=999)
                printPermutation(num);
            else
                throw new Exception("Wrong number");
        }
        catch(InputMismatchException exception) {
            System.out.println("Error: input value not a number");
        }
        catch (Exception exception) {
            System.out.println("Error: "+ exception.getMessage());
        }
    }
    static boolean exit(){
        System.out.print("Exit the program? (yes/no): ");
        sc.nextLine();
        String str = sc.nextLine();


        if (Objects.equals(str.toLowerCase(), "y") || Objects.equals(str.toLowerCase(), "yes"))
            return false;
        else if (Objects.equals(str.toLowerCase(), "n") || Objects.equals(str.toLowerCase(), "no"))
            return true;
        return true;
    }
    static void printPermutation(int number)
    {
        List<Integer> list = new ArrayList<>();

        while(number > 0)
        {
            list.add(number % 10);
            number /= 10;
        }

        list.sort(reverseOrder());

        for(var i: list)
        System.out.print(i);

        System.out.print("\n");
    }
}
