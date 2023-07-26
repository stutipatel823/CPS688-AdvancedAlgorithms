/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem2;

/**
 *
 * @author s364pate
 */
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class AcyclicGraph {
    private int vertices,edges;
    public LinkedList<Integer> adjList[];
    private boolean[] visited;

   // Constructor
    public AcyclicGraph(int vertices, int edges){
        // Initialize instance variables
        this.vertices = vertices;
        this.edges = edges;
        this.visited = new boolean[vertices];
        adjList = new LinkedList[vertices];
        for(int i=0; i < vertices; i++){
            adjList[i] = new LinkedList<>();
        }
    }

    // Method to add edge to the graph
    public void addEdge(int a, int b){
        adjList[a].add(b);
        adjList[b].add(a);
    }

    // Method to print the adjacency list of the graph
    public void printGraph(){
        for(int i=0; i<vertices; i++){
            System.out.print("Vertex "+i+": ");
            for(int j=0; j<adjList[i].size(); j++){
                System.out.print(adjList[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    // Method to check if the graph contains a cycle using DFS(from previous lab)
    public boolean isCyclic_usingDFS(int node, int parent){
        // System.out.print(node + " ");
        visited[node] = true;
        for(int neighbor : adjList[node]){
            if(!visited[neighbor]){
                isCyclic_usingDFS(neighbor, node);
            }
            else if(neighbor != parent){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            // Read the input file
            File file = new File("input1.txt");//change the input2.txt to input1.txt to test other txt file
            Scanner scan = new Scanner(file);
            
            // Create a new graph object
            AcyclicGraph graph = new AcyclicGraph(scan.nextInt(),scan.nextInt());
                            
            // Add edges to the graph
            while (scan.hasNextInt()) {//start scanning the text file
                graph.addEdge(scan.nextInt(), scan.nextInt()); 
            }
            scan.close();
            
            // Print the adjacency list of the graph
            graph.printGraph();

            // Check if the graph contains a cycle
            if(graph.isCyclic_usingDFS(0, -1)){
                System.out.println("NO!: The graph contains a cycle.");
            } 
            else {
                System.out.println("YES!: The graph is acyclic (no cycles).");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}