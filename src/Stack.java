import java.util.Arrays;

public class Stack<T> {
    private T[] items;
    private int count;

    public Stack(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    public void push(T value) {
        resizeIfFull();
        items[count++] = value;
    }

    private void resizeIfFull() {
        if (count >= items.length) {
            T[] arr = (T[]) new Object[count * 2];
            for (int i = 0; i < items.length; i++)
                arr[i] = items[i];
            items = arr;
        }
    }

    public T pop() {
        if (count == 0) throw new IllegalStateException();
        return items[--count];
    }

    public T peek() {
        if (count == 0) throw new IllegalStateException();
        return items[count];

    }

    public boolean empty() {
        return (count == 0);
    }

    @Override
    public String toString() {
        T[] arr = Arrays.copyOfRange(items, 0, count);
        return Arrays.toString(arr);
    }
}
