import java.util.*;

public class AdjacencyList{
    private LinkedList<Integer>[] adjList; //adjacency list to store graph
     int vertices; //total number of vertices
    private int edges; //total number of edges
    private boolean[] visited; // to track visited nodes

    //constructor to initialize the list.with given number of nodes
    public AdjacencyList(int nodes){
        this.vertices = nodes;
        this.edges = 0;
        this.adjList = new LinkedList[nodes];
        this.visited = new boolean[nodes];
        /*initializing a linkedlist for each index */
        for(int v = 0; v < vertices; v++){
            adjList[v] = new LinkedList<>();
        }
    }

    //method to add an edge between two nodes
    public void addEdge(int a, int b){
        adjList[a].add(b);
        adjList[b].add(a);
        edges++; 
    }
    //method to get degree of a vertex
    public int degreeVertex(int a){
        int size = 0;
        for(int i : adjList[a]){
            size++;
        }
        // System.out.println(size);
        return size;
    }
    //method to print adjacency list representation of graph
    public void printAllVertices(){
        for(int i = 0; i<vertices; i++){
            System.out.print("Vertex "+i+": ");
            for(int j : adjList[i]){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    //method to print vertices(nodes) connected to the given vertix
    public void printAdjVertices(int a){
        System.out.print("Vertex "+ a + " is connected to: ");
        for(int node : adjList[a]){
            System.out.print(node+" ");
        }
        System.out.println();
    }
    
    
    //method to perform breadth first search starting from given node
    public void BFS(int node) {
        visited = new boolean[vertices]; //reset visited array
        ArrayList<Integer> queue = new ArrayList<>(); //queue for bfs
    
        visited[node] = true;
        queue.add(node);
    
        while (!queue.isEmpty()) {
            int current = queue.remove(0);
            System.out.print(current + " ");
            for (int i : adjList[current]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
    

    //method to perform depth first search starting from given node
    public void DFS(int node){
        System.out.print(node + " ");
        visited[node] = true;
        for(int i : adjList[node]){
            if(!visited[i]){
                DFS(i);
            }
        }
    }
    //main method to test the program
    public static void main(String[] args){
        // AdjacencyList list = new AdjacencyList(6);
        //adding edges to graph
        // list.addEdge(0,1);
        // list.addEdge(0,3);
        // list.addEdge(1,2);
        // list.addEdge(2,4);
        // list.addEdge(3,4);
        // list.addEdge(3,5);
        
        AdjacencyList list = new AdjacencyList(30);
        list.addEdge(0, 1);
        list.addEdge(0, 2);
        list.addEdge(0, 3);
        list.addEdge(1, 4);
        list.addEdge(1, 5);
        list.addEdge(1, 6);
        list.addEdge(2, 7);
        list.addEdge(2, 8);
        list.addEdge(3, 9);
        list.addEdge(3, 10);
        list.addEdge(3, 11);
        list.addEdge(3, 12);
        list.addEdge(4, 13);
        list.addEdge(5, 14);
        list.addEdge(5, 15);
        list.addEdge(6, 16);
        list.addEdge(7, 17);
        list.addEdge(8, 18);
        list.addEdge(10, 19);
        list.addEdge(12, 20);
        list.addEdge(13, 21);
        list.addEdge(17, 22);
        list.addEdge(18, 23);
        list.addEdge(18, 24);
        list.addEdge(20, 25);
        list.addEdge(22, 28);
        list.addEdge(25, 26);
        list.addEdge(26, 27);
        list.addEdge(27, 29);
        
        list.printAdjVertices(2);
        list.printAllVertices();
        
        for(int i = 0; i<list.vertices; i++){
            System.out.println("Vertex "+i+" has "+list.degreeVertex(i)+" adjaceny vertices.");
        }

        System.out.println("DFS (Depth first search): ");
        list.DFS(0);

        System.out.println("\nBFS(Breadth First Search): ");
        list.BFS(0);
    }
}