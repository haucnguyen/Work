package hw4.hash;

<<<<<<< HEAD
import java.lang.reflect.Array;
=======
>>>>>>> e5eb1deae982c391e100a8fe3837e97db9d60f20
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
<<<<<<< HEAD
        int[] lol = new int[M];
        int bucketNum;
        for (Oomage s: oomages) {
            bucketNum = (s.hashCode() & 0x7FFFFFFF) % M;
            lol[bucketNum]++;
        }
        for (int i = 0; i < M; i++) {
            if (lol[i] < oomages.size() / 50 || lol[i] > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;

=======
>>>>>>> e5eb1deae982c391e100a8fe3837e97db9d60f20
        /* TODO: Write a utility function that returns true if the given oomages 
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
<<<<<<< HEAD
=======
        return false;
>>>>>>> e5eb1deae982c391e100a8fe3837e97db9d60f20
    }
}
