import java.util.Arrays;

public class Heap {
    private int items[];
    private int count;

    Heap(int capacity) {
        items = new int[capacity];
    }

    public void insert(int item) {
        if (isFull())
            throw new IllegalStateException();

        items[count++] = item;
        int currentIndex = count - 1;

        bubbleUp(currentIndex);
    }

    private void bubbleUp(int currentIndex) {
        while (currentIndex > 0 && items[currentIndex] > items[parent(currentIndex)]) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    public boolean isFull() {
        return count == items.length;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int item1, int item2) {
        int temp = items[item1];
        items[item1] = items[item2];
        items[item2] = temp;
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();

        int root = items[0];
        items[0] = items[--count];

        bubbleDown();

        return root;
    }

    private void bubbleDown() {
        int currentIndex = 0;
        while (currentIndex <= count && !isValidParent(currentIndex)) {
            int largerIndex = getLargerIndex(currentIndex);
            swap(currentIndex, largerIndex);
            currentIndex = largerIndex;
        }
    }

    private int getLargerIndex(int index) {
        if (!hasLeftChild(index))
            return index;

        if (!hasRightChild(index))
            return leftChildIndex(index);

        return leftChild(index) > rightChild(index)
                ? leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;

        boolean isValid = items[index] >= leftChild(index);

        if (hasRightChild(index))
            isValid &= items[index] >= rightChild(index);

        return isValid;
    }

    private int rightChild(int currentIndex) {
        return items[rightChildIndex(currentIndex)];
    }

    private int leftChild(int currentIndex) {
        return items[leftChildIndex(currentIndex)];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    public int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) <= count;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) <= count;
    }

//    public void heapify(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            if(arr[i] < arr[i] && arr[])
//        }
//    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
