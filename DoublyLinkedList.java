public class DoublyLinkedList {

    // Node class represents each element in the list
    class Node {
        int value;
        Node next;
        Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    // Head -> first element
    // Tail -> last element
    // Length -> number of elements
    private Node head;
    private Node tail;
    private int length;

    // Constructor (initialize list with one node)
    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    // ================= BASIC METHODS =================

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    // Check if list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Print all elements
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // ================= ADD METHODS =================

    // Add element at the end
    public void append(int value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            length = 1;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        length++;
    }

    // Add element at the beginning
    public void prepend(int value) {
        if (isEmpty()) {
            append(value);
            return;
        }

        Node newNode = new Node(value);
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        length++;
    }

    // Insert element at specific index
    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;

        if (index == 0) {
            prepend(value);
            return true;
        }

        if (index == length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.next;

        before.next = newNode;
        newNode.prev = before;
        newNode.next = after;
        after.prev = newNode;

        length++;
        return true;
    }

    // ================= REMOVE METHODS =================

    // Remove last element
    public Node removeLast() {
        if (isEmpty()) return null;

        Node temp = tail;

        if (length == 1) {
            head = null;
            tail = null;
            length = 0;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
            length--;
        }

        return temp;
    }

    // Remove first element
    public Node removeFirst() {
        if (isEmpty()) return null;

        Node temp = head;

        if (length == 1) {
            head = null;
            tail = null;
            length--;
            return temp;
        }

        head = head.next;
        head.prev = null;
        temp.next = null;
        length--;

        return temp;
    }

    // Remove element at specific index
    public Node remove(int index) {
        if (index < 0 || index >= length) return null;

        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node temp = get(index);

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;

        temp.next = null;
        temp.prev = null;

        length--;
        return temp;
    }

    // ================= GET & SET =================

    // Get node by index (optimized from both directions)
    public Node get(int index) {
        if (index < 0 || index >= length) return null;

        Node temp;

        // If index in first half → start from head
        if (index < length / 2) {
            temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }
        // If index in second half → start from tail
        else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }

        return temp;
    }

    // Update value at index
    public boolean set(int index, int value) {
        Node temp = get(index);

        if (temp != null) {
            temp.value = value;
            return true;
        }

        return false;
    }
}
