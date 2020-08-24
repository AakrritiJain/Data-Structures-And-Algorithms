import java.util.Stack;

public class QueueWithTwoStacks<T> {
    private Stack<T> enqueuingStack;
    private Stack<T> dequeuingStack;

    //O(1)
    public void enqueue(T value) {
        enqueuingStack.push(value);
    }

    // O(n)
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException();
        moveStacks();
        return dequeuingStack.pop();
    }

    // O(1)
    public T peek() {
        if (isEmpty()) throw new IllegalStateException();
        moveStacks();
        return dequeuingStack.peek();
    }

    public boolean empty() {
        return isEmpty();
    }

    private boolean isEmpty() {
        return dequeuingStack.empty() && enqueuingStack.empty();
    }

    private void moveStacks() {
        if (dequeuingStack.isEmpty()) {
            while (!enqueuingStack.empty()) {
                dequeuingStack.push(enqueuingStack.pop());
            }
        }
    }
}
