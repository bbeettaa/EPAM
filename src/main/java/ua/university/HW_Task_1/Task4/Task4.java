package ua.university.HW_Task_1.Task4;
import java.util.*;

public class Task4 {

    private static final Scanner sc= new Scanner(System.in);

    public static void main(String[] args) {
        int num;

        do
        {
            try {
                System.out.println("----------------");
                num = inputNum();
                printConvertedNumAndAmountOfOne(num);
            }
            catch(InputMismatchException exception) {
                System.out.println("Error: input value not a number");
            }
            catch (Exception exception) {
                System.out.println("Error: "+ exception.getMessage());
            }
        }
        while(exit());
    }
    static int inputNum() throws Exception {
        System.out.print("Input positive number: ");
        int num;
        num = sc.nextInt();

        if(num <= 0)
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
    static void printConvertedNumAndAmountOfOne(int number)
    {
        int sumOfOnes=0;
        List<Integer> convertedNum = new ArrayList<>();

        while(number > 0) {
            convertedNum.add(number%2);
            if((number)%2==1)
                sumOfOnes+=1;
            number/=2;
        }

        for(int i = convertedNum.size()-1;i>=0;i--) {
            System.out.print(convertedNum.get(i));
            if(i%4==0) System.out.print(" ");
        }
        System.out.print("\nthe sum of one`s: "+sumOfOnes+"\n");
    }
}
