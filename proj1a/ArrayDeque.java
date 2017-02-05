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
        if (nextFirst < nextLast) {
            System.arraycopy(array, nextFirst, a, 0, size);
            nextFirst = 0;
            nextLast = size - 1;
        } else {
            System.arraycopy(array, nextFirst, a, 0, array.length - nextFirst);
            System.arraycopy(array, 0, a, a.length - nextFirst, nextLast + 1);
            nextFirst = 0;
            nextLast = size - 1;
        }
        array = a;
    }


    private void downSize() {
        Item[] a = (Item[]) new Object[array.length / 4];
        if (nextFirst < nextLast) {
            System.arraycopy(array, nextFirst, a, 0, size);
            nextFirst = 0;
            nextLast = size - 1;
        } else {
            System.arraycopy(array, nextFirst, a, 0, array.length - nextFirst);
            System.arraycopy(array, 0, a, a.length - nextFirst, nextLast + 1);
            nextFirst = 0;
            nextLast = size - 1;
        }
        array = a;
    }


    /**
     * Adds an Item to the front of the Deque.
     */
    public void addFirst(Item x) {
        if (nextFirst - 1 == nextLast) {
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
        if (nextLast + 1 == nextFirst) {
            upSize();
        }
        if (((nextFirst == 0 && nextLast == array.length - 1)) && size != 0) {
            upSize();
        }
        if (size == array.length) {
            upSize();
        }
        if (nextFirst == nextLast && size == 0) {
            array[nextLast] = x;
        }
        if (nextLast == array.length - 1) {
            nextLast = 0;
            array[nextLast] = x;
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
        if (nextFirst == nextLast) {
            size--;
            return array[nextFirst];
        }
        if ((size / array.length) < .25 && array.length > 16) {
            downSize();
        }
        int x = plusOne(nextFirst);
        Item a = array[x];
        array[x] = null;
        nextFirst++;
        size--;
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
        if ((size / array.length) < .25 && array.length > 16) {
            downSize();
        }
        int x = minusOne(nextLast);
        Item a = array[x];
        array[x] = null;
        nextLast--;
        size--;
        return a;
    }

    public Item get(int index) {
        if (index > array.length - 1) {
            return null;
        }
        if (index < 0) {
            return null;
        }
        if (nextFirst < nextLast) {
            return array[nextFirst + index];
        }
        if (index < array.length - nextFirst) {
            return array[nextFirst + index];
        }
        if (size == 1) {
            return array[nextFirst];
        }
        return array[index - (array.length - nextFirst)];
    }
}

