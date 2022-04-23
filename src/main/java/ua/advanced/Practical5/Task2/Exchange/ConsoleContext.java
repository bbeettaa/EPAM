package ua.advanced.Practical5.Task2.Exchange;

public class ConsoleContext {
    synchronized public static void notify(String message) {
        System.out.println(message);
    }
}
