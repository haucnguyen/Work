
public class Palindrome {

    public static Deque<Character> wordToDeque (String word) {
        ArrayDequeSolution Deque = new ArrayDequeSolution();
        int size = word.length();

        for (int x = 0; x < size; x++) {
            Deque.addLast(word.charAt(x));
            System.out.println(word.charAt(x));
        }
        return (Deque<Character>) Deque;
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
