/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem2;

/**
 *
 * @author s364pate
 */
// Import necessary packages
import java.io.*;
import java.util.*;

// Graph class to represent directed graphs
public class Graph {
    // Number of vertices in the graph
    private int V;
    // An array of linked lists to store the adjacency list
    private LinkedList<Integer>[] adj;

    // Constructor to initialize the graph
    Graph(int v) {
        V = v;
        // Initialize the adjacency list for each vertex
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    // Method to add an edge to the graph
    void addEdge(int v, int w) {
        // Add an edge from vertex v to vertex w
        adj[v].add(w); 
        // DO NOT add adj[w].add(v) because the graphs are directed.
    }

    // Recursive utility function to do a DFS traversal of the graph
    void DFSUtil(int v, boolean[] visited) {
        // Mark the current vertex as visited
        visited[v] = true;
        // Recur for all the vertices adjacent to this vertex
        for (int n : adj[v]) {
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // Method to get the transpose of the graph
    Graph getTranspose() {
        // Create a new graph object with the same number of vertices
        Graph g = new Graph(V);
        // Traverse through all the vertices in the original graph
        for (int i = 0; i < V; i++) {
            // Traverse through all the adjacent vertices in the original graph
            for (int n : adj[i]) {
                // Add the reverse edge to the new graph
                g.addEdge(n, i);
            }
        }
        // Return the transpose graph
        return g;
    }

    // Method to check if the graph is strongly connected
    boolean isStronglyConnected() {
        // Initialize an array to keep track of visited vertices
        boolean[] visited = new boolean[V];
        // Do a DFS traversal of the graph starting from vertex 0
        DFSUtil(0, visited);

        // If any vertex is not reachable from vertex 0, return false
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        // Get the transpose of the graph
        Graph g = getTranspose();

        // Reset the visited array to all false values
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        // Do a DFS traversal of the transpose graph starting from vertex 0
        g.DFSUtil(0, visited);

        // If any vertex is not reachable from vertex 0 in the transpose graph, return false
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        // If all vertices are reachable from vertex 0 and all vertices can reach vertex 0, return true
        return true;
    }

// Main class to read input and output the result
    public static void main(String[] args) {
    try {
    // Read input from a file named input1.txt
    Scanner in = new Scanner(new File("input1.txt"));
    // Read the number of vertices and edges from the input
    int n = in.nextInt();
    int e = in.nextInt();
            // Create a new Graph object with n vertices
            Graph g = new Graph(n);

            // Add edges to the graph by reading them from the input
            for (int i = 0; i < e; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                g.addEdge(u, v);
            }
    
            // Check if the graph is strongly connected and output the result
            if (g.isStronglyConnected()) {
                System.out.println("yes");
            } 
            else {
                System.out.println("no");
            }
        } catch (FileNotFoundException e) {
            // If the input file is not found, output an error message
            System.out.println("Input file not found.");
        }
    
    } 
}   