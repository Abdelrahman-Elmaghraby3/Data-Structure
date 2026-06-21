import java.util.ArrayList;
import java.util.List;

/**
 * Min Heap implementation using ArrayList.
 *
 * Heap Property:
 * Every parent node is less than or equal to its children.
 *
 * Example:
 *          2
 *        /   \
 *       5     8
 *      / \
 *     10  7
 */
public class MinHeap {

    // Internal storage for heap elements
    private List<Integer> heap;

    /**
     * Creates an empty Min Heap.
     */
    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Returns a copy of the heap.
     */
    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    /**
     * Returns the index of the left child.
     */
    public int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Returns the index of the right child.
     */
    public int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * Returns the index of the parent.
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
     * Time Complexity: O(log n)
     */
    public void insert(int value) {
        heap.add(value);

        int current = heap.size() - 1;

        // Bubble Up
        while (current != 0 &&
                heap.get(current) < heap.get(parent(current))) {

            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Removes and returns the minimum element (root).
     *
     * Time Complexity: O(log n)
     */
    public Integer remove() {

        if (heap.size() == 0)
            return null;

        if (heap.size() == 1)
            return heap.remove(0);

        int minValue = heap.get(0);

        // Move last element to root
        heap.set(0, heap.remove(heap.size() - 1));

        // Restore heap property
        sinkDown(0);

        return minValue;
    }

    /**
     * Restores the Min Heap property by moving a node downward.
     *
     * Time Complexity: O(log n)
     */
    public void sinkDown(int index) {

        while (true) {

            int minIndex = index;

            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);

            // Check left child
            if (leftIndex < heap.size() &&
                    heap.get(leftIndex) < heap.get(minIndex)) {

                minIndex = leftIndex;
            }

            // Check right child
            if (rightIndex < heap.size() &&
                    heap.get(rightIndex) < heap.get(minIndex)) {

                minIndex = rightIndex;
            }

            // If a smaller child exists, swap
            if (index != minIndex) {
                swap(index, minIndex);
                index = minIndex;
            } else {
                return;
            }
        }
    }

    /**
     * Returns the minimum value without removing it.
     *
     * Time Complexity: O(1)
     */
    public Integer peek() {
        if (heap.isEmpty())
            return null;

        return heap.get(0);
    }

    /**
     * Returns the number of elements in the heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Checks whether the heap is empty.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
