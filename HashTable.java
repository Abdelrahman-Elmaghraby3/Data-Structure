import java.util.ArrayList;

public class HashTable {

    // Size of the hash table
    private int size = 7;

    // Array that stores the buckets
    private Node[] dataMap;

    // Constructor
    public HashTable() {
        dataMap = new Node[size];
    }

    // Each node stores a key-value pair
    // and a reference to the next node
    class Node {

        private String key;
        private int value;
        private Node next;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Converts a key into an array index
    private int hash(String key) {

        int hash = 0;
        char[] keyChars = key.toCharArray();

        for (int i = 0; i < keyChars.length; i++) {
            int asciiValue = keyChars[i];
            hash = (hash + asciiValue * 23) % dataMap.length;
        }

        return hash;
    }

    // Inserts a new key-value pair
    // Uses chaining to handle collisions
    public void set(String key, int value) {

        int index = hash(key);
        Node newNode = new Node(key, value);

        if (dataMap[index] == null) {

            dataMap[index] = newNode;

        } else {

            Node temp = dataMap[index];

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }
    }

    // Returns the value associated with the given key
    public int get(String key) {

        int index = hash(key);
        Node temp = dataMap[index];

        while (temp != null) {

            if (temp.key.equals(key)) {
                return temp.value;
            }

            temp = temp.next;
        }

        return 0;
    }

    // Returns all keys stored in the hash table
    public ArrayList<String> keys() {

        ArrayList<String> allKeys = new ArrayList<>();

        for (int i = 0; i < dataMap.length; i++) {

            Node temp = dataMap[i];

            while (temp != null) {

                allKeys.add(temp.key);
                temp = temp.next;
            }
        }

        return allKeys;
    }

    // Prints the entire hash table
    // Useful for testing and debugging
    public void printTable() {

        for (int i = 0; i < dataMap.length; i++) {

            System.out.println(i + ":");

            Node temp = dataMap[i];

            while (temp != null) {

                System.out.println(" {" + temp.key + " = " + temp.value + "}");

                temp = temp.next;
            }
        }
    }
}
