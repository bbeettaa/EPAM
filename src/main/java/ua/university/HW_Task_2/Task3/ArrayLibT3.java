package ua.university.HW_Task_2.Task3;

public class ArrayLibT3 {

    public static void printArray(int[][] arrNum) {
        for (int[] ints : arrNum) {
            for (int ii = 0; ii < arrNum[0].length; ii++)
                System.out.print(ints[ii] + " ");
            System.out.print("\n");
        }
    }
    public static int[][] deleteRepeatNum(int[][] arrNum) {
        for(int i = 0; i< arrNum.length; i++) {
            for (int ii = 0; ii < arrNum[1].length; ii++) {
                if(ii < i)
                    arrNum[i][ii]=0;
                else if(ii > i)
                    arrNum[i][ii]=1;
            }
        }

        return arrNum;
    }
}
