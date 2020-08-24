public class Array<T> {
    private int count;
    private T[] items;

    public Array(int count) {
        items = (T[]) new Object[count];
    }

    public void add(T item) {
        resizeIfFull();
        items[count++] = item;
    }

    private void resizeIfFull() {
        if (count == items.length) {
            T[] arr = (T[]) new Object[count * 2];
            for (int i = 0; i < count; i++) {
                arr[i] = items[i];
            }
            items = arr;
        }
    }

    public int size() {
        return count;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= count)
            throw new ArrayIndexOutOfBoundsException();
        for (int i = index; i < count - 1; i++)
            items[i] = items[i + 1];
        count--;
    }

    public int indexOf(T item) {
        for (int i = 0; i < count; i++) {
            if (items[i] == item)
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void clear() {
        for (int i = 0; i < count; i++)
            items[i] = null;
        count = 0;
    }

    public Array<T> intersect(Array<T> anotherArray) {
        Array<T> intersection = new Array<>(count);
        for (T item : items) {
            if (anotherArray.indexOf(item) >= 0)
                intersection.add(item);
        }
        return intersection;
    }

    public void reverse() {
        T[] newItems = (T[]) new Object[count];
        for (int i = 0; i < count; i++)
            newItems[i] = items[count - i - 1];

        items = newItems;
    }

    public void insertAt(T item, int index) {
        resizeIfFull();
        for (int i = count - 1; i >= index; i--) {
            items[i + 1] = items[i];
        }
        items[index] = item;
        count++;
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder(count);
        strBldr.append("[");
        for (int i = 0; i < count - 1; i++)
            strBldr.append(items[i] + " ,");
        if (count > 0)
            strBldr.append(items[count - 1]);
        strBldr.append("]");
        return strBldr.toString();
    }
}
