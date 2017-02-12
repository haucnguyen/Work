import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void testMethods() {

        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> answer = new ArrayDequeSolution<>();

        for (int lol = 0; lol < 100000; lol++); {

            int number = StdRandom.uniform(8);

            if (student.isEmpty()) {

                int operation = StdRandom.uniform(2);

                if (operation == 0) {
                    student.addFirst(number);
                    answer.addFirst(number);
                } else {
                    student.addLast(number);
                    answer.addLast(number);
                }

        } else {

            int operation = StdRandom.uniform(5);

            if (operation == 0) {
                student.addLast(number);
                answer.addLast(number);
            } else if (operation == 1) {
                student.addFirst(number);
                answer.addFirst(number);
            } else if (operation == 2) {
                Integer remove1 = student.removeLast();
                Integer remove2 = answer.removeLast();
            } else {
                Integer remove1 = student.removeFirst();
                Integer remove2 = answer.removeFirst();
            }
        }
    }


        for (int index = 0; index < answer.size(); index++) {
            Integer test1 = student.get(index);
            Integer test2 = answer.get(index);
            assertEquals(test1, test2);
        }
    }

    public void main(String[] args) {
        for (int x = 0; x < 1000000; x++) {
            testMethods();
        }
    }
}