package ua.university.HW_Task_3.Task4;

/*
Task 4.
Create function SumGeometricElements, determining the sum of the first
elements of a decreasing geometric progression of real numbers with a
given initial element of a progression a1 and a given progression step t,
while the last element must be greater than a given alim. an is calculated
by the formula (an+1 = an * t), 0<t<1 .
Example,
For a progression, where a1 = 100, and t = 0.5, the sum of the first elements,
grater than alim = 20, equals to 100+50+25 = 175
*/    

public class Task4 {
    public static void main(String[] args) throws Exception {
        int a,alim;
        double t;

        a=100;t=0.5;alim=20;
        multiArithmeticElements(a,t,alim);

        a=30;t=0.2;alim=25;
        multiArithmeticElements(a,t,alim);

        a=169;t=0.5;alim=20;
        multiArithmeticElements(a,t,alim);
    }

    public static void multiArithmeticElements(int a1, double t, int alim) throws Exception {
        if(t < 0 || t > 1)
            throw new Exception("The value 't' must be (0 < t < 1)");

        if (!(a1 * t >= alim))
            return;

        System.out.printf("a1 = %d, t = %f, alim = %d\n",a1,t,alim);
        int res=a1;

        while (a1*t >= alim) {
            System.out.printf("%d + ", a1);
            a1 *= t;
            res += a1;
        }

        System.out.printf("%d",a1);
        System.out.printf(" = %d\n\n",res);
    }

}
