public class ArrayDeque <item> {

    private static int ArraySize = 8;
    private int nextFirst;
    private int nextLast;
    private int[] array;
    private int size;


    /** Creates an empty Array Deque */
    public ArrayDeque() {
        array = new int[ArraySize];
        nextFirst = 1;
        nextLast= 0;
        size = 0;
    }

    /** Adds an item to the front of the Deque. */
    public void addFirst(int x) {
        array[nextFirst] = x;
        nextLast++;
        size++;
    }

    /** Adds an item to the back of the Deque. */
    public void addLast(int x) {
        array[nextLast] = x;
        nextLast--;
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
    public int removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        return array[nextFirst + 1];
    }

    /** Removes and returns the item at the back of the Deque. If no such item exists, returns null. */
    public int removeLast(){
        if (this.isEmpty()) {
            return null;
        }
        return array[nextLast + 1];
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. */
    public int get(int index){
        return array[index];
    }
}