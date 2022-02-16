package ua.advanced.Practical2.Task2;

/*
  Task 2.2
  ——————————————————————————————
  Type names: Queue, QueueImpl
  ——————————————————————————————
  <p>
  2.2.1  Create Queue interface containing the following:
  <p>
  —————————————————-
  public interface Queue extends Container {
  <p>
  // Appends the specified element to the end.
  void enqueue(Object element);
  <p>
  // Removes the head.
  Object dequeue();
  <p>
  // Returns the head.
  Object top();
  }
  —————————————————-
  <p>
  2.2.2 Create QueueImpl class that implements the Queue interface.
  <p>
  In case the three elements A, B, C were added to the container using the ‘enqueue’ method, then:
  <p>
  1)	toString method should return the following string "[A, B, C]”
  2)	the elements iteration order should be: A B C
  <p>
  2.2.3 In the QueueImpl class, create ‘main’ method where you should demonstrate the functionality of:
  1)	all the methods from the Queue interface (including those inherited from Container and Interable);
  2)	all the methods of the Iterator interface (hasNext/next/remove).
  <p>
  _______________________
 */
