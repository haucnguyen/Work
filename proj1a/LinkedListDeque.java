public class LinkedListDeque <Item> {

    private class Node {
        private Item value;
        private Node next;
        private Node previous;

        private Node(Item i, Node n, Node p) {
            value = i;
            next = n;
            previous = p;
        }
    }

    /**
     * The first Item (if it exists) is at sentinel.next.
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

    private LinkedListDeque(Item x) {
        sentinel = new Node(x, null, null);
        Node Item = new Node(x, sentinel, sentinel);
        sentinel.previous = Item;
        sentinel.next = Item;
    }

    /**
     * Adds a Node to the front of the Deque.
     */
    private void addFirst(Node x) {
        x.next = sentinel.next;
        x.next.previous = x;
        sentinel.next = x;
        x.previous = sentinel;
        size++;
    }

    /**
     * Adds an Int to the front of the Deque
     */
    public void addFirst(Item x) {
        Node num = new Node(x, sentinel.next, sentinel);
        sentinel.next = num;
        num.next.previous = num;
        size++;
    }

    /**
     * Adds a Node to the front of the Deque.
     */
    private void addLast(Node x) {
        x.next = sentinel;
        x.previous = sentinel.previous;
        x.previous.next = x;
        sentinel.previous = x;
        size++;
    }

    /**
     * Adds an Int to the front of the Deque.
     */
    public void addLast(Item x) {
        Node num = new Node(x, sentinel, sentinel.previous);
        num.previous.next = num;
        sentinel.previous = num;
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
     * Prints the Items in the Deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node placeholder = sentinel;
        while (placeholder.next != sentinel) {
            System.out.print(placeholder.next.value + " ");
            placeholder = placeholder.next;
        }
    }
    /** Removes and returns the Item at the back of the Deque. If no such Item exists, returns null. */
    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            Item placeholder = sentinel.next.value;
            sentinel.next.next.previous = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return placeholder;
        }
    }

    /** Removes and returns the Item at the back of the Deque. */
    public Item removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            Item placeholder = sentinel.previous.value;
            sentinel.previous.previous.next = sentinel;
            sentinel.previous = sentinel.previous.previous;
            size--;
            return placeholder;
        }
    }
    /** Gets the Item at the given index */
    public Item get(int index) {
        if (index >= size) {
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
    public Item getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.value;
        }
        Node placeholder = sentinel.next;
        return getRecursiveHelper(index, placeholder);

    }

    private Item getRecursiveHelper(int index, Node n) {
        if (index == 0) {
            return n.value;
        }
        n = n.next;
        return getRecursiveHelper(index - 1, n);
    }
}
