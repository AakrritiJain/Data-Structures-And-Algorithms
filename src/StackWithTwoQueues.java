import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues<T> {
    private Queue<T> queue1 = new ArrayDeque<>();
    private Queue<T> queue2 = new ArrayDeque<>();

    public void push(T value) {
        queue1.offer(value);
    }

    public T pop() {
        if (empty()) throw new IllegalStateException();
        moveQueue();
        return queue2.poll();
    }

    public T peek() {
        if (empty()) throw new IllegalStateException();
        moveQueue();
        return queue2.peek();
    }

    public int size() {
        return queue1.size() + queue2.size();
    }

    private void moveQueue() {
        if (queue2.isEmpty()) {
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
        }
    }

    private boolean empty() {
        return queue2.isEmpty() && queue1.isEmpty();
    }
}
