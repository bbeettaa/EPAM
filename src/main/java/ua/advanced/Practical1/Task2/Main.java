/*
1.2. Create ArrayImpl class that implements the Array interface.

Implement the storage of the objects inside the container using an array of objects.

The method ‘iterator’ should return an instance of the IteratorImpl class that implements java.util.Iterator<Object> interface.
IteratorImpl class should be defined inside the ArrayImpl class (it is to be an inner class).

In case the three elements A, B, C were added to the container using ‘add’ method, then:
1)	‘toString’ method should return the following string: "[A, B, C]”
2)	the elements iteration order should be: A B C

1.3. In the ArrayImpl class, create ‘main’ method where you should demonstrate the functionality of:
1)	all the methods of the Array interface (including those inherited from Container and Iterable);
2)	all the methods of the Iterator interface (hasNext/next/remove).
_______________________
*/

package ua.advanced.Practical1.Task2;

import ua.advanced.Practical1.Task1.residents.cats.Cat;
import ua.advanced.Practical1.Task1.residents.cats.Kitten;
import ua.advanced.Practical1.Task1.residents.dogs.Dog;
import ua.advanced.Practical1.Task1.residents.dogs.Puppy;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        ArrayImpl array = new ArrayImpl();

        //
        //Array methods
        //
        System.out.printf("The value '%s' has been added\n", "1");
        array.add("1");
        System.out.printf("The value '%s' has been added\n", "2");
        array.add("2");
        System.out.printf("The value '%s' has been added\n", "3");
        array.add("3");
        System.out.println(array + "\n");

        array.set(1, "4");
        System.out.printf("The value '%s' has been set to %d position\n", "4", 1);
        array.set(3, "5");
        System.out.printf("The value '%s' has been set to %d position\n", "5", 3);
        System.out.println(array + "\n");

        int index = 0;
        System.out.printf("The value at index %d is '%s'\n", index, array.get(index));
        index = 4;
        System.out.printf("The value at index %d is '%s'\n\n", index, array.get(index));


        System.out.printf("Index of '%s' is %d\n", "4", array.indexOf("4"));
        System.out.printf("Index of '%s' is %d\n\n", "5", array.indexOf("5"));


        index = 0;
        System.out.printf("Value at index %d = '%s' removed.\n", index, array.get(index));
        array.remove(index);
        try {
            index = 4;
            array.remove(index);
            System.out.printf("Value at index %d = '%s' removed.\n", index, array.get(index));
        } catch (NoSuchElementException ex) {
            System.out.printf("%s\n", ex);
        }
        System.out.println(array + "\n");

        System.out.printf("Array has been cleared\n");
        array.clear();
        System.out.println(array + "\n");

        //
        //Iterator methods
        //
        System.out.println("\n\n\tIterator methods");
        array.add(new Dog("Rax"));
        array.add(new Puppy("Randy"));
        array.add(new Cat("Barbos"));
        array.add(new Kitten("Murzik"));

        ArrayImpl.IteratorImpl iterator = array.new IteratorImpl();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "; ");
        }
        System.out.print("\n");
        iterator = array.new IteratorImpl();

        iterator.next();
        iterator.next();
        iterator.remove();

        iterator = array.new IteratorImpl();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "; ");
        }
    }
}
