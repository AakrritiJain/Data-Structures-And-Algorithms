import java.util.*;
import java.util.Stack;

public class Graph {
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalStateException();

        Node toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalStateException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String label) {
        Node curr = nodes.get(label);
        if (curr == null)
            return;

        for (Node node : adjacencyList.keySet())
            adjacencyList.get(node).remove(curr);

        adjacencyList.remove(curr);
        nodes.remove(label);
    }

    public void removeEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return;
        adjacencyList.get(fromNode).remove(toNode);
    }

    public void traverseDepthFirst(String label) {
        Node node = nodes.get(label);
        if (node == null)
            return;
        traverseDepthFirst(node, new HashSet<>());
    }

    private void traverseDepthFirst(Node root, Set<String> visited) {
        System.out.println(root.label);
        visited.add(root.label);

        for (Node n : adjacencyList.get(root)) {
            if (!visited.contains(n.label))
                traverseDepthFirst(n, visited);
        }
    }

    public void traverseBreadthFirst(String label) {
        traverseBreadthFirst(nodes.get(label), new HashSet<>(), new ArrayDeque<>());
    }

    private void traverseBreadthFirst(Node root, Set<String> visited, Queue<String> queue) {
        if (!visited.contains(root.label))
            System.out.println(root.label);
        for (Node n : adjacencyList.get(root)) {
            queue.offer(n.label);
        }
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            if (!visited.contains(poll)) {
                visited.add(poll);
                System.out.println(poll);
            }
        }
        for (Node n : adjacencyList.get(root)) {
            traverseBreadthFirst(n, visited, queue);
        }
    }

    public List<String> topologicalSort(String label) {
        Node root = nodes.get(label);
        if(root == null)
            return new ArrayList<>();


        return topologicalSort(root, new HashSet<>(), new ArrayList<>(), new Stack<>());
    }

    private List<String> topologicalSort(Node root, Set<String> visited, List<String> list, Stack<String> stack) {
        stack.push(root.label);
        visited.add(root.label);

        for(Node n: adjacencyList.get(root)){
            if(!visited.contains(n.label))
                topologicalSort(n, visited, list, stack);
        }

        list.add(stack.pop());
        return list;
    }

    public void print() {
        for (Node node : adjacencyList.keySet()) {
            System.out.println(node + " is connected to " + adjacencyList.get(node));
        }
    }
}
