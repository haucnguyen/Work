public class LinkedListDeque <item> {

    private class Node {
        private item value;
        private Node next;
        private Node previous;

        private Node(item i, Node n, Node p) {
            value = i;
            next = n;
            previous = p;
        }
    }

    /**
     * The first item (if it exists) is at sentinel.next.
     */
    private Node sentinel;
    private int size;

    /**
     * Creates an empty SLList.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }

    public LinkedListDeque(item x) {
        sentinel = new Node(x, null, null);
        Node item = new Node(x, sentinel, sentinel);
        sentinel.previous = item;
        sentinel.next = item;
    }

    /**
     * Adds a Node to the front of the Deque.
     */
    public void addFirst(Node x) {
        x.next = sentinel.next;
        x.next.previous = x;
        sentinel.next = x;
        x.previous = sentinel;
        size++;
    }

    /**
     * Adds an Int to the front of the Deque
     */
    public void addFirst(item x) {
        Node num = new Node(x, sentinel.next, sentinel);
        sentinel.next = num;
        num.next.previous = num;
        size++;
    }

    /**
     * Adds a Node to the front of the Deque.
     */
    public void addLast(Node x) {
        x.next = sentinel;
        x.previous = sentinel.previous;
        x.previous.next = x;
        sentinel.previous = x;
        size++;
    }

    /**
     * Adds an Int to the front of the Deque.
     */
    public void addLast(item x) {
        Node num = new Node(x, sentinel, sentinel.previous);
        num.previous.next = num;
        sentinel.previous = num;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the Deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the Deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node placeholder = sentinel;
        while (placeholder.next != sentinel) {
            System.out.print(placeholder.next.value + " ");
            placeholder = placeholder.next;
        }
    }
    /** Removes and returns the item at the back of the Deque. If no such item exists, returns null. */
    public item removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        else {
        item placeholder = sentinel.next.value;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return placeholder;
        }
    }

    /** Removes and returns the item at the back of the Deque. If no such item exists, returns null. */
    public item removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        item placeholder = sentinel.previous.value;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size--;
        return placeholder;
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    public item get(int index) {
        if (index >= size){
            return null;
        }
        int counter = 0;
        Node placeholder = sentinel.next;
        while (counter < index) {
            placeholder = placeholder.next;
            counter++;
        }
        return placeholder.value;
    }
    /**  Same as get, but uses recursion. */
    public item getRecursive(int index) {
        if (index >= size){
            return null;
        }
        if (index == 0) {
            return sentinel.next.value;
        }
        Node placeholder = sentinel.next;
        return getRecursiveHelper(index, placeholder);

    }

    public item getRecursiveHelper(int index, Node n) {
        if (index == 0) {
            return n.value;
        }
        n = n.next;
        return getRecursiveHelper(index - 1, n);
    }
}
