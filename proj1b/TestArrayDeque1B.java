import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void testMethods() {

        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> answer = new ArrayDequeSolution<>();

        for (int lol = 0; lol < 10000; lol++) {
            Integer number = StdRandom.uniform(100);

            if (student.isEmpty()) {

                if (number < 50) {
                    student.addFirst(number);
                    answer.addFirst(number);
                } else {
                    student.addLast(number);
                    answer.addLast(number);
                }

            } else {

                if (number < 25) {
                    System.out.println("addLast" + "(" + number + ")");
                    student.addLast(number);
                    answer.addLast(number);
                } else if (number < 50) {
                    System.out.println("addFirst" + "(" + number + ")");
                    student.addFirst(number);
                    answer.addFirst(number);
                } else if (number < 75) {
                    System.out.println("removeLast()");
                    assertEquals(answer.removeLast(), student.removeLast());
                } else {
                    System.out.println("removeFirst()");
                    assertEquals(answer.removeFirst(), student.removeFirst());

                }
            }
        }
    }
}
