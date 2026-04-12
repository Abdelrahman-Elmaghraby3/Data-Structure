/**
 * Stack implementation using Linked List
 * LIFO (Last In First Out)
 */
public class Stack {

    /**
     * Node class represents each element in the stack
     */
    private class Node {
        private int value;
        private Node next;

        // Constructor
        public Node(int value) {
            this.value = value;
        }
    }

    private Node top;   // Points to the top element of the stack
    private int height; // Number of elements in the stack

    /**
     * Constructor - initialize stack with one element
     */
    public Stack(int value) {
        Node newNode = new Node(value);
        top = newNode;
        height = 1;
    }

    /**
     * Print all elements in the stack
     */
    public void printStack() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    /**
     * Print the top element
     */
    public void getTop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Top: " + top.value);
    }

    /**
     * Print the number of elements
     */
    public void getHeight() {
        System.out.println("Height: " + height);
    }

    /**
     * Push a new value onto the stack
     */
    public void push(int value) {
        Node newNode = new Node(value);

        // If stack is empty
        if (height == 0) {
            top = newNode;
        } else {
            // Insert at the top
            newNode.next = top;
            top = newNode;
        }

        height++;
    }

    /**
     * Remove and return the top element
     */
    public int pop() {
        if (height == 0) {
            System.out.println("Stack is empty");
            return -1;
        }

        Node temp = top;
        top = top.next;

        // Disconnect node (clean memory reference)
        temp.next = null;

        height--;
        return temp.value;
    }

    /**
     * Return the top element without removing it
     */
    public int peek() {
        if (height == 0) {
            System.out.println("Stack is empty");
            return -1;
        }
        return top.value;
    }
}
