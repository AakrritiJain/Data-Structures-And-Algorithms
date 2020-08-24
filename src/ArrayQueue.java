import java.util.Arrays;

public class ArrayQueue<T> {
    private T[] items;
    private int front;
    private int rear;
    private int count;

    public ArrayQueue(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    public void enqueue(T value) {
        if (count == items.length) throw new IllegalStateException();
        items[rear] = value;
        rear = (rear + 1) % items.length;
        count++;
    }

    public T dequeue() {
        count--;
        T item = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        return item;
    }

    public T peek() {
        return items[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == items.length;
    }

    public int size() {
        return count;
    }

    public void reverseFirst(int k) {
        if (k > items.length) throw new IllegalStateException();

        Stack<T> stack = new Stack<>(5);
        for (int i = 0; i < k; i++)
            stack.push(items[i]);
        int index = 0;
        while (!stack.empty())
            items[index++] = stack.pop();
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
