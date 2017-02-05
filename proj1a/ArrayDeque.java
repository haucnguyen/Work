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
        int halfArraylength = array.length / 2;
        System.arraycopy(array, x, a, array.length, array.length - x);
        System.arraycopy(array, 0, a, a.length - x, x);
        array = a;
        nextFirst = (halfArraylength) - 1;
        nextLast = 0;

    }

    private void downSize() {
        Item[] a = (Item[]) new Object[array.length / 4];
        int x = plusOne(nextFirst);
        int halfLength = a.length / 2;
        System.arraycopy(array, x, a, halfLength, halfLength);
        System.arraycopy(array, x + halfLength, a, 0, size - halfLength);
        array = a;
        nextFirst = halfLength - 1;
        nextLast = size - halfLength;

    }



    /**
     * Adds an Item to the front of the Deque.
     */
    public void addFirst(Item x) {
        array[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size++;

        if (size == array.length) {
            upSize();
        }
    }

    /**
     * Adds an Item to the back of the Deque.
     */
    public void addLast(Item x) {
        array[nextLast] = x;
        nextLast = plusOne(nextLast);
        size++;
        if (size == array.length) {
            upSize();
        }
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

    /** Removes and returns the Item at the front of the Deque. */
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        if (nextFirst == nextLast) {
            size--;
            return array[nextFirst];
        }
        int x = plusOne(nextFirst);
        Item a = array[x];
        array[x] = null;
        nextFirst++;
        size--;
        if ((size / array.length) < .25 && array.length > 16) {
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
        if (nextFirst == nextLast) {
            size--;
            return array[nextLast];
        }
        int x = minusOne(nextLast);
        Item a = array[x];
        array[x] = null;
        nextLast--;
        size--;
        if ((size / array.length) < .25 && array.length > 16) {
            downSize();
        }
        return a;
    }

    public Item get(int index) {
        if (index > array.length - 1) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        return array[index - (array.length - nextFirst)];
    }
}
