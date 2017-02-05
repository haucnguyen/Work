public class ArrayDeque<Item> {

    private int nextFirst;
    private int nextLast;
    private Item[] array;
    private int size;


    /**
     * Creates an empty Array Deque
     */
    public ArrayDeque() {
        array = (Item[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;

    }

    private int minusOne(int index) {
        if (index == 0) {
            return array.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == array.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private void upSize() {
        Item[] a = (Item[]) new Object[size * 2];
        int x = plusOne(nextFirst);
        if (nextFirst < nextLast) {
            System.arraycopy(array, x, a, 0, size);
            array = a;
            nextFirst = array.length - 1;
            nextLast = size;
        } else {
            System.arraycopy(array, x, a, 0, array.length - x);
            System.arraycopy(array, 0, a, array.length - x, nextLast);
            array = a;
            nextFirst = array.length - 1;
            nextLast = size;
        }
    }


    private void downSize() {
        Item[] a = (Item[]) new Object[array.length / 4];
        int x = plusOne(nextFirst);
        if (nextFirst < nextLast) {
            System.arraycopy(array, x, a, 0, size);
            nextFirst = 0;
            nextLast = size - 1;
        } else {
            nextFirst = 0;
            System.arraycopy(array, nextFirst,  a, 0, array.length - x);
            System.arraycopy(array, 0, a, a.length - x, nextLast + 1);
            nextLast = size - 1;
        }
        array = a;
    }


    /**
     * Adds an Item to the front of the Deque.
     */
    public void addFirst(Item x) {
        if (nextFirst == nextLast) {
            upSize();
        }
        if (((nextFirst == 0) && nextLast == array.length - 1) && size != 0) {
            upSize();
        }
        if (size == array.length) {
            upSize();
        } else {
            array[nextFirst] = x;
            nextFirst = minusOne(nextFirst);
        }
        size++;
    }

    /**
     * Adds an Item to the back of the Deque.
     */
    public void addLast(Item x) {
        if (nextLast == nextFirst) {
            upSize();
        }
        if (((nextFirst == 0 && nextLast == array.length - 1)) && size != 0) {
            upSize();
        }
        if (size == array.length) {
            upSize();
        }
        if (size == 0) {
            array[nextLast] = x;
        }
        if (nextLast == array.length - 1) {
            array[nextLast] = x;
            nextLast = 0;
        } else {
            array[nextLast] = x;
            nextLast = plusOne(nextLast);
        }
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of Items in the Deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the Items in the Deque from first to last, by a space.
     */
    public void printDeque() {
        int x = 0;
        while (x < size) {
            System.out.print(array[x] + " ");
            x++;
        }
    }

    /**
     * Removes and returns the Item at the front of the Deque.
     */
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        int x = plusOne(nextFirst);
        Item a = array[x];
        array[x] = null;
        size--;
        nextFirst++;
        if (nextFirst == array.length) {
            nextFirst = 0;
        }
        if ((size / array.length) < .25 && array.length > 14) {
            downSize();
        }
        return a;
    }


    /**
     * Removes and returns the Item at the back of the Deque.
     */

    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = array.length - 1;
            size--;
            return array[nextLast];
        }
        int x = minusOne(nextLast);
        Item a = array[x];
        array[x] = null;
        nextLast--;
        size--;
        if (((double) size / array.length) < .25 && array.length > 14) {
            downSize();
        }
        return a;
    }


    public Item get(int index) {
        if (size == 1) {
            return array[nextFirst + index + 1];
        }
        if (nextFirst < nextLast && size == 1) {
            return array[nextFirst + index + 1];
        }
        if (index < array.length - nextFirst) {
            if (nextFirst + index + 1 == array.length) {
                return array[0];
            }
            return array[nextFirst + index + 1];
        }
        return array[index - (array.length - nextFirst) + 1];
    }

    private static void main(String[] args) {
        ArrayDeque<Integer> A = new ArrayDeque<Integer>();
        A.addLast(0);
        A.removeFirst();
        A.addLast(2);
        A.addFirst(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.get(2);
        A.addFirst(8);
        A.addLast(9);
        A.removeFirst();
        A.addLast(11);
        A.get(6);
        A.get(5);
        A.addLast(14);
        A.addFirst(15);
        A.addFirst(16);
        A.addFirst(17);
        A.get(2);
        A.addLast(19);
        A.addLast(20);
        A.addLast(21);
        System.out.print(A.removeLast());

    }
}

