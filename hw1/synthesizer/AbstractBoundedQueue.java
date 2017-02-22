public abstract class AbstractBoundedQueue implements BoundedQueue<T> {
    protected int fillCount;

    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }
}