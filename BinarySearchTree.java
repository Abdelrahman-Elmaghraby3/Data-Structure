/**
 * Binary Search Tree Implementation
 * Supports: insert, search (contains), delete (iterative)
 */
public class BinarySearchTree {

    // Root of the tree
    private Node root;

    /**
     * Node class represents each element in the tree
     */
    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    /**
     * Insert a new value into the BST
     * @return true if inserted, false if value already exists
     */
    public boolean insert(int value) {
        Node newNode = new Node(value);

        // If tree is empty → new node becomes root
        if (root == null) {
            root = newNode;
            return true;
        }

        Node temp = root;

        while (true) {
            // Prevent duplicates
            if (temp.value == value) return false;

            // Go left
            if (value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            }
            // Go right
            else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    /**
     * Search for a value in the BST
     * @return true if found, false otherwise
     */
    public boolean contains(int value) {
        Node temp = root;

        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true; // Found
            }
        }

        return false; // Not found
    }

    /**
     * Delete a value from the BST (Iterative)
     * @return true if deleted, false if value not found
     */
    public boolean remove(int value) {

        Node parent = null;
        Node current = root;

        // <1> Search for the node to delete
        while (current != null) {
            if (value < current.value) {
                parent = current;
                current = current.left;
            } else if (value > current.value) {
                parent = current;
                current = current.right;
            } else {
                break; // Node found
            }
        }

        // If value not found
        if (current == null) return false;

        /**
         * <2️> Case: Node has TWO children
         * Replace with smallest value in right subtree (successor)
         */
        if (current.left != null && current.right != null) {

            Node successorParent = current;
            Node successor = current.right;

            // Find smallest in right subtree (go left until null)
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // Replace value
            current.value = successor.value;

            // Now delete the successor instead
            parent = successorParent;
            current = successor;
        }

        /**
         * <3️> Case: Node has ONE child or NO children
         */
        Node child;

        // If left child exists → use it
        if (current.left != null) {
            child = current.left;
        } 
        // Else use right child (or null if no children)
        else {
            child = current.right;
        }

        /**
         * <4️> Re-link parent with child
         */

        // Case: deleting root
        if (parent == null) {
            root = child;
        }
        // If current is left child
        else if (parent.left == current) {
            parent.left = child;
        }
        // If current is right child
        else {
            parent.right = child;
        }

        return true;
    }
}
