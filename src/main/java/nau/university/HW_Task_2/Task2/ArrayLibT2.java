package nau.university.HW_Task_2.Task2;


public class ArrayLibT2 {

    public static int[] createArrayOfDistance(int[] arrNum, int[] numAndDistance) {
        int firstIndex, secondIndex, distance;
        for (int i: arrNum) {
            firstIndex = firstIndexOf(i, arrNum);
            secondIndex = lastIndexOf(i, arrNum);
            distance= secondIndex - firstIndex;

            if(distance!= 0)
            {
                numAndDistance = resize(numAndDistance, numAndDistance.length+2);
                numAndDistance[numAndDistance.length-2] = i;
                numAndDistance[numAndDistance.length-1] = distance;
            }

        }
        return numAndDistance;
    }
    public static int[] delete(int index,int[] arrNum) {
        int[] newArr = new int[arrNum.length-1];

        for (int i=0, j=0;i<newArr.length;i++,j++) {
            if(i==index)
                j++;
            newArr[i]=arrNum[j];
        }

        return newArr;
    }
    public static int lastIndexOf(int ch,int[] arrNum) {
        for (int i = arrNum.length-1; i >= 0 ;i--) {
            if(arrNum[i]==ch)
                return i;
        }
        return -1;
    }
    public static int firstIndexOf(int ch,int[] arrNum) {
        for (int i = 0;i<arrNum.length;i++) {
            if(arrNum[i]==ch)
                return i;
        }
        return -1;
    }
    public static int[] resize(int[] arrNum,int newLength) {
        int[] newArr = new int[newLength];

        System.arraycopy(arrNum, 0, newArr, 0, arrNum.length);

        return newArr;
    }
    public static void printArray(int[] arrNum) {
        for (int j : arrNum) System.out.print(j + " ");
        System.out.print("\n");
    }
    public static int[] deleteRepeatNum(int[] numAndDistance) {
        for (int k = 0; k < numAndDistance.length ; k+=2 ) {
            for (int kk = 2; kk < numAndDistance.length-1 ; kk+=2)
                if(numAndDistance[k]== numAndDistance[kk]) {
                    numAndDistance = delete(kk, numAndDistance);
                    numAndDistance = delete(kk, numAndDistance);
                }
        }
        return numAndDistance;
    }
    public static int[] deleteMinNums(int[] numAndDistance) {
        int min = numAndDistance[0];
        for (int i = 0; i < numAndDistance.length ; i+=2 ) {

            if(numAndDistance[i] < min) {
                numAndDistance = delete(i, numAndDistance);
                numAndDistance = delete(i, numAndDistance);
            }
            if(numAndDistance[i] > min) {
                min=numAndDistance[i];
                i=-2;
            }

        }
        return numAndDistance;
    }
}
