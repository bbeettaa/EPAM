package ua.advanced.Practical2.Task1;
/*
2.1.1 Create a List interface containing the following:
 —————————————————-
 package ua.advanced.practice2
 public interface List extends Container {

 // Inserts the specified element at the beginning.
 void addFirst(Object element);

 // Appends the specified element to the end.
 void addLast(Object element);

 // Removes the first element.
 void removeFirst();

 // Removes the last element.
 void removeLast();

 // Returns the first element.
 Object getFirst();

 // Returns the last element.
 Object getLast();

 // Returns the first occurrence of the specified element.
 // Returns null if no such element.
 // (use 'equals' method to check an occurrence)
 Object search(Object element);

 // Removes the first occurrence of the specified element.
 // Returns true if this list contained the specified element.
 // (use 'equals' method to check an occurrence)
 boolean remove(Object element);

 }

 —————————————————-

 Storage of the objects inside the container should be implemented by using set of nodes - instances of the Node class.
 Each node stores an object and a reference to the next node.
 Node class should be defined inside the ListImpl class (it should be a static nested class).

 The ‘iterator’ method should return an instance of the IteratorImpl class that implements java.util.Iterator<Object> interface.
 The IteratorImpl class should be defined inside the ListImpl class (it should be an inner class).

 In case the three elements A, B, C were added to the container using the ‘addLast’ method, then:
 1)	toString method should return the following string "[A, B, C]”
 2)	the elements iteration order should be: A B C

 2.1.2 In the ListImpl class, create ‘main’ method where you should demonstrate the functionality of:
 1)	all the methods from the List interface (including those inherited from Container and Iterable);
 2)	all the methods of the Iterator interface (hasNext/next/remove).

 _______________________
 */