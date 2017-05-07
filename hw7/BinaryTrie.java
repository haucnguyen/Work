import java.io.CharArrayReader;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Hau on 5/6/17.
 */

public class BinaryTrie implements Serializable {
    private trieNode root, lol;
    private int length;
    PriorityQueue<trieNode> hi;
    StringBuilder pop;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
         hi = new PriorityQueue<>(Comparator.comparingInt(o -> o.frequency));

        for (Map.Entry<Character, Integer> a : frequencyTable.entrySet()) {
            lol = new trieNode(a.getValue(), a.getKey());
            hi.add(lol);
        }

        while (hi.size() > 1) {
            trieNode a = hi.poll();
            trieNode b = hi.poll();
            hi.add(new trieNode(a.frequency + b.frequency,  ' ', a, b));
        }

        root = hi.poll();


    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        trieNode node = root;
        length = querySequence.length();
        pop = new StringBuilder();

        for (int i = 0; i <= length; i++) {
            if (node.isLeaf()) {
                return new Match(new BitSequence(pop.toString()), node.character);
            }
            int k = querySequence.bitAt(i);
            pop.append(String.valueOf(k));
            node = node.childNode(k);
        }
        return null;
    }

    public Map<Character, BitSequence> buildLookupTable() {
       return null;
    }


    private class trieNode implements Serializable{
        private char character;
        private int frequency;
        private trieNode leftTree, rightTree;

        public trieNode(int frequency, char character, trieNode leftTree, trieNode rightTree) {
            this.frequency = frequency;
            this.character = character;
            this.leftTree = leftTree;
            this.rightTree = rightTree;
        }

        public trieNode(int frequency, char character) {
            this(frequency, character, null, null);
        }

        public trieNode childNode(int index) {
            if (index == 0) {
                return leftTree;
            } else return rightTree;
        }

        public boolean isLeaf() {
            return leftTree == null && rightTree == null;
        }
    }
}
