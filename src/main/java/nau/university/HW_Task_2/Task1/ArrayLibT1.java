package nau.university.HW_Task_2.Task1;

public class ArrayLibT1 {

    public static int[] resize(int[] arrNum,int newLength) {
        int[] newArr = new int[newLength];

        System.arraycopy(arrNum, 0, newArr, 0, arrNum.length);

        return newArr;
    }

    public static int[] swapEvenElementOfArray(int[] arrNum) {
        for(int i = 0; i < arrNum.length/2; i++)
        {
            if(arrNum[i] % 2==0 && arrNum[arrNum.length-i-1]%2==0)
            {
                arrNum[i]= arrNum[i] + arrNum[arrNum.length-i-1];
                arrNum[arrNum.length-i-1]= arrNum[i] - arrNum[arrNum.length-i-1];
                arrNum[i]= arrNum[i] - arrNum[arrNum.length-i-1];
            }
        }
        return arrNum;
    }

    public static void printArray(int[] arrNum) {
        for (int i : arrNum)
            System.out.print(i + " ");
        System.out.print("\n");
    }


}
