package ua.university.HW_Task_4.Task4;

/*
Task 4.
Create Test for Task3.task4
*/    

public class Task4 {
    public static void main(String[] args) {
    }

    public static int multiArithmeticElements(int a1, double t, int alim) throws Exception {
        if(t <= 0 || t >= 1)
            throw new Exception("The value 't' must be (0 < t < 1)");

        if (!(a1 * t >= alim))
            return 0;

        System.out.printf("a1 = %d, t = %f, alim = %d\n",a1,t,alim);
        int res=a1;

        while (a1*t >= alim) {
            System.out.printf("%d + ", a1);
            a1 *= t;
            res += a1;
        }

        System.out.printf("%d",a1);
        System.out.printf(" = %d\n\n",res);

        return res;
    }

}
