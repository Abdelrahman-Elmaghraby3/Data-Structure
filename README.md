#  Java Data Structures 

A comprehensive reference implementation of the most essential **Data Structures**  — written with clean code, full inline documentation, and Big O complexity analysis for every operation.

---

##  Repository Structure

| File | Description |
|------|-------------|
| `SingleLinkedList.java` | Singly Linked List with core operations |
| `DoublyLinkedList.java` | Doubly Linked List with forward & backward traversal |
| `Stack.java` | Stack (LIFO) implemented using a Linked List |
| `Queue.java` | Queue (FIFO) implemented using a Linked List |
| `BinarySearchTree.java` | BST with iterative insert, search, delete & traversals |
| `RecursionBinarySearchTree.java` | BST with recursive CRUD operations |
| `MaxHeap.java` | Max Heap with insert and extract-max |
| `MinHeap.java` | Min Heap with insert and extract-min |
| `HashTable.java` | Hash Table with chaining for collision handling |
| `Graph.java` | Graph with adjacency list, BFS & DFS |

---

##  Data Structures

###  Linked Lists
- **SingleLinkedList** — append, prepend, insert, delete, reverse
- **DoublyLinkedList** — all singly operations + backward traversal

###  Stack & Queue
- **Stack** — `push`, `pop`, `peek` — O(1) all operations
- **Queue** — `enqueue`, `dequeue`, `peek` — O(1) all operations

###  Binary Search Tree (BST)
Two implementations — **iterative** and **recursive**:
- `insert` / `contains` / `remove`
- **BFS** — Level-order traversal
- **DFS** — Pre-order / In-order / Post-order

>  In-order traversal of a BST always returns elements in **sorted order**.

###  Heaps
- **MaxHeap** — parent is always greater than its children; `insert`, `extractMax`
- **MinHeap** — parent is always smaller than its children; `insert`, `extractMin`

###  Hash Table
- Handles collisions using **separate chaining** (linked lists per bucket)
- `set`, `get`, `keys`

###  Graph
- Adjacency list representation
- `addVertex`, `addEdge`
- **BFS** and **DFS** traversal

---


---

##  Getting Started

### Prerequisites
- Java 8 or higher
- Any IDE (IntelliJ IDEA, Eclipse, VS Code)

### Run a file
```bash
# Compile
javac BinarySearchTree.java

# Run
java BinarySearchTree
```

---

##  Concepts Covered

- [x] Linked Lists (Singly & Doubly)
- [x] Stack & Queue
- [x] Binary Search Tree (Iterative & Recursive)
- [x] Heaps (Min & Max)
- [x] Hash Tables
- [x] Graphs (BFS & DFS)




> ⭐ If you find this repo helpful, consider giving it a star!
