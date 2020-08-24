public class AVLTree {
    private class AVLNode {
        private int value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        AVLNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + this.value;
        }
    }

    private AVLNode root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode node, int value) {
        if (node == null) {
            return new AVLNode(value);
        }
        if (value > node.value)
            node.rightChild = insert(node.rightChild, value);
        else
            node.leftChild = insert(node.leftChild, value);

        setHeight(node);

        return balance(node);
    }

    private void setHeight(AVLNode node) {
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }

    private AVLNode balance(AVLNode node) {
        if (isLeftHeavy(node)) {
            if (balanceFactor(node.leftChild) < 0)
                root.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        } else if (isRightHeavy(node)) {
            if (balanceFactor(node.rightChild) > 0)
                root.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }
        return node;
    }

    private AVLNode rotateLeft(AVLNode node) {
        AVLNode newRoot = node.rightChild;
        node.rightChild = newRoot.leftChild;
        newRoot.leftChild = node;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private AVLNode rotateRight(AVLNode node) {
        AVLNode newRoot = node.leftChild;
        node.leftChild = newRoot.rightChild;
        newRoot.rightChild = node;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    private int height(AVLNode node) {
        return (node == null) ? -1 : node.height;
    }
}
