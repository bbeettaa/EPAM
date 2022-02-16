package ua.advanced.Practical2.Task3;
/*
  Task 2.3
  ——————————————————————————————
  Type names: Stack, StackImpl
  ——————————————————————————————
  <p>
  2.3.1.  Create Stack interface containing the following:
  <p>
  —————————————————-
  package ua.advanced.practice2;
  <p>
  public interface Stack extends Container {
  <p>
  // Pushes the specified element onto the top.
  void push(Object element);
  <p>
  // Removes and returns the top element.
  Object pop();
  <p>
  // Returns the top element.
  Object top();
  <p>
  }
  —————————————————-
  <p>
  2.3.2 Create StackImpl class that implements the Stack interface.
  <p>
  In case the three elements  A, B, C were added to the container using ‘push’ method , then:
  1)   toString method should return the following string "[A, B, C]”
  3)	the elements iteration order should be: C B A
  <p>
  2.3.3 In the StackImpl class, create ‘main’ method where you should demonstrate the functionality of:
  1)	all the methods from the Stack interface (including those inherited from Container and Interable);
  2)	all the methods of the Iterator interface (hasNext/next/remove).
  <p>
  <p>
  _______________________
  <p>
  Notes.
  1.	The result should be presented as a project named Practice2.
  2.	The root package for all the classes: com.epam.rd.java.basic.practice2
  3.  Additionally, add Demo class to your root package that demonstrates the actions of all the subtasks.
  4.  Upload the project to the repository, run the build successfully in Jenkins, optimize the metrics in Sonar.
  <p>
  _______________________
 */
