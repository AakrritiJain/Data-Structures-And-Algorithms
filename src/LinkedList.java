public class LinkedList<T> {
    private class Node<T> {
        private T value;
        private Node<T> next;

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

    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
    }

    public void removeFirst() {
        if (isEmpty())
            throw new RuntimeException("List Empty");
        if (first == last) {
            first = last = null;
            return;
        }
        Node<T> node = first.next;
        first.next = null;
        first = node;
    }

    public T removeLast() {
        if (isEmpty())
            throw new RuntimeException("List Empty");
        if (first == last) {
            Node<T> nodeToReturn = first;
            first = last = null;
            return nodeToReturn.value;
        }
        Node<T> nodeToReturn = last;
        Node<T> previous = getPrevious(last);
        last = previous;
        last.next = null;
        return nodeToReturn.value;
    }

    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("List Empty");
        return first.value;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("List Empty");
        return last.value;
    }

    public void reverse() {
        if (isEmpty()) return;

        Node<T> prev = first;
        Node<T> curr = first.next;
        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        last = first;
        last.next = null;
        first = prev;
    }

    public T[] toArray() {
        T[] arr = (T[]) new Object[size()];
        int index = 0;
        Node<T> node = first;
        while (node != null) {
            arr[index++] = node.value;
            node = node.next;
        }
        return arr;
    }

    public void addAloop(){
        first.next.next.next = first.next;
    }

    public boolean hasLoop () {
        Node<T> slow = first;
        Node<T> fast = first;
        while (fast != last && fast.next != last){
            if(fast.next.next == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public T[] getMiddle() {
        if (isEmpty()) throw new IllegalArgumentException();

        T[] arr = (T[]) new Object[2];
        Node<T> slow = first;
        Node<T> fast = first;
        while (fast != last && fast.next != last) {
            slow = slow.next;
            fast = fast.next.next;
        }

        arr[0] = slow.value;
        if (fast.next == last)
            arr[1] = slow.next.value;

        return arr;
    }


    public T getKthFromTheEnd(int k) {
        if (k <= 0 || isEmpty()) throw new IllegalArgumentException();

        Node<T> kth = first;
        Node<T> next = first;
        for (int i = 0; i < k - 1; i++) {
            next = next.next;
            if (next == null)
                throw new IllegalArgumentException();
        }
        while (next != last) {
            next = next.next;
            kth = kth.next;
        }

        return kth.value;
    }

//    public Node<T> getKthFromTheEnd(int k) {
//        if (k <= 0 || isEmpty()) throw new IllegalArgumentException();
//
//        Node<T> kth = null;
//        Node<T> next = first;
//        int count = 0;
//        while (next != last) {
//            if (count == k -1) {
//                kth = first;
//            }
//            if (kth != null) {
//                kth = kth.next;
//            }
//
//            count++;
//            next = next.next;
//        }
//
//        return kth;
//    }

    public int size() {
        int count = 0;
        Node<T> node = first;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    private Node<T> getPrevious(Node<T> node) {
        Node<T> current = first;
        while (current != null) {
            if (current.next == node)
                return current;
            current = current.next;
        }
        return null;
    }

    public int indexOf(T value) {
        int index = 0;
        Node<T> node = first;
        while (node != null) {
            if (node.value == value)
                return index;
            node = node.next;
            index++;
        }
        return -1;
    }


    public boolean contains(T value) {
        return indexOf(value) != -1;
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
