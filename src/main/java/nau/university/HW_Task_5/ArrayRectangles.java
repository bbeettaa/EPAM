package nau.university.HW_Task_5;

import java.util.AbstractMap;
import java.util.Map;

public class ArrayRectangles {
    private Rectangle[] rectangle_array;

    public ArrayRectangles(int length) {
        rectangle_array = new Rectangle[length];
    }

    public ArrayRectangles(Rectangle... rectangles) {
        rectangle_array = rectangles;
    }

    public boolean addRectangle(Rectangle rectangle) {
        for (int i = 0; i < rectangle_array.length; i++) {
            if (rectangle_array[i] == null) {
                rectangle_array[i] = rectangle;
                return true;
            }
        }
        return false;
            /*int[] newArr = new int[rectangle_array.length+1];
            System.arraycopy(rectangle_array,0,newArr,0,newArr.length+1);
            rectangle_array[rectangle_array.length-1]=rectangle;*/
    }

    public AbstractMap.Entry<Integer, Double> numberMaxArea() {
        return calculateMaxAreaOrMinPerimeter(true);
    }

    public AbstractMap.Entry<Integer, Double> numberMinPerimeter() {
        return calculateMaxAreaOrMinPerimeter(false);
    }

    private Map.Entry<Integer, Double> calculateMaxAreaOrMinPerimeter(boolean isCalcMax) {
        int index = 0;
        double returnValue = rectangle_array[0].perimeter();
        double currentValue;

        for (int i = 1; i < rectangle_array.length; i++) {
            if (isCalcMax) currentValue = rectangle_array[i].area();
            else currentValue = rectangle_array[i].perimeter();

            if ((currentValue > returnValue) == isCalcMax) {
                index = i;
                returnValue = currentValue;
            }
        }
        return new AbstractMap.SimpleEntry<>(index, returnValue);
    }

    public int numberSquare() {
        int num = 0;
        for (var rectangle : rectangle_array) {
            if (rectangle.isSquare())
                num++;
        }
        return num;
    }

}
