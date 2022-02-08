package nau.university.Test_Task_9;

import nau.university.HW_Task_9.Matrix;
import org.junit.Test;

import static org.junit.Assert.*;

public class Task9_Matrix_Test {
    @Test
    public void testMatrix_() {
        Matrix matrix = new Matrix(new double[5][4]);
        System.out.println(matrix);
    }
    @Test
    public void testMatrix_ReceivingNumberRows() {
        Matrix matrix1 = new Matrix(new double[5][4]);

        int numberRows1 = matrix1.numberRows();

        assertEquals(5,numberRows1);
    }
    @Test
    public void testMatrix_ReceivingNumberRows_Throw() {
        assertThrows( ArrayIndexOutOfBoundsException.class,() -> new Matrix(new double[0][10]));
        assertThrows( ArrayIndexOutOfBoundsException.class,() -> new Matrix(new double[10][0]));
    }


    @Test
    public void testMatrix_ReceivingNumberColumns() {
        Matrix matrix1 = new Matrix(new double[5][4]);
        assertThrows( ArrayIndexOutOfBoundsException.class,() -> new Matrix(new double[10][0]));
        assertThrows( ArrayIndexOutOfBoundsException.class,() -> new Matrix(new double[0][0]));

        int numberColumns1 = matrix1.numberColumns();

        assertEquals(4,numberColumns1);
    }

    @Test
    public void testMatrix_SetElement() {
        Matrix matrix1 = new Matrix(5,2);

        matrix1.setElement(2,1,54);


        assertEquals(54.0,matrix1.getElement(2,1),1);
    }

    @Test
    public void testMatrix_SetElement_Throw() {
        Matrix matrix1 = new Matrix(5,2);
        assertThrows( ArrayIndexOutOfBoundsException.class,() -> matrix1.setElement(-1,-10,66));
    }


    @Test
    public void testMatrix_numberColumns() {
        Matrix matrix1 = new Matrix(new double[5][4]);
        int numberColumns1 = matrix1.numberColumns();
        assertEquals(4,numberColumns1);
    }

    @Test
    public void testMatrix_numberRows() {
        Matrix matrix1 = new Matrix(new double[5][4]);
        int numberRows1 = matrix1.numberRows();
        assertEquals(5,numberRows1);
    }





    @Test
    public void testMatrix_Addition() {
        double[][] mat1 = new double[][] {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,-2,-3,4,-5}};
        double[][] mat2 = new double[][] {{1,2,3,4,5},{0,1,2,3,4},{0,0,0,0,0},{1,2,3,4,5},{-1,2,-3,4,5}};

        Matrix matrix1 = new Matrix(mat1);
        Matrix matrix2 = new Matrix(mat2);

        Matrix result = matrix1.addition(matrix2);

        assertArrayEquals(new double[][] {{2,4,6,8,10},{1,3,5,7,9},{1,2,3,4,5},{2,4,6,8,10},{0,0,-6,8,0}},result.getMatrix());
    }
    @Test
    public void testMatrix_Addition_Throw() {
        double[][] mat1 = new double[][] {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,-2,-3,4,-5}};
        double[][] mat2 = new double[][] {{1,2,3,4,5,1},{0,1,2,3,4},{0,0,0,0,0},{1,2,3,4,5},{-1,2,-3,4,5}};

        Matrix matrix1 = new Matrix(mat1);
        Matrix matrix2 = new Matrix(mat2);

        Matrix result = matrix1.addition(matrix2);

        assertNull(result);
    }





    @Test
    public void testMatrix_Sub() {
        double[][] mat1 = new double[][] {{1,2,3,4,5},{1,1,3,2,5},{1,2,3,4,5},{0,0,0,0,0},{1,-2,-3,4,-5}};
        double[][] mat2 = new double[][] {{1,2,3,4,5},{0,1,2,3,4},{0,0,0,0,0},{1,2,3,4,5},{-1,2,-3,4,5}};

        Matrix matrix1 = new Matrix(mat1);
        Matrix matrix2 = new Matrix(mat2);

        Matrix result = matrix1.subtraction(matrix2);

        assertArrayEquals(new double[][] {{0,0,0,0,0},{1,0,1,-1,1},{1,2,3,4,5},{-1,-2,-3,-4,-5},{2,-4,0,0,-10}},result.getMatrix());
    }
    @Test
    public void testMatrix_Sub_Throw() {
        double[][] mat1 = new double[][] {{1,2,3,4,5},{1,1,3,2,5},{1,2,3,4,5},{0,0,0,0,0},{1,-2,-3,4,-5}};
        double[][] mat2 = new double[][] {{1,2,3,4,5,1},{0,1,2,3,4},{0,0,0,0,0},{1,2,3,4,5},{-1,2,-3,4,5}};

        Matrix matrix1 = new Matrix(mat1);
        Matrix matrix2 = new Matrix(mat2);

        Matrix result = matrix1.subtraction(matrix2);

        assertNull(result);
    }





    @Test
    public void testMatrix_Multi() {
        double[][] mat1 = new double[][] {{1,2,3,4,5},{1,1,3,2,5},{1,2,3,4,5},{0,0,0,0,0},{1,-2,-3,4,-5}};
        double[][] mat2 = new double[][] {{1},{1},{1},{1},{1}};

        Matrix matrix1 = new Matrix(mat1);
        Matrix matrix2 = new Matrix(mat2);

        Matrix result = matrix1.multiplication(matrix2);

        assertArrayEquals(new double[][] {{15},{12},{15},{0},{-5}},result.getMatrix());
    }
    @Test
    public void testMatrix_Multi2() {
        double[][] mat1 = new double[][] {{1,2,3,4,5},{1,1,3,2,5},{1,2,3,4,5},{0,0,0,0,0},{1,-2,-3,4,-5}};
        double[][] mat2 = new double[][] {{1,2,3},{1,0,0},{1,9,1},{1,1,1},{1,1,1}};

        Matrix matrix1 = new Matrix(mat1);
        Matrix matrix2 = new Matrix(mat2);

        Matrix result = matrix1.multiplication(matrix2);

        assertArrayEquals(new double[][] {{15,38,15},{12,36,13},{15,38,15},{0,0,0},{-5,-26,-1}},result.getMatrix());
    }
}
