package ua.advanced.Practical4.Task2;
/*
Task 2
———————————————————————————————-
Package name: practice4
Class names: Part2, Spam
———————————————————————————————-

Create Spam class that accepts an array of messages and a coherent array of time intervals in milliseconds in its constructor,
and simultaneously prints out the corresponding messages in the given time intervals. On Enter click,
the application should terminate its work (this functionality should be placed in the Spam.main method).

Recommended structure of the Spam class:
————————————————
public class Spam {
  private Thread[] threads;
  public Spam(String[], int[]) {…}
  public void start() {…}
  public void stop() {…}
  private static class Worker extends Thread {…}
  public static void main(String[] args) {...}
}
————————————————

During demonstration of the functionality, mock Enter click in every 2 seconds (this functionality is to be placed in the Part2.main method).

Input data (an array of messages and an array of intervals) are to be written in the code of the Spam class. The quantity of the elements in each of the arrays should be at least 2, you can take them from the example:

Example of the input data (Spam class)
————————————————
String[] messages = new String[] { "@@@", "bbbbbbb" };
int[] times = new int[] { 333, 222 };
————————————————

Additional task information.

(1)	Do not use daemon threads as the execution of Part2 goes along with the other tasks in the package, daemons threads will not terminate their work until the Demo.main method finishes its execution.

(2) In order to track the Enter click, it’s enough to read the console input and analyze the content. If the reading is implemented using Scanner / BufferedReader classes, then an empty line will be the sign of the Enter click that is returned, respectively, by Scanner#nextLine() / BufferedReader#readLine() methods.

(3) The algorithm of the console input mock (Part2.main method):

————————————————
(a) substitute the system input stream for your own:
	System.setIn(YOUR_OWN_INPUT_STREAM);

(b) call Spam.main in a separate thread:
	Thread t = new Thread() { public void run() {Spam.main(null);}};
 	t.start();

(c) wait until Spam.main terminates its work:
	t.join();

(d) restore the system stream:
	System.setIn(CAСHED_VALUE_OF_SYSTEM_IN)
————————————————

(4) For implementation of your own input stream it’s appropriate to create a class that extends java.io.InputStream abstract class. However, you will need to implement the only abstract method of this class:

————————————————
public abstract int read() throws IOException;  ————————————————

All the methods from the InputStream class (along with all its child classes methods) that read bytes from some data resource, in the end, call the ‘read’ method. It’s enough to implement a pause in this method during the first call that will make the execution thread, calling the ‘read’ method, wait. The ‘read’ method should sequentially return bytes that correspond to the line terminator, after that it needs to return -1 only (the sign that there is no data in the input stream anymore).

_______________________
*/