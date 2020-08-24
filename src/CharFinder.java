import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharFinder {
    public char findNonRepeatedFirstCharacter(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            int count = map.getOrDefault(ch, 0);
            map.put(ch, count + 1);
        }
        for (char ch : str.toCharArray()) {
            if (map.get(ch) == 1)
                return ch;

        }

        return Character.MIN_VALUE;
    }

    public char findFirstRepeatedCharacter(String str) {
        Set<Character> set = new HashSet<>();

        for (char ch : str.toCharArray()) {
            if (set.contains(ch))
                return ch;
            else set.add(ch);
        }
        return Character.MIN_VALUE;
    }
}
