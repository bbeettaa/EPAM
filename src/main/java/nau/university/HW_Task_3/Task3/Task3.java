package nau.university.HW_Task_3.Task3;

/*
Task 3.
Create  function  MultiArithmeticElements,  which  determines  the
multiplication  of  the  first  n  elements  of  arithmetic  progression  of  real
numbers with a given initial element of progression a1 and progression step
t. an is calculated by the formula (an+1 = an + t).
Example,
For a1 = 5, t = 3,  n = 4 multiplication equals to 5*8*11*14 = 6160
*/

public class Task3 {
    public static void main(String[] args) {
        int a,t,n;

        a=5;t=3;n=4;
        multiArithmeticElements(a,t,n);

        a=1;t=2;n=5;
        multiArithmeticElements(a,t,n);

        a=2;t=3;n=4;
        multiArithmeticElements(a,t,n);
    }

    public static void multiArithmeticElements(int a1, int t, int n) {
        System.out.printf("a1 = %d, t = %d, n = %d\n",a1,t,n);
        System.out.printf("%d * ",a1);
        int res=a1;

        for (int i = 1; i < n; i++) {
            a1+=t;
            res*=a1;

            if(i!=n-1)  System.out.printf("%d * ",a1);
            else System.out.printf("%d",a1);
        }
        System.out.printf(" = %d\n\n",res);
    }

}
