/*
Practical1 task #2
_______________________
Note: You are not allowed to use any types from java.util package except for these two:

1)	java.util.Iterator
2)	java.util.NoSuchElementException
_______________________
Create Container interface and place it in the root package (ua.advanced.practice1)
————————————————————
package ua.advanced.practice1.task2;
    public interface Container extends Iterable<Object> {
    // Removes all the elements.
    void clear();
    // Returns the number of elements.
    int size();
    // Returns a string representation of this container.
    String toString();
    // Returns an iterator over elements.
    // Iterator must implement the remove method.
    Iterator<Object> iterator();
 }
*/

package ua.advanced.Practical1;

import java.util.Iterator;

public interface Container extends Iterable<Object> {
    // Removes all the elements.
    void clear();

    // Returns the number of elements.
    int size();

    // Returns a string representation of this container.
    String toString();

    // Returns an iterator over elements.
    // Iterator must implement the remove method.
    Iterator<Object> iterator();
}