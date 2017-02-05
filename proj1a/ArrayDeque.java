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
        nextFirst = 1;
        nextLast = 2;
    }

    /** */
    private void upSize() {
        Item[] a = (Item[]) new Object[size * 2];
        System.arraycopy(array, size - nextFirst, a, 0, size);
        System.arraycopy(array, nextLast, a, size - nextFirst, size);
        array = a;
        nextFirst = array.length - 1;
        nextLast = size;

    }

    private void downSize() {
        Item[] a = (Item[]) new Object[size * 4];
        System.arraycopy(array, nextFirst + 1, a, 0, size);
        array = a;
        nextFirst = array.length - 1;
        nextLast = size;

    }

    /**
     * Adds an Item to the front of the Deque.
     */
    public void addFirst(Item x) {
        if (nextFirst == 0) {
            nextFirst = array.length - 1;
        }
        if (size == array.length) {
            upSize();
        }
        array[nextFirst] = x;
        nextFirst--;
        size++;
    }

    /**
     * Adds an Item to the back of the Deque.
     */
    public void addLast(Item x) {
        if (nextLast == array.length - 1) {
            nextLast = 0;
        }
        if (size == array.length) {
            upSize();
        }
        array[nextLast] = x;
        nextLast--;
        size++;

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

    /**
     * Removes and returns the Item at the front of the Deque.
     */
    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        Item a = array[nextFirst + 1];
        array[nextFirst + 1] = null;
        nextFirst++;
        size--;

        if (size % 4 < array.length) {
            downSize();
        }

        return a;
    }

    /**
     * Removes and returns the Item at the back of the Deque.
     */

    public Item removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        Item a = array[nextLast - 1];
        array[nextLast - 1] = null;
        nextLast--;
        size--;

        if (size % 4 < array.length) {
            downSize();
        }
        return a;
    }

    public Item get(int index) {
        if (size < index) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        return array[nextFirst + 1 + index];
    }
}


