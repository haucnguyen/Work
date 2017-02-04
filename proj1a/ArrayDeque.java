public class ArrayDeque <item> {

    private static int ArraySize = 8;
    private item nextFirst;
    private item nextLast;
    private int[] array;
    private int size;


    /** Creates an empty Array Deque */
    public ArrayDeque() {
        array = new int[ArraySize];
        size = 0;
    }

    /** Adds an item to the front of the Deque. */
    public void addFirst(item x) {
        nextFirst = x;
        
        size++;
    }

    /** Adds an item to the back of the Deque. */
    public void addLast(int x) {
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (nextFirst == nextLast) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the Deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the Deque from first to last, separated by a space. */
    public void printDeque() {
        int x = 0;
        while (x < size) {
            System.out.print(array[x] + " ");
            x++;
        }
    }

   /** Removes and returns the item at the front of the Deque. If no such item exists, returns null. */
    public item removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        return null;
    }


        /** Removes and returns the item at the back of the Deque. If no such item exists, returns null. */

    public item removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        return null;
    }

    public item get(int index) {
        return null;
    }

}
