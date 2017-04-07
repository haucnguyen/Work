package hw4.hash;

import java.lang.reflect.Array;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] lol = new int[M];
        for (Oomage s: oomages) {
            int bucketNum = (oomages.hashCode() & 0x7FFFFFFF) % M;
            lol[bucketNum]++;
        }
        for (int i = 0; i < M; i++) {
            if (lol[i] < oomages.size() / 50 || lol[i] > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;

        /* TODO: Write a utility function that returns true if the given oomages 
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
    }
}
