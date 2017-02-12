public interface Deque<Item> {

    void addFirst(Item x);

    void addLast(Item x);

    Item removeLast();

    Item removeFirst();

    Item get(int index);

    boolean isEmpty();

    int size();

    void printDeque();

}
