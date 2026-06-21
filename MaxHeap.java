import java.util.ArrayList;
import java.util.List;

/**
 * Max Heap implementation using ArrayList.
 *
 * Heap Property:
 * Every parent node is greater than or equal to its children.
 *
 * Example:
 *          10
 *        /    \
 *       7      8
 *      / \
 *     2   5
 */
public class Heap {

    // Internal storage for heap elements
    private List<Integer> heap;

    /**
     * Creates an empty Max Heap.
     */
    public Heap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Returns a copy of the heap.
     * Prevents external modification of the original heap.
     */
    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    /**
     * Returns the index of the left child.
     *
     * Formula:
     * left = (2 * index) + 1
     */
    public int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Returns the index of the right child.
     *
     * Formula:
     * right = (2 * index) + 2
     */
    public int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * Returns the index of the parent node.
     *
     * Formula:
     * parent = (index - 1) / 2
     */
    public int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Swaps two elements inside the heap.
     */
    public void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    /**
     * Inserts a new value into the heap.
     *
     * Process:
     * 1. Add the value at the end.
     * 2. Bubble it up until the Max Heap property is restored.
     *
     * Time Complexity: O(log n)
     */
    public void insert(int value) {
        heap.add(value);

        // Start from the last inserted node
        int current = heap.size() - 1;

        // Bubble Up
        while (current != 0 &&
                heap.get(current) > heap.get(parent(current))) {

            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Removes and returns the maximum element (root).
     *
     * Process:
     * 1. Save the root value.
     * 2. Move the last element to the root.
     * 3. Sink it down to restore heap order.
     *
     * Time Complexity: O(log n)
     */
    public Integer remove() {

        // Heap is empty
        if (heap.size() == 0)
            return null;

        // Heap contains one element
        if (heap.size() == 1)
            return heap.remove(0);

        int maxValue = heap.get(0);

        // Move last element to root
        heap.set(0, heap.remove(heap.size() - 1));

        // Restore heap property
        sinkDown(0);

        return maxValue;
    }

    /**
     * Moves a node downward until the Max Heap property is restored.
     *
     * Time Complexity: O(log n)
     */
    public void sinkDown(int index) {

        while (true) {

            int maxIndex = index;

            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);

            // Check left child
            if (leftIndex < heap.size()
                    && heap.get(leftIndex) > heap.get(maxIndex)) {

                maxIndex = leftIndex;
            }

            // Check right child
            if (rightIndex < heap.size()
                    && heap.get(rightIndex) > heap.get(maxIndex)) {

                maxIndex = rightIndex;
            }

            // If a larger child exists, swap
            if (index != maxIndex) {
                swap(index, maxIndex);
                index = maxIndex;
            } else {
                // Heap property restored
                return;
            }
        }
    }
}
