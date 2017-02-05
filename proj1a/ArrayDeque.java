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

    private void upSize() {
        Item[] a = (Item[]) new Object[size * 2];
        System.arraycopy(array, nextFirst + 1, a, 0, array.length - (nextFirst + 1));
        System.arraycopy(array, 0, a, a.length - (nextFirst + 1), nextFirst + 1);
        array = a;
        nextFirst = (array.length / 2) - 1;
        nextLast = 0;
    }

    private void downSize() {
        Item[] a = (Item[]) new Object[array.length / 4];
        int halfLength = a.length / 2;
        System.arraycopy(array, nextFirst + 1, a, halfLength, halfLength);
        System.arraycopy(array, nextFirst + 1 + halfLength, a, 0, size - halfLength);
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
        Item a = array[nextFirst + 1];
        array[nextFirst + 1] = null;
        nextFirst++;
        size--;
        if (midDistance == array.length * 2) {
            midDistance = array.length + 1;
        }
            midDistance++;
        if (size % 4 < array.length) {
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
        Item a = array[nextLast - 1];
        array[nextLast - 1] = null;
        nextLast--;
        size--;
        if (midDistance == array.length + 1) {
            midDistance = array.length * 2;
        }
        midDistance--;
        if (size % 4 < array.length) {
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
