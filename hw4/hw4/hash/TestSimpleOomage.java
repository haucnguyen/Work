package hw4.hash;

<<<<<<< HEAD
import org.junit.Assert;
=======
>>>>>>> e5eb1deae982c391e100a8fe3837e97db9d60f20
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
<<<<<<< HEAD
        SimpleOomage ooA = new SimpleOomage(0, 0, 5);
        int counter = 0;
        int poop = ooA.hashCode();
        SimpleOomage[] ooB = new SimpleOomage[2];
        ooB[0] = new SimpleOomage(5, 10, 20);
        ooB[1] = new SimpleOomage(5, 20, 10);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        for (int i = 0; i < 2; i++) {
            if (poop == (ooB[i].hashCode())) {
                counter++;
            }
        }
        assertEquals(1, counter);
=======
>>>>>>> e5eb1deae982c391e100a8fe3837e97db9d60f20
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode!
         */
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Once you've finished haveNiceHashCodeSpread,
    in OomageTestUtility, uncomment this test. */
    /*@Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }*/

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
