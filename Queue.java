/**
 * Queue implementation using Linked List
 * FIFO (First In First Out)
 */
public class Queue {

    /**
     * Node represents each element in the queue
     */
    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node first;  // Front of the queue
    private Node last;   // End of the queue
    private int length;  // Number of elements

    /**
     * Constructor - initialize queue with one element
     */
    public Queue(int value) {
        Node newNode = new Node(value);
        first = newNode;
        last = newNode;
        length = 1;
    }

    /**
     * Check if queue is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Print all elements in the queue
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Node temp = first;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Print first element
     */
    public void getFirst() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.println("First: " + first.value);
    }

    /**
     * Print last element
     */
    public void getLast() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.println("Last: " + last.value);
    }

    /**
     * Print queue length
     */
    public void getLength() {
        System.out.println("Length: " + length);
    }

    /**
     * Add element to the end of queue
     */
    public void enqueue(int value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }

        length++;
    }

    /**
     * Remove and return the first element
     */
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        Node temp = first;

        if (length == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            temp.next = null; // clean reference
        }

        length--;
        return temp.value;
    }
}
