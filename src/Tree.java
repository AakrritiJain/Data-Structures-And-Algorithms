import java.util.ArrayList;
import java.util.List;

public class Tree {
    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node=" + this.value;
        }
    }

    private Node root;

    public void insert(int value) {
        Node newNode = new Node();
        newNode.value = value;
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        while (true) {
            if (value > current.value) {
                if (current.rightChild == null) {
                    current.rightChild = newNode;
                    break;
                }
                current = current.rightChild;
            } else {
                if (current.leftChild == null) {
                    current.leftChild = newNode;
                    break;
                }
                current = current.leftChild;
            }
        }
    }

    public boolean search(int value) {
        Node current = root;

        while (current != null) {
            if (current.value == value)
                return true;
            if (value > current.value) {
                current = current.rightChild;
            } else current = current.leftChild;
        }
        return false;
    }

    public void traversePreOrder() {
        this.traversePreOrder(root);
    }

    public void traverseInOrder() {
        this.traverseInOrder(root);
    }

    public int height() {
        return this.height(root);
    }

    public void traversePostOrder() {
        this.traversePostOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node == null)
            return;

        traverseInOrder(node.leftChild);
        System.out.println(node.value);
        traverseInOrder(node.rightChild);
    }

    private int height(Node node) {
        if (root == null)
            return -1;
        if (isLeaf(node))
            return 0;

        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private void traversePostOrder(Node node) {
        if (node == null)
            return;

        traversePostOrder(node.leftChild);
        traversePostOrder(node.rightChild);
        System.out.println(node.value);
    }

    private void traversePreOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.value);
        traversePreOrder(node.leftChild);
        traversePreOrder(node.rightChild);
    }
//    Binary Search tree: find min value
//    public int min(){
//        return this.min(root);
//    }
//
//    private int min(Node node){
//        if(node.leftChildIndex == null)
//            return node.value;
//
//       return min(node.leftChildIndex);
//    }

    //   Binary tree: find min value
    public int min() {
        return this.min(root);
    }

    private int min(Node node) {
        if (isLeaf(node))
            return node.value;

        int left = min(node.leftChild);
        int right = min(node.rightChild);

        return Math.min(Math.min(left, right), root.value);
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

    public boolean equals(Tree tree) {
        if (tree == null)
            return false;
        return this.equals(this.root, tree.root);

    }

    public boolean equals(Node root, Node otherRoot) {
        if (root == null && otherRoot == null)
            return true;
        if (root != null && otherRoot != null)
            return root.value == otherRoot.value
                    && equals(root.leftChild, otherRoot.leftChild)
                    && equals(root.rightChild, otherRoot.rightChild);
        return false;
    }

    public void swapRoot() {
        Node temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
    }

    public boolean isBinarySearchTree() {
        return this.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int min, int max) {
        if (root == null)
            return true;
        if (root.value < min || root.value > max)
            return false;
        return isBinarySearchTree(root.leftChild, min, root.value - 1)
                && isBinarySearchTree(root.rightChild, root.value + 1, max);
    }

    public List<Integer> getNodesAtDistance(int k) {
        List<Integer> list = new ArrayList<>();
        this.getNodesAtDistance(k, root, list);
        return list;
    }

    private void getNodesAtDistance(int k, Node root, List<Integer> list) {
        if (root == null)
            return;
        if (k == 0)
            list.add(root.value);
        else {
            getNodesAtDistance(k - 1, root.leftChild, list);
            getNodesAtDistance(k - 1, root.rightChild, list);
        }
    }

    public void traverseLevelOrder() {
        for (int i = 0; i <= height(); i++)
            getNodesAtDistance(i).forEach(System.out::println);
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return 1 + size(root.leftChild) + size(root.rightChild);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return countLeaves(root.leftChild) + countLeaves(root.rightChild);
    }

    // Binary Search tree: find max value
//    public int max(){
//        return this.max(root);
//    }
//
//    private int max(Node node){
//        if(node.rightChildIndex == null)
//            return node.value;
//
//       return min(node.rightChildIndex);
//    }

    // Binary tree: find max value
    public int max() {
        return this.max(root);
    }

    private int max(Node root) {
        if (isLeaf(root))
            return root.value;

        int left = max(root.leftChild);
        int right = max(root.rightChild);

        return Math.max(Math.max(left, right), root.value);
    }

//  Binary Search Tree: Contains O log(n)
//    public boolean contains(int item) {
//        return this.contains(root, item);
//    }
//
//    private boolean contains(Node root, int item) {
//        if (root == null)
//            return false;
//        if (item > root.value)
//            return contains(root.rightChildIndex, item);
//        else if(item < root.value)
//            return contains(root.leftChildIndex, item);
//        return true;
//    }

    //  Binary Tree: Contains
    public boolean contains(int item) {
        return this.contains(root, item);
    }

    private boolean contains(Node root, int item) {
        if (root == null)
            return false;
        if (root.value == item)
            return true;

        return contains(root.rightChild, item) || contains(root.leftChild, item);
    }

    //  Binary Search Tree: find O log(n)
    public boolean find(int item) {
        Node curr = root;
        while (curr != null) {
            if (curr.value == item)
                return true;
            else if (curr.value > item)
                curr = curr.leftChild;
            else curr = curr.rightChild;
        }
        return false;
    }

    public boolean areSibling(int first, int second) {
        return areSibling(root, first, second);
    }

    private boolean areSibling(Node root, int first, int second) {
        if (root == null)
            return false;

        boolean areSibling = false;
        if (root.leftChild != null && root.rightChild != null) {
            areSibling = (root.leftChild.value == first && root.rightChild.value == second) ||
                    (root.rightChild.value == first && root.leftChild.value == second);
        }

        return areSibling ||
                areSibling(root.leftChild, first, second) ||
                areSibling(root.rightChild, first, second);
    }

    public List<Integer> getAncestors(int value) {
        List<Integer> list = new ArrayList<>();
        getAncestors(root, value, list);
        return list;
    }

    private boolean getAncestors(Node root, int value, List<Integer> list) {
        // We should traverseDepthFirst the tree until we find the target value. If
        // find the target value, we return true without adding the current node
        // to the list; otherwise, if we ask for ancestors of 5, 5 will be also
        // added to the list.
        if (root == null)
            return false;

        if (root.value == value)
            return true;

        // If we find the target value in the left or right sub-trees, that means
        // the current node (root) is one of the ancestors. So we add it to the list.
        if (getAncestors(root.leftChild, value, list) ||
                getAncestors(root.rightChild, value, list)) {
            list.add(root.value);
            return true;
        }

        return false;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node root) {
        if (root == null)
            return true;

        int balanceFactor = height(root.leftChild) - height(root.rightChild);

        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(root.leftChild) &&
                isBalanced(root.rightChild);
    }

    public boolean isPerfect() {
        return size() == (Math.pow(2, height() + 1) - 1);
    }
}
