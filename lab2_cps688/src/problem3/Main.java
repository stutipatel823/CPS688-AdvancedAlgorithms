/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem3;

/**
 *
 * @author s364pate
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // read input from file
        File file = new File("input.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = scan.nextInt();//total vertices
        int e = scan.nextInt();//total edges

        // Create a graph with n vertices
        Graph graph = new Graph(n);

        // Loop through all the edges
        for (int i = 0; i < e; i++) {
            // Get source vertex of the edge
            int src = scan.nextInt();
            // Get destination vertex of the edge
            int dest = scan.nextInt();
            // Get weight of the edge
            int weight = scan.nextInt();
            // Add the Edge to the graph
            graph.addEdge(src, dest, weight);
        }

        // Run Prim's algorithm
        // An array to keep track of visited vertices
        boolean[] visited = new boolean[n];
        // A priority queue to store the edges in increasing order of weight
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        // Get the adjacency list of the graph
        LinkedList<Edge>[] adjList = graph.getAdjList();
        // Start with vertex 0
        visited[0] = true;
        // Add all edges adjacent to vertex 0 to the priority queue
        for (Edge edge : adjList[0]) {
            pq.add(edge);
        }
        int mstWeight = 0;
        ArrayList<Edge> mst = new ArrayList<>();
        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            int u = minEdge.u;
            int v = minEdge.v;
            if (visited[u] && visited[v]) {
                continue;
            }
            mst.add(minEdge);
            mstWeight += minEdge.weight;
            if (!visited[u]) {
                visited[u] = true;
                for (Edge edge : adjList[u]) {
                    pq.add(edge);
                }
            }
            if (!visited[v]) {
                visited[v] = true;
                for (Edge edge : adjList[v]) {
                    pq.add(edge);
                }
            }
        }

        // Print the MST
        for (Edge edge : mst) {
            System.out.printf("Edge %d-%d has a weight of %d\n", edge.u, edge.v, edge.weight);
        }
        System.out.println("MST = "+mstWeight);
    }
}
