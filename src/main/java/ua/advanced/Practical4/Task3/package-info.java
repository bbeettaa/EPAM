package ua.advanced.Practical4.Task3;
/*
 Task 3
———————————————————————————————-
Class name: practice4.Part3
———————————————————————————————-

Create a class with two separate counters.
Create several equal threads, each of them repeats the following:
	* compares the value of the counters and prints out the result of the comparison;
	* increments the first counter;
	* sleeps for 100 milliseconds;
	* increments the second counter.

Compare the program functionality provided that the code is synchronized and not synchronized.
Implement the following scheme:
	* at first, a not synchronized code gets executed.
	* after its termination, the synchronized code gets executed.
The resulting output should be small, not more than a few dozen lines.

The recommended structure of the class:
 ————————————————
public class Part3 {
 	 private int counter;
  	private int counter2;
  	public void compare() {…}
  	public void compareSync() {…}
  	public static void main(String[] args) {...}
}
————————————————

_______________________
* */