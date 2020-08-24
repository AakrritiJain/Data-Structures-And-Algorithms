public class DataStructure {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode("x");
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("p");
        graph.addEdge("x", "a");
        graph.addEdge("x", "b");
        graph.addEdge("b", "p");
        graph.addEdge("a", "p");
        System.out.println(graph.topologicalSort("x"));
    }
}
