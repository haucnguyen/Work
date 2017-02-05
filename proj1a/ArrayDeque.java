public class ArrayDeque<Item> {

    private int nextFirst;
    private int nextLast;
    private Item[] array;
    private int size;
    private int midDistance;


    /**
     * Creates an empty Array Deque
     */
    public ArrayDeque() {
        array = (Item[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        midDistance = 13;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return array.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == array.length) {
            return 0;
        }
        return index + 1;
    }

    private void upSize() {
        Item[] a = (Item[]) new Object[size * 2];
        int x = plusOne(nextFirst);
        System.arraycopy(array, x, a, 0, array.length - x);
        System.arraycopy(array, 0, a, a.length - x, x);
        array = a;
        nextFirst = (array.length / 2) - 1;
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
        if (midDistance == array.length + 1) {
            midDistance = array.length;
        }
        midDistance -= array.length;
    }



    /**
     * Adds an Item to the front of the Deque.
     */
    public void addFirst(Item x) {
        array[nextFirst] = x;
        nextFirst--;
        size++;
        if (midDistance == array.length + 1) {
            midDistance = array.length * 2;
        }
        midDistance--;
        if (size == array.length) {
            upSize();
        }
    }

    /**
     * Adds an Item to the back of the Deque.
     */
    public void addLast(Item x) {
        array[nextLast] = x;
        nextLast++;
        size++;
        if (midDistance == array.length * 2) {
            midDistance = array.length + 1;
        }
        midDistance++;
        if (size == array.length) {
            upSize();
        }
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
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
        int x = plusOne(nextFirst);
        Item a = array[x];
        array[x] = null;
        nextFirst++;
        size--;
        if (midDistance == array.length * 2) {
            midDistance = array.length + 1;
        }
        midDistance++;
        if (array.length > 16 && (size / array.length) < .25) {
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
        int x = minusOne(nextLast);
        Item a = array[x];
        array[x] = null;
        nextLast--;
        size--;
        if (midDistance == array.length + 1) {
            midDistance = array.length * 2;
        }
        midDistance--;
        if (array.length > 16 && (size / array.length) < .25) {
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
        return array[(midDistance + index) % array.length];
    }
}
