import java.util.Deque;

public class Palindrome {

    public static Deque<Character> wordToDeque(String word) {
        ArrayDequeSolution donewithdeque = new ArrayDequeSolution();
        int size = word.length();

        for (int x = 0; x < size; x++) {
            donewithdeque.addLast(word.charAt(x));
            System.out.println(word.charAt(x));
        }
        return donewithdeque;
    }

    public static boolean isPalindrome(String word) {
        int size = word.length() / 2;
        int end = word.length() - 1;

        if (word.length() <= 1) {
            return true;
        }
        for (int x = 0; x <= size; x++) {
            if (word.charAt(x) != word.charAt(end)) {
                return false;
            }
            end--;
        }
        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
            int size = word.length() / 2;
            int end = word.length() - 1;

            if (word.length() <= 1) {
                return true;
            }
            for (int x = 0; x <= size; x++) {
                if (!cc.equalChars(word.charAt(x), word.charAt(end))) {
                    return false;
                }
                end--;
            }
            return true;
    }
}
