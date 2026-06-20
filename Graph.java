import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    // Adjacency List representation of the graph
    // Key = Vertex
    // Value = List of connected vertices
    HashMap<String, ArrayList<String>> adjList = new HashMap<>();


    // Print the entire graph
    public void printGraph() {
        System.out.println(adjList);
    }


    // Add a new vertex to the graph
    public boolean addVertex(String vertex) {

        // Check if the vertex does not already exist
        if (!adjList.containsKey(vertex)) {

            // Create an empty neighbor list for the new vertex
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

            // Add vertex2 to vertex1's neighbor list
            adjList.get(vertex1).add(vertex2);

            // Add vertex1 to vertex2's neighbor list
            adjList.get(vertex2).add(vertex1);

            return true;
        }

        // One or both vertices do not exist
        return false;
    }


    // Remove an edge between two vertices
    public boolean removeEdge(String vertex1, String vertex2) {

        // Make sure both vertices exist
        if (adjList.containsKey(vertex1) && adjList.containsKey(vertex2)) {

            // Remove vertex2 from vertex1's neighbor list
            adjList.get(vertex1).remove(vertex2);

            // Remove vertex1 from vertex2's neighbor list
            adjList.get(vertex2).remove(vertex1);

            return true;
        }

        // One or both vertices do not exist
        return false;
    }


    // Remove a vertex and all edges connected to it
    public boolean removeVertex(String vertex) {

        // Check if the vertex exists
        if (!adjList.containsKey(vertex)) {
            return false;
        }

        // Iterate through all neighboring vertices
        for (String neighbor : adjList.get(vertex)) {

            // Remove the current vertex from each neighbor's list
            adjList.get(neighbor).remove(vertex);
        }

        // Remove the vertex itself from the graph
        adjList.remove(vertex);

        return true;
    }
}
