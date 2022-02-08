package nau.university.HW_Task_4.Task3;
//
//Create Test for Task3.task3
//
public class Task3 {
    public static void main(String[] args) {
    }
    public static int multiArithmeticElements(int a1, int t, int n)
    {
        System.out.printf("a1 = %d, t = %d, n = %d\n",a1,t,n);
        if(n<=0) {
            System.out.printf(" = %d\n\n",0);
            return 0;
        }

        System.out.printf("%d * ",a1);
        int res=a1;

        for (int i = 0; i < n-1; i++) {
            a1+=t;
            res*=a1;

            if(i!=n-2)  System.out.printf("%d * ",a1);
            else System.out.printf("%d",a1);
        }
        System.out.printf(" = %d\n\n",res);
        return res;
    }
}
