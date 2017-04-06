package hw4.hash;

import java.lang.reflect.Array;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        Array[] lol = new Array[M];
        int counter = 0;
        for (int i = 0; i < oomages.size(); i++) {
            int bucketNum = (oomages.hashCode() & 0x7FFFFFFF) % M;

            counter++;
        }


        /* TODO: Write a utility function that returns true if the given oomages 
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        return false;
    }
}
