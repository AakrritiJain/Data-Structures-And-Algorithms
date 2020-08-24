public class DoublyLinkedList<T> {
    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private Node<T> first;
    private Node<T> last;

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    public void add(T value) {
        addLast(value);
    }

    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
    }

    public void removeFirst() {
        if (first == null)
            throw new RuntimeException("List Empty");
        if (isEmpty()) {
            first = last = null;
            return;
        }
        Node<T> node = first.next;
        first.next = null;
        first = node;
    }

    public void removeLast() {
        if (isEmpty())
            throw new RuntimeException("List Empty");
        if (first == last) {
            first = last = null;
            return;
        }
        Node<T> node = last.prev;
        node.next = null;
        last = node;
    }

    public int size() {
        int count = 0;
        Node<T> node = first;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

    public int indexOf(T value) {
        int index = 0;
        Node<T> node = first;
        while (node != null) {
            if (node.value == value)
                return index;
            index++;
            node = node.next;
        }
        return -1;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("List Empty");
        return first.value;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("List Empty");
        return last.value;
    }

    private boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        if (first != null) {
            Node<T> node = first;
            while (node.next != null) {
                stringBuilder.append(node.value).append(" ,");
                node = node.next;
            }
            if (node == last) {
                stringBuilder.append(node.value);
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }
}
