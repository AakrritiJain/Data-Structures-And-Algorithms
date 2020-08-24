import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private class Node {
        private char value;
        private boolean isEndOfWord;
        private HashMap<Character, Node> children = new HashMap<>();

        Node(char value) {
            this.value = value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public void removeChild(char ch) {
            children.remove(ch);
        }

        @Override
        public String toString() {
            return "Node = " + this.value;
        }
    }

    private Node root = new Node(' ');

    public void insert(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        Node current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isEndOfWord;
    }

    public void traverse() {
        this.traverse(root);
    }

    // Pre-Order traversal
    private void traverse(Node root) {
        if (root == null)
            return;

        System.out.println(root.value);
        for (Node node : root.getChildren()) {
            traverse(node);
        }
    }

    // Post-Order traversal
//    private void traverseDepthFirst(Node root){
//        if(root == null)
//            return;
//
//        for(Node node: root.getChildren()){
//            traverseDepthFirst(node);
//        }
//        System.out.println(root.value);
//    }

    public void remove(String word) {
        if (word == null)
            return;

        remove(root, word, 0);
    }

    private void remove(Node root, String word, int index) {
        if (index == word.length()) {
            root.isEndOfWord = false;
            return;
        }

        char ch = word.charAt(index);
        Node child = root.getChild(ch);
        if (child == null)
            return;

        remove(child, word, index + 1);

        if (!child.hasChildren() && !child.isEndOfWord)
            root.removeChild(ch);
    }

    public List<String> findWords(String prefix) {
        List<String> words = new ArrayList<>();
        Node last = findLastNodeOf(prefix);
        findWords(last, prefix, words);
        return words;
    }

    private void findWords(Node root, String prefix, List<String> words) {
        if (root == null)
            return;

        if (root.isEndOfWord)
            words.add(prefix);

        for (Node child : root.getChildren())
            findWords(child, prefix + child.value, words);
    }

    private Node findLastNodeOf(String prefix) {
        if (prefix == null)
            return null;

        Node current = root;
        for (char ch : prefix.toCharArray()) {
            Node child = current.getChild(ch);
            if (child == null)
                return null;
            current = child;
        }
        return current;
    }

}