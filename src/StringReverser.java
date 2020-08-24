import java.util.Stack;

public class StringReverser {
    public String reverse(String input) {
        if (input == null) throw new IllegalArgumentException();

        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray())
            stack.push(ch);

        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.empty())
            stringBuffer.append(stack.pop());

        return stringBuffer.toString();
    }
}
