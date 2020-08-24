import java.util.Arrays;

public class PriorityQueue {
    private int[] items;
    private int count;

    public PriorityQueue(int capacity) {
        this.items = new int[capacity];
    }

    public void enqueue(int value) {
        if (isFull()) throw new IllegalStateException();
        int i = shiftItems(value);
        items[i] = value;
        count++;
    }

    private boolean isFull() {
        return count == items.length;
    }

    private int shiftItems(int value) {
        int i;
        for (i = count - 1; i >= 0; i--) {
            if (items[i] > (value))
                items[i + 1] = items[i];
            else break;
        }
        return i + 1;
    }

    public int dequeue() {
        if (count == 0) throw new IllegalStateException();

        return items[--count];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
