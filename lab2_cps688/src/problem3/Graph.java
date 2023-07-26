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
import java.util.LinkedList;

class Graph {
    int v;
    LinkedList<Edge>[] adjList;

    public Graph(int v) {
        this.v = v;
        this.adjList = new LinkedList[this.v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<Edge>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        Edge srcEdge = new Edge(src, dest, weight);
        Edge destEdge = new Edge(dest, src, weight);
        adjList[src].add(srcEdge);
        adjList[dest].add(destEdge);
    }

    public LinkedList<Edge>[] getAdjList() {
        return adjList;
    }

    public int getWeight(int src, int dest) {
        for (Edge edge : adjList[src]) {
            if (edge.v == dest) {
                return edge.weight;
            }
        }
        return 0;
    }
}