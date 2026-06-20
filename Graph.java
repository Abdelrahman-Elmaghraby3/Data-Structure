```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    /*
     * Adjacency List representation of the graph.
     *
     * Key   -> Vertex
     * Value -> List of connected vertices
     */
    private HashMap<String, ArrayList<String>> adjList = new HashMap<>();


    // Print the graph in a readable format
    public void printGraph() {
        for (String vertex : adjList.keySet()) {
            System.out.println(vertex + " -> " + adjList.get(vertex));
        }
    }


    // Add a new vertex to the graph
    public boolean addVertex(String vertex) {

        // Vertex does not exist
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
            return true;
        }

        // Vertex already exists
        return false;
    }


    // Add an undirected edge between two vertices
    public boolean addEdge(String vertex1, String vertex2) {

        // Make sure both vertices exist
        if (adjList.containsKey(vertex1) && adjList.containsKey(vertex2)) {

            adjList.get(vertex1).add(vertex2);
            adjList.get(vertex2).add(vertex1);

            return true;
        }

        return false;
    }


    // Remove an edge between two vertices
    public boolean removeEdge(String vertex1, String vertex2) {

        // Make sure both vertices exist
        if (adjList.containsKey(vertex1) && adjList.containsKey(vertex2)) {

            adjList.get(vertex1).remove(vertex2);
            adjList.get(vertex2).remove(vertex1);

            return true;
        }

        return false;
    }


    // Remove a vertex and all edges connected to it
    public boolean removeVertex(String vertex) {

        // Vertex does not exist
        if (!adjList.containsKey(vertex)) {
            return false;
        }

        // Remove this vertex from all neighboring vertices
        for (String neighbor : adjList.get(vertex)) {
            adjList.get(neighbor).remove(vertex);
        }

        // Remove the vertex itself
        adjList.remove(vertex);

        return true;
    }


    /*
     * Depth First Search (DFS)
     *
     * Uses recursion and explores as deep as possible
     * before backtracking.
     */
    public void dfs(String vertex, HashSet<String> visited) {

        // Mark the current vertex as visited
        visited.add(vertex);

        // Process the current vertex
        System.out.print(vertex + " ");

        // Visit all unvisited neighbors
        for (String neighbor : adjList.get(vertex)) {

            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }


    /*
     * Breadth First Search (BFS)
     *
     * Uses a Queue and visits vertices level by level.
     */
    public void bfs(String startVertex) {

        // Stores visited vertices
        HashSet<String> visited = new HashSet<>();

        // Queue used for BFS traversal
        Queue<String> queue = new LinkedList<>();

        // Visit the starting vertex
        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {

            // Get the first vertex from the queue
            String currentVertex = queue.poll();

            // Process the current vertex
            System.out.print(currentVertex + " ");

            // Visit all neighbors of the current vertex
            for (String neighbor : adjList.get(currentVertex)) {

                if (!visited.contains(neighbor)) {

                    // Mark neighbor as visited
                    visited.add(neighbor);

                    // Add neighbor to the queue
                    queue.offer(neighbor);
                }
            }
        }
    }
}
```
