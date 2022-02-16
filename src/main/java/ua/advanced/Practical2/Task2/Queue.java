package ua.advanced.Practical2.Task2;

import ua.advanced.Practical1.Container;

public interface Queue extends Container {
        // Appends the specified element to the end.
        void enqueue(Object element);

        // Removes the head.
        Object dequeue();

        // Returns the head.
        Object top();
    }

