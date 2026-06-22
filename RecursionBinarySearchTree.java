/**
 * Recursive Binary Search Tree (BST) Implementation.
 *
 * Supported Operations:
 * - Recursive Insert
 * - Recursive Search
 * - Recursive Delete
 *
 * BST Property:
 * Left Child  < Parent
 * Right Child > Parent
 */
public class RecursionBinarySearchTree {

    // Root node of the tree
    private Node root;

    /**
     * Represents a single node in the BST.
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
     * Recursively searches for a value in the tree.
     *
     * Time Complexity:
     * O(log n) average
     * O(n) worst case
     */
    private boolean rContains(Node currentNode, int value) {

        // Value not found
        if (currentNode == null) {
            return false;
        }

        // Value found
        if (currentNode.value == value) {
            return true;
        }

        // Search in left subtree
        if (value < currentNode.value) {
            return rContains(currentNode.left, value);
        }

        // Search in right subtree
        return rContains(currentNode.right, value);
    }

    /**
     * Public wrapper for recursive search.
     */
    public boolean rContains(int value) {
        return rContains(root, value);
    }

    /**
     * Recursively inserts a value into the BST.
     *
     * Returns the current subtree root after insertion.
     *
     * Time Complexity:
     * O(log n) average
     * O(n) worst case
     */
    private Node rInsert(Node currentNode, int value) {

        // Found insertion position
        if (currentNode == null) {
            return new Node(value);
        }

        // Insert into left subtree
        if (value < currentNode.value) {
            currentNode.left = rInsert(currentNode.left, value);
        }

        // Insert into right subtree
        else if (value > currentNode.value) {
            currentNode.right = rInsert(currentNode.right, value);
        }

        // Return updated subtree root
        return currentNode;
    }

    /**
     * Public wrapper for recursive insertion.
     */
    public void rInsert(int value) {
        root = rInsert(root, value);
    }

    /**
     * Recursively deletes a node from the BST.
     *
     * Deletion Cases:
     * 1. Leaf node
     * 2. Node with one child
     * 3. Node with two children
     *
     * Time Complexity:
     * O(log n) average
     * O(n) worst case
     */
    private Node deleteNode(Node currentNode, int value) {

        // Value not found
        if (currentNode == null) {
            return null;
        }

        // Search left subtree
        if (value < currentNode.value) {
            currentNode.left = deleteNode(currentNode.left, value);
        }

        // Search right subtree
        else if (value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        }

        // Node found
        else {

            // Case 1: No children (leaf node)
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            }

            // Case 2: Only right child exists
            else if (currentNode.left == null) {
                return currentNode.right;
            }

            // Case 2: Only left child exists
            else if (currentNode.right == null) {
                return currentNode.left;
            }

            // Case 3: Node has two children
            else {

                // Find the smallest value in the right subtree
                int subTreeMin = minValue(currentNode.right);

                // Replace current value with successor value
                currentNode.value = subTreeMin;

                // Delete duplicate successor node
                currentNode.right =
                        deleteNode(currentNode.right, subTreeMin);
            }
        }

        return currentNode;
    }

    /**
     * Returns the minimum value in a subtree.
     *
     * The minimum value is always located
     * at the leftmost node.
     */
    private int minValue(Node currentNode) {

        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode.value;
    }

    /**
     * Public wrapper for recursive deletion.
     */
    public void deleteNode(int value) {
        root = deleteNode(root, value);
    }
}
