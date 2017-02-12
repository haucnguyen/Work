public interface Deque<Item> {

    void addFirst(Item x);
    void addLast(Item x);
    boolean isEmpty();
    int size();
    void printDeque();
    Item removeLast();
    Item removeFirst();
    Item get(int index);
}
