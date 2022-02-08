package nau.university.HW_Task_9;

import java.util.Arrays;

import static java.lang.Math.min;

public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        if (matrix.length < 1 || matrix[0].length < 1)
            throw new ArrayIndexOutOfBoundsException("The index of array is less than 1");
        this.matrix = matrix;
    }

    public Matrix(int row, int collum) {
        this(new double[row][collum]);
    }





    public Matrix addition(Matrix secondMatrix) {
        if (checkEqualMatrix(secondMatrix)) {
            Matrix newMatrix = new Matrix(this.numberRows(), this.numberColumns());

            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[0].length; j++)
                    newMatrix.setElement(i, j, matrix[i][j] + secondMatrix.getElement(i, j));

            return newMatrix;
        }

        return null;
    }

    public Matrix subtraction(Matrix secondMatrix) {
        if (checkEqualMatrix(secondMatrix)) {
            Matrix newMatrix = new Matrix(this.numberRows(), this.numberColumns());

            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[0].length; j++)
                    newMatrix.setElement(i, j, matrix[i][j] - secondMatrix.getElement(i, j));

            return newMatrix;
        }

        return null;
    }

    private boolean checkEqualMatrix(Matrix secondMatrix) {
        return matrix.length == secondMatrix.numberRows() && matrix[0].length == secondMatrix.numberColumns();
    }


    public Matrix multiplication(Matrix secondMatrix) {
        Matrix newMatrix = new Matrix( min(this.numberRows(),secondMatrix.numberRows()), min(this.numberColumns(),secondMatrix.numberColumns()));

        for(int i=0;i<numberRows();i++)
            for (int j = 0; j < secondMatrix.numberColumns(); j++)
                for (int k = 0; k < numberColumns(); k++)
                    newMatrix.setElement(i, j,  matrix[i][k] * secondMatrix.getElement(k, j) + newMatrix.getElement(i,j));

        return newMatrix;
    }


    private void checkRowCollum(int row, int collum) {
        if (row < 0 || collum < 0 ||
                row > numberRows() || collum > numberColumns())
            throw new ArrayIndexOutOfBoundsException("The indexer must be in [" + numberRows() + ", " + numberColumns() + "]");
    }

    public void setElement(int row, int collum, double value) {
        checkRowCollum(row, collum);
        matrix[row][collum] = value;
    }

    public double getElement(int row, int collum) {
        checkRowCollum(row, collum);
        return matrix[row][collum];
    }

    public int numberRows() {
        return this.matrix.length;
    }

    public int numberColumns() {
        return this.matrix[0].length;
    }


    @Override
    public String toString() {
        StringBuilder returnStr = new StringBuilder();
        for (var row : matrix) {
            returnStr.append(Arrays.toString(row)).append("\n");
        }
        return returnStr.toString();
    }

    /*
    @Override
        public String toString() {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < this.rows(); i++) {
                for (int j = 0; j < this.columns(); j++) {
                    try {
                        if (j != this.columns() - 1) {
                            builder.append(decimalFormat.format(getValue(i, j)) + " ");
                        } else {
                            builder.append(decimalFormat.format(getValue(i, j)));
                        }
                    } catch (MatrixException e) {
                        e.getMessage();
                    }
                }
                builder.append("\n");
            }
            return builder.toString();
        }
    */
    public double[][] getMatrix() {
        return matrix;
    }
}


/*

public class Matrix {







*/