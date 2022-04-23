package ua.advanced.Practical5.Task1;

public class ConsoleNotification implements INotifier {
    synchronized public void notify(String message,Object source) {
        System.out.println(message);
    }
}
