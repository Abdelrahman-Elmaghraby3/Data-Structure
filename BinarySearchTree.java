import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Search Tree (BST) implementation in Java.
 *
 * A BST is a tree data structure where each node has at most two children.
 * For every node:
 *   - All values in the LEFT subtree are LESS than the node's value.
 *   - All values in the RIGHT subtree are GREATER than the node's value.
 *
 * Supported operations:
 *   - insert(value)     : Add a new value to the tree.
 *   - contains(value)   : Check if a value exists in the tree.
 *   - remove(value)     : Delete a value from the tree.
 *   - BFS()             : Breadth-First Search traversal (level by level).
 *   - DFSPreOrder()     : Depth-First Search - Pre-Order  (Root → Left → Right).
 *   - DFSPostOrder()    : Depth-First Search - Post-Order (Left → Right → Root).
 *   - DFSInOrder()      : Depth-First Search - In-Order   (Left → Root → Right).
 */
public class BinarySearchTree {

    /** The root node of the tree. Null when the tree is empty. */
    private Node root;

    // ─────────────────────────────────────────────
    //  Inner Node Class
    // ─────────────────────────────────────────────

    /**
     * Represents a single node in the BST.
     * Each node holds an integer value and pointers to its left and right children.
     */
    class Node {
        int value;
        Node left;
        Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    // ─────────────────────────────────────────────
    //  Insert
    // ─────────────────────────────────────────────

    /**
     * Inserts a new value into the BST.
     *
     * Starting from the root, we walk down the tree:
     *   - Go LEFT  if the new value is smaller than the current node.
     *   - Go RIGHT if the new value is larger  than the current node.
     *   - Stop and return false if the value already exists (no duplicates allowed).
     *
     * Time complexity: O(log n) average, O(n) worst case (unbalanced tree).
     *
     * @param value The integer value to insert.
     * @return true if inserted successfully, false if the value already exists.
     */
    public boolean insert(int value) {
        Node newNode = new Node(value);

        // If the tree is empty, the new node becomes the root.
        if (root == null) {
            root = newNode;
            return true;
        }

        Node temp = root;

        while (true) {
            // Duplicate found — BSTs do not allow duplicate values.
            if (temp.value == newNode.value) return false;

            if (temp.value > newNode.value) {
                // New value is smaller → go left.
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                // New value is larger → go right.
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    // ─────────────────────────────────────────────
    //  Contains
    // ─────────────────────────────────────────────

    /**
     * Checks whether a given value exists in the BST.
     *
     * Walks the tree from the root, going left or right based on comparisons,
     * until the value is found or we reach a null node (not found).
     *
     * Time complexity: O(log n) average, O(n) worst case.
     *
     * @param value The integer value to search for.
     * @return true if the value is found, false otherwise.
     */
    public boolean contains(int value) {
        Node temp = root;

        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;   // Search left subtree.
            } else if (value > temp.value) {
                temp = temp.right;  // Search right subtree.
            } else {
                return true;        // Value found.
            }
        }

        return false; // Value not found.
    }

    // ─────────────────────────────────────────────
    //  Remove
    // ─────────────────────────────────────────────

    /**
     * Removes a value from the BST (if it exists).
     *
     * Three cases are handled:
     *   1. Node has NO children (leaf)      → Simply remove it.
     *   2. Node has ONE child               → Replace it with its child.
     *   3. Node has TWO children            → Replace its value with its
     *      in-order successor (the smallest value in its right subtree),
     *      then remove the successor node.
     *
     * Time complexity: O(log n) average, O(n) worst case.
     *
     * @param value The integer value to remove.
     * @return true if the value was found and removed, false if not found.
     */
    public boolean remove(int value) {
        Node parent  = null;
        Node current = root;

        // Step 1: Find the node to remove and track its parent.
        while (current != null) {
            if (current.value > value) {
                parent  = current;
                current = current.left;
            } else if (current.value < value) {
                parent  = current;
                current = current.right;
            } else {
                break; // Node found.
            }
        }

        // Value not found in the tree.
        if (current == null) return false;

        // Step 2: Handle the case where the node has TWO children.
        // We find the in-order successor: the leftmost node in the right subtree.
        // The successor is the smallest value greater than the current node,
        // so replacing current's value with it preserves the BST property.
        if (current.left != null && current.right != null) {
            Node successorParent = current;
            Node successor       = current.right;

            // Traverse left to find the smallest node in the right subtree.
            while (successor.left != null) {
                successorParent = successor;
                successor       = successor.left;
            }

            // Copy the successor's value into the current node.
            current.value = successor.value;

            // Now we need to remove the successor node instead.
            parent  = successorParent;
            current = successor;
        }

        // Step 3: Handle nodes with ONE or ZERO children.
        // A node with at most one child: pick that child (or null if none).
        Node child;
        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right; // Could be null if it's a leaf node.
        }

        // Link the parent to the child, bypassing the removed node.
        if (parent == null) {
            // Removing the root node.
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        return true;
    }

    // ─────────────────────────────────────────────
    //  Breadth-First Search (BFS)
    // ─────────────────────────────────────────────

    /**
     * Traverses the BST level by level using Breadth-First Search (BFS).
     *
     * Uses a Queue to process nodes one level at a time:
     *   1. Dequeue the front node and record its value.
     *   2. Enqueue its left child (if any).
     *   3. Enqueue its right child (if any).
     *   4. Repeat until the queue is empty.
     *
     * Example for a tree with root 47, left 21, right 76:
     *   Result → [47, 21, 76]
     *
     * Time complexity: O(n)
     *
     * @return An ArrayList of node values in BFS (level-order) sequence.
     */
    public ArrayList<Integer> BFS() {
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> results = new ArrayList<>();

        queue.add(currentNode);

        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            results.add(currentNode.value);

            if (currentNode.left  != null) queue.add(currentNode.left);
            if (currentNode.right != null) queue.add(currentNode.right);
        }

        return results;
    }

    // ─────────────────────────────────────────────
    //  Depth-First Search – Pre-Order
    // ─────────────────────────────────────────────

    /**
     * Traverses the BST using DFS Pre-Order: Root → Left → Right.
     *
     * Visits the current node BEFORE recursing into its children.
     * Useful for copying or serializing a tree.
     *
     * Example for a tree with root 47, left 21, right 76:
     *   Result → [47, 21, 76]
     *
     * Time complexity: O(n)
     *
     * @return An ArrayList of node values in Pre-Order sequence.
     */
    public ArrayList<Integer> DFSPreOrder() {
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                results.add(currentNode.value);           // Visit root first.
                if (currentNode.left  != null) new Traverse(currentNode.left);
                if (currentNode.right != null) new Traverse(currentNode.right);
            }
        }

        new Traverse(root);
        return results;
    }

    // ─────────────────────────────────────────────
    //  Depth-First Search – Post-Order
    // ─────────────────────────────────────────────

    /**
     * Traverses the BST using DFS Post-Order: Left → Right → Root.
     *
     * Visits the current node AFTER recursing into its children.
     * Useful for deleting a tree or evaluating expression trees.
     *
     * Example for a tree with root 47, left 21, right 76:
     *   Result → [21, 76, 47]
     *
     * Time complexity: O(n)
     *
     * @return An ArrayList of node values in Post-Order sequence.
     */
    public ArrayList<Integer> DFSPostOrder() {
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left  != null) new Traverse(currentNode.left);
                if (currentNode.right != null) new Traverse(currentNode.right);
                results.add(currentNode.value);           // Visit root last.
            }
        }

        new Traverse(root);
        return results;
    }

    // ─────────────────────────────────────────────
    //  Depth-First Search – In-Order
    // ─────────────────────────────────────────────

    /**
     * Traverses the BST using DFS In-Order: Left → Root → Right.
     *
     * Visits nodes in ascending sorted order — a key property of BSTs.
     * Useful for retrieving all values in sorted sequence.
     *
     * Example for a tree with root 47, left 21, right 76:
     *   Result → [21, 47, 76]
     *
     * Time complexity: O(n)
     *
     * @return An ArrayList of node values in In-Order (sorted) sequence.
     */
    public ArrayList<Integer> DFSInOrder() {
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left  != null) new Traverse(currentNode.left);
                results.add(currentNode.value);           // Visit root in the middle.
                if (currentNode.right != null) new Traverse(currentNode.right);
            }
        }

        new Traverse(root);
        return results;
    }
}
